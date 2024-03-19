package com.lexlei.festivalplaylistapp.Controllers;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lexlei.festivalplaylistapp.Configuration.SpotifyConfiguration;
import com.lexlei.festivalplaylistapp.Models.SpotifyUser;
import com.lexlei.festivalplaylistapp.Repositories.SpotifyUserRepository;
import com.lexlei.festivalplaylistapp.Service.SpotifyUserService;
import com.neovisionaries.i18n.CountryCode;

import jakarta.servlet.http.HttpServletResponse;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

/**
 * SpotifyController Class
 * 
 * This class handles all the HTTP requests relating to the Spotify user.
 * Includes enpoints for retrieving and managing Spotify user data, redirect 
 * pages, and playlist creation.
 */
@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {
    @Value("${custom.ip}")
	private String customIp = "https://www.festbeatsapp.com";

	@Autowired
	private SpotifyConfiguration spotifyConfiguration;

    @Autowired
    private SpotifyUserService spotifyUserService;
	
	@Autowired
	private SpotifyUserRepository spotifyUserRepository;
    /**
     * Initiates the Spotify OAuth process.
     * This method creates an authorization request to Spotify's OAuth endpoint.
     * 
     * @return uri - String representation of the redirect URI for Spotify's OAuth login.
     */
    @GetMapping("login")
    public String spotifyLogin() {
        SpotifyApi spotify = spotifyConfiguration.getSpotifyObject(); // Instantiate SpotifyApi object
        
        // Build request for authorization endpoint URI
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotify.authorizationCodeUri()
                .scope("user-read-private user-read-email playlist-modify-private playlist-modify-public") // Requested permissions
                .show_dialog(true) // Shows authorization dialogue when the user logs in
                .build(); // Completes construction of the request

        // Executes the build request and retireves the URI which redirects user to authorization page
        final URI uri = authorizationCodeUriRequest.execute();

        // Returns authorization URI as a String
        return uri.toString();
    }

    /**
     * Handles the retrieval of the Spotify user's access and refresh token and reference ID
     * This endpoint is triggered by the OAuth callback.
     * 
     * @param authorizationCode An authorization code that can be exchanged for an access token. 
     *                          It is passed in by Spotify as a query parameter in the redirect URI 
     *                          after the user accepts the authorization request.
     *                          I.e. http://localhost:8080//api/spotify/get-user-code?code=XXX..XXX&state=XXXXXXXX
     * @param response The HttpServletResponse object to manage the HTTP response.
     * @throws IOException If an input or output exception occurs.
     */
    @GetMapping(value = "get-user-code")
    public void getSpotifyUserCode(@RequestParam(value = "code", required = false) String authorizationCode, 
                                   @RequestParam(value = "error", required = false) String error, 
                                   HttpServletResponse response) throws IOException {

        // Check if there is an error in the parameter (User cancelled OAuth process)
        if (error != null) {
            response.sendRedirect(customIp); // Redirect to landing page
        } 

        // Proceed with OAuth
        if (authorizationCode != null) {
            // Instantiate SpotifyApi object
            SpotifyApi spotify = spotifyConfiguration.getSpotifyObject();

            // Build Authorization Code Request using the authorization code passed into the redirect URI
            AuthorizationCodeRequest authorizationCodeRequest = spotify.authorizationCode(authorizationCode).build();
            User user = null;
            
            try {
                // Executes the build request and retireves the user's authorization credential data
                final AuthorizationCodeCredentials authorizationCredentials = authorizationCodeRequest.execute();

                // Sets the SpotifyApi instance's access and refresh token from AuthorizationCodeCredentials entity
                spotify.setAccessToken(authorizationCredentials.getAccessToken());
                spotify.setRefreshToken(authorizationCredentials.getRefreshToken());

                // Gets the current Spotify user that has granted authorization
                final GetCurrentUsersProfileRequest getCurrentUsersProfile = spotify.getCurrentUsersProfile().build();
                user = getCurrentUsersProfile.execute(); 

                // Sets the access and refresh token and reference id of the Spotify user instance in database
                spotifyUserService.insertOrUpdateSpotifyUser(user, authorizationCredentials.getAccessToken(), authorizationCredentials.getRefreshToken());
                // Redirects user with reference ID as a query parameter
                response.sendRedirect(customIp + "/home?id="+user.getId());
            }
            catch (Exception e) {
                System.out.println("Exception occured while getting user code: " + e);
            }
        }
    }

    /**
     * Endpoint verifies that the user reference is was successfully retrieved.
     * 
     * @param userId
     * @return userId - The Spotify reference ID for the user.
     */
    @GetMapping(value = "home")
    public String home(@RequestParam String userId) {
        try {
            return userId;
        } catch (Exception e) {
            System.out.println("Exception occured while landing to home page: " + e);
        }
        return null;
    }

    /**
     * Handles the creation of a Spotify playlist based on artists selected by the user.
     * 
     * @param userId The Spotify refrence ID for the user.
     * @param playlistName The user's playlist name.
     * @param artistNames The names of the artists selected by the user.
     * @return ResponseEntity<String> - The HTTP request status code indicating if the 
     *                                  playlist was successfully created.
     */
    @PostMapping("add-playlist/{userId}/{playlistName}")
    public ResponseEntity<String> createSpotifyPlaylist(@PathVariable String userId,
                                                        @PathVariable String playlistName, 
                                                        @RequestBody List<String> artistNames) {
        
        // Finds Spotify user with reference ID
        SpotifyUser userDetails = spotifyUserRepository.findByRefId(userId);
        SpotifyApi spotify = spotifyConfiguration.getSpotifyObject();
        spotify.setAccessToken(userDetails.getAccessToken());
        spotify.setRefreshToken(userDetails.getRefreshToken());
        // Scales the number of tracks per artist to add to the playlist
        Double trackLimit = Math.floor(85.0 / (artistNames.size()));

        // Validate playlist name
        if (playlistName == null || playlistName.matches("\\d+")) {
            return ResponseEntity.badRequest().body("Invalid playlist name");
        }

        try {
            // Create a new playlist with user inputted playlist name
            final Playlist newPlaylist = spotify.createPlaylist(userId, playlistName.toString())
                                                .public_(true)
                                                .collaborative(false)
                                                .build().execute();

            // Instantiate the list of Spotify track URIs
            Set<String> trackUriSet = new LinkedHashSet<>();
            // Get top tracks of each artist
            for (String artistName : artistNames) {
                // Retrieves the first search result for the artist name
                Paging<Artist> searchArtistsRequest = spotify.searchArtists(artistName).limit(5).build().execute();
                Artist artist = null;
                if (searchArtistsRequest.getTotal() == 0) { // Skips this iteration if artist could not be found on Spotify
                    continue;
                } else {
                    artist = searchArtistsRequest.getItems()[0];
                }
                // Retrieves the artist's top 5 track URIs
                GetArtistsTopTracksRequest topTracksRequest = spotify.getArtistsTopTracks(artist.getId(), CountryCode.US).build();
                Track[] topTracks = topTracksRequest.execute();
                for (int i = 0; i < Math.min(topTracks.length, trackLimit); i++) {
                    trackUriSet.add(topTracks[i].getUri());
                } 
            }
            // Converts the list of track URIs to an array
            String[] uris = trackUriSet.toArray(new String[0]);

            // Requests Spotify to add all tacks to the playlist created by the user
            AddItemsToPlaylistRequest addItemsRequest = spotify.addItemsToPlaylist(newPlaylist.getId(), uris).build();
            addItemsRequest.execute();
            return ResponseEntity.ok("Playlist Created Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error Creating Playlist");
        }

    }
}

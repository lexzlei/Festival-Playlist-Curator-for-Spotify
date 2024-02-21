package com.lexlei.festivalplaylistapp.Controllers;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lexlei.festivalplaylistapp.Configuration.SpotifyConfiguration;
import com.lexlei.festivalplaylistapp.Repositories.SpotifyUserRepository;
import com.lexlei.festivalplaylistapp.Service.SpotifyUserService;

import jakarta.servlet.http.HttpServletResponse;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {
    @Value("${custom.ip}")
	private String customIp;

	@Autowired
	private SpotifyConfiguration spotifyConfiguration;

    @Autowired
    private SpotifyUserService spotifyUserService;
	
	@Autowired
	private SpotifyUserRepository spotifyUserRepository;

    @GetMapping("login")
    public String spotifyLogin() {
        SpotifyApi spotify = spotifyConfiguration.getSpotifyObject();
        
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotify.authorizationCodeUri()
                .scope("user-read-private user-read-email user-read-private playlist-modify-private playlist-modify-public")
                .show_dialog(true)
                .build();
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    @GetMapping(value = "get-user-code")
    public void getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        SpotifyApi spotify = spotifyConfiguration.getSpotifyObject();

        // Instantiate Authorization Code Request 
        AuthorizationCodeRequest authorizationCodeRequest = spotify.authorizationCode(userCode).build();
        User user = null;
        
        try {
            final AuthorizationCodeCredentials authorizationCode = authorizationCodeRequest.execute();

            // Gets access token and refresh token from AuthorizationCodeCredentials entity
            spotify.setAccessToken(authorizationCode.getAccessToken());
            spotify.setRefreshToken(authorizationCode.getRefreshToken());

            final GetCurrentUsersProfileRequest getCurrentUsersProfile = spotify.getCurrentUsersProfile().build();
            user = getCurrentUsersProfile.execute(); // gets user information from Spotify User class

            spotifyUserService.insertOrUpdateSpotifyUser(user, authorizationCode.getAccessToken(), authorizationCode.getRefreshToken());
        }
        catch (Exception e) {
            System.out.println("Exception occured while getting user code: " + e);
        }
        response.sendRedirect(customIp + "/home?id="+user.getId());
        //response.sendRedirect(customIp + "/api/festival/search?id="+user.getId());
    }

    @GetMapping(value = "home")
    public String home(@RequestParam String userId) {
        try {
            return userId;
        } catch (Exception e) {
            System.out.println("Exception occured while landing to home page: " + e);
        }
        return null;
    }
    // Spotify Create Playlist
    //@GetMapping("")
    // Spotify Artist Top Songs
    // Spotify Add Songs to Playlist
}

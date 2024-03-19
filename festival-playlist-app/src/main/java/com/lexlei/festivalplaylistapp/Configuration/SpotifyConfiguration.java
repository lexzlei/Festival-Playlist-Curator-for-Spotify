package com.lexlei.festivalplaylistapp.Configuration;

import java.net.URI;

import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
/**
 * SpotifyConfiguration class
 * 
 * This class provides the configuration settings for the Spotify API.
 * It sets up the Spotify API client with the necessary credentials and the
 * redirect URL.
 */
@Service
public class SpotifyConfiguration {
    private String clientID = "dfeb4f7138d94975801924fe101a5c09"; // Spotify client ID
    private String clientSecret = "fddc9a0f00be4762b7d1084208c150a9"; // Spotify client secret

    /**
     * Creates and returns a configured SpotifyAPI Object for use throughout the backend.
     * 
     * @return SpotifyAPI - The configured Spotify API object.
     */
    public SpotifyApi getSpotifyObject() {
        // URI where Spotify will redirect the user after successful authentication
        URI redirectURL = SpotifyHttpManager.makeUri("https://www.festbeatsapp.com/api/spotify/get-user-code");

        // Create new instance of SpotifyApi object using it's builder pattern. 
        return new SpotifyApi 
                .Builder()
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectURL)
                .build();
    }
}

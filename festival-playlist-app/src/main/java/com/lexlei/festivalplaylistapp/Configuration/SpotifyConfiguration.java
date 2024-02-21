package com.lexlei.festivalplaylistapp.Configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;

@Service
public class SpotifyConfiguration {
    public String clientID = "dfeb4f7138d94975801924fe101a5c09";
    public String clientSecret = "fddc9a0f00be4762b7d1084208c150a9";
    //public String createPlaylistUrl = "https://api.spotify.com/v1/users/{user_id}/playlists";
    @Value("${redirect.server.ip}")
    private String customIp;

    public SpotifyApi getSpotifyObject() {
        URI redirectURL = SpotifyHttpManager.makeUri(customIp + "/api/spotify/get-user-code");
        //System.out.println(redirectURL);
        return new SpotifyApi
                .Builder()
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectURL)
                .build();
    }
}

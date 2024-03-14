package com.lexlei.festivalplaylistapp.Service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexlei.festivalplaylistapp.Models.SpotifyUser;
import com.lexlei.festivalplaylistapp.Repositories.SpotifyUserRepository;

import se.michaelthelin.spotify.model_objects.specification.User;

/**
 * This service class handles creating a new SpotifyUser entity.
 */
@Service
public class SpotifyUserService {
    
    @Autowired
    private SpotifyUserRepository spotifyUserRepository;

    /**
     * Handles creating or updating a Spotify user entity.
     * 
     * @param user The Spotify User object.
     * @param accessToken The Spotify user's access token for authorizations.
     * @param refreshToken The Spotify user's refresh token.
     * @return SpotifyUser - The configures SpotifyUser entity.
     */
    public SpotifyUser insertOrUpdateSpotifyUser(User user, String accessToken, String refreshToken) {
        SpotifyUser spotifyUser = spotifyUserRepository.findByRefId(user.getId());

        if(Objects.isNull(spotifyUser)) {
            spotifyUser = new SpotifyUser();
        }
        spotifyUser.setUserName(user.getDisplayName());
        //spotifyUser.setEmailId(user.getEmail());
        spotifyUser.setAccessToken(accessToken);
        spotifyUser.setRefreshToken(refreshToken);
        spotifyUser.setRefId(user.getId());
        return spotifyUserRepository.save(spotifyUser);
    }
}

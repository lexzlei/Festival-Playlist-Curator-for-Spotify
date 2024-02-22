package com.lexlei.festivalplaylistapp.Service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexlei.festivalplaylistapp.Models.SpotifyUser;
import com.lexlei.festivalplaylistapp.Repositories.SpotifyUserRepository;

import se.michaelthelin.spotify.model_objects.specification.User;

@Service
public class SpotifyUserService {
    
    @Autowired
    private SpotifyUserRepository spotifyUserRepository;

    public SpotifyUser insertOrUpdateSpotifyUser(User user, String accessToken, String refreshToken) {
        SpotifyUser spotifyUser = spotifyUserRepository.findByRefId(user.getId());

        if(Objects.isNull(spotifyUser)) {
            spotifyUser = new SpotifyUser();
        }
        //spotifyUser.setUserName(user.getDisplayName());
        //spotifyUser.setEmailId(user.getEmail());
        spotifyUser.setAccessToken(accessToken);
        spotifyUser.setRefreshToken(refreshToken);
        spotifyUser.setRefId(user.getId());
        return spotifyUserRepository.save(spotifyUser);
    }
}

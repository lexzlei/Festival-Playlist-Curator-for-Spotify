package com.lexlei.festivalplaylistapp.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.SpotifyUser;

/**
 * SpotifyUserRepository
 * 
 * Handles CRUD operations for SpotifyUser entities.
 */
public interface SpotifyUserRepository extends CrudRepository<SpotifyUser, Integer> {

    /**
     * Finds the Spotify user by refernece ID.
     * 
     * @param refId The reference ID associated with a Spotify user.
     * @return SpotifyUser - The SpotifyUser entity if found.
     */
    SpotifyUser findByRefId(String refId); // Extends CrudRepository
}

package com.lexlei.festivalplaylistapp.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.SpotifyUser;
public interface SpotifyUserRepository extends CrudRepository<SpotifyUser, Integer> {
    SpotifyUser findByRefId(String refId);
}

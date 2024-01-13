package com.lexlei.festivalplaylistapp.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.Playlist;

/**
 * Handles Playlist data CRUD operations (provided by spring).
 */
public interface PlaylistRepository extends CrudRepository<Playlist, Integer>{

}

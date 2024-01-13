package com.lexlei.festivalplaylistapp.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.Song;

/**
 * Handles user data CRUD operations.
 */
public interface SongRepository extends CrudRepository<Song, Integer> {

}

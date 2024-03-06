/**
 * THIS FILE IS CURRENTLY UNUSED UNTIL FURTHER FUNCTIONALITY IMPLEMENTATION.
 */

package com.lexlei.festivalplaylistapp.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.Song;

/**
 * Handles song CRUD operations.
 */
public interface SongRepository extends CrudRepository<Song, Integer> {

}

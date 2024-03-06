/**
 * THIS FILE IS CURRENTLY UNUSED UNTIL FURTHER FUNCTIONALITY IMPLEMENTATION.
 */

package com.lexlei.festivalplaylistapp.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.PlaylistSong;
import com.lexlei.festivalplaylistapp.Models.PlaylistSongId;

/**
 * Handles PlaylistSong data CRUD operations.
 */
public interface PlaylistSongRepository extends CrudRepository<PlaylistSong, PlaylistSongId> {

}
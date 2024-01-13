package com.lexlei.festivalplaylistapp.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.Artist;

/**
 * Handles artist data CRUD operations.
 */
public interface ArtistRepository extends CrudRepository<Artist, Integer> {

}

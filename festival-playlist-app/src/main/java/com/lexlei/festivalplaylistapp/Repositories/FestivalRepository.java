package com.lexlei.festivalplaylistapp.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.lexlei.festivalplaylistapp.Models.Festival;

/**
 * Handles festival data CRUD operations.
 */
public interface FestivalRepository extends CrudRepository<Festival, Integer> {

}
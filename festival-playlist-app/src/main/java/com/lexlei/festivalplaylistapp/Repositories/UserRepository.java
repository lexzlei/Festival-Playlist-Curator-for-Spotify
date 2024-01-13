package com.lexlei.festivalplaylistapp.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lexlei.festivalplaylistapp.Models.User;

/**
 * Handles user data CRUD operations.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
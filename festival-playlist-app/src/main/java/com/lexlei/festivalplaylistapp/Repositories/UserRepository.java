/**
 * THIS FILE IS CURRENTLY UNUSED UNTIL FURTHER FUNCTIONALITY IMPLEMENTATION.
 */

package com.lexlei.festivalplaylistapp.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.lexlei.festivalplaylistapp.Models.User;

/**
 * Handles user data CRUD operations.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

}
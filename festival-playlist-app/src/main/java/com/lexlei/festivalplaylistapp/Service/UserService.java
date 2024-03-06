/**
 * THIS FILE IS CURRENTLY UNUSED UNTIL FURTHER FUNCTIONALITY IMPLEMENTATION.
 */
package com.lexlei.festivalplaylistapp.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lexlei.festivalplaylistapp.Models.User;
import com.lexlei.festivalplaylistapp.Repositories.UserRepository;

/**
 * Service class for handling User entity.
 * This class includes methods for creating, updating, deleting,
 * and fetching user details and their playlists.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     * 
     * @param user The user to create.
     * @return User - The created user.
     */
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users.
     * 
     * @return List<User> - A list of all users.
     */
    public List<User> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(),false)
                            .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by ID.
     * @param userId the ID of the user to retrieve.
     * @return an Optional containing the user if found.
     */
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Updates an existing user.
     * 
     * @param user The user to update.
     * @return User - The updated user.
     */
    public User updateUser(Integer id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> {
                    user.setUserID(id);
                    return userRepository.save(user);
                });
    }
    
    /**
     * Deletes a user by ID.
     * 
     * @param userId The ID of the user to delete.
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}

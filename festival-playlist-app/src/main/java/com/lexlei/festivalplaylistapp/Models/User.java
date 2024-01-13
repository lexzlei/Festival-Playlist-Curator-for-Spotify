/**
 * Provides classes for managing user information in the Festival Playlist Curator App.
 * This package includes classes to handle user interactions, festival details, artists, 
 * songs, and playlists.
 */

package com.lexlei.festivalplaylistapp.Models;
import jakarta.persistence.Entity;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a user in the Festival Playlist Curator Application.
 * This class stores information about a user, including their first and last name,
 * email, and password.
 * It also links the user and playlists they have created.
 */
@Entity
@Table(name = "users", schema = "festival_app")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID; // Unique primary key identifier. 
    private String firstName; // First name of user.
    private String lastName; // Last name of user.
    private String email; // Email of user.
    private String password; // Password of user account.

    @OneToMany(mappedBy = "users")
    private List<Playlist> playlists; // List of playlists made by the user

    /**
     * Gets the unique identifier for the user.
     * @return the user ID.
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets the user ID.
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Gets the first name of the user.
     * @return the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * @return the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the user.
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the playlists created by this user.
     * @return the playlists.
     */
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Sets the playlists created by this user.
     */
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}

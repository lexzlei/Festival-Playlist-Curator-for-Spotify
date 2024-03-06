/**
 * Provides classes for managing song information in the Festival Playlist Curator App.
 */
package com.lexlei.festivalplaylistapp.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * SpotifyUser Class
 * 
 * This class represents a Spotify user and stores information
 * about the user's ID, access token, refresh token, and reference ID.
 */
@Entity
@Table(name = "spotify_user_details")
@Getter
@Setter
public class SpotifyUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId; // Auto generated unique user ID
    @Column(name = "access_token")
    private String accessToken; // Unique access token for Spotify Credentials
    @Column(name = "refresh_token")
    private String refreshToken; // Unique refresh token for Spotify Credentials
    @Column(name = "ref_id")
    private String refId; // Unique Spotify user reference ID

}

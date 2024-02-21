/**
 * Provides classes for managing playlist information in the Festival Playlist Curator App.
 * This package includes classes to handle user interactions, festival details, artists, 
 * and songs.
 */
package com.lexlei.festivalplaylistapp.Models;
import jakarta.persistence.Entity;
import java.util.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Represents a playlist in the Festival Playlist Curator Application.
 * This class stores information about a playlist including
 * it's name, the date it was created, and the user who made it.
 * It links a song and playlist.
 */

@Entity
@Table(name = "playlists", schema = "festival_app")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistID; // The unique primary key identifier of this playlist.
    private String playlistName; // the name of this playlist.
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    /**
     * Gets the playlist ID.
     * @return the playlist ID.
     */
    public Integer getPlaylistID() {
        return playlistID;
    }

    /**
     * Sets the playlist ID.
     */
    public void setPlaylistID(Integer playlistID) {
        this.playlistID = playlistID;
    }

    /**
     * Gets the playlist name.
     * @return the playlist name.
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * Sets the playlist name.
     */
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Gets the playlist creation date.
     * @return the playlist creation date.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets the playlist creation date.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Gets the user who is the owner of this playlist.
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user.
     */
    public void setUser(User user) {
        this.user = user;
    }

}

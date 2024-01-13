/**
 * Provides classes for managing the composite primary key of PlaylistSongs
 * in the Festival Playlist Curator App. This package includes classes to 
 * handle user interactions, festival details, artists, songs, and playlists.
 */
package com.lexlei.festivalplaylistapp.Models;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * PlaylistSongID Embeddable class denote a compository primary key, 
 * it does not have an independent existance. 
 * PlaylistSongID Shares the identity of Playlist and Song
 */
@Embeddable
public class PlaylistSongId implements Serializable{
    
    @Column(name = "playlistID") // Specifies the mapped playlistID column from playlists
    private Integer playlistID; // The unique primary key identifier for a playlist.
    @Column(name = "songID") // Specifies the mapped songID column from songs.
    private Integer songID; // The unique primary key identifier for a song.

    // No-arg PlaylistSongID default constructor (required for JPA)
    public PlaylistSongId() {
    }

    // Parameterized PlaylistSongId constructor
    public PlaylistSongId(Integer playlistID, Integer songID) {
        this.playlistID = playlistID;
        this.songID = songID;
    }

    /**
     * Gets the playlist ID for this instance.
     * @return the playlistID.
     */
    public Integer getPlaylistID() {
        return playlistID;
    }

    /**
     * Gets the song ID for this instance.
     * @return the songID.
     */
    public Integer getSongID() {
        return songID;
    }

    /**
     * Sets the playlist ID.
     */
    public void setPlaylistID(Integer playlistID) {
        this.playlistID = playlistID;
    }

    /**
     * Sets the song ID.
     */
    public void setSongID(Integer songID) {
        this.songID = songID;
    }

    // Override Equals method
    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        PlaylistSongId that = (PlaylistSongId) o;
        return Objects.equals(playlistID, that.playlistID) && Objects.equals(songID, that.songID);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(playlistID, songID);
    }
}

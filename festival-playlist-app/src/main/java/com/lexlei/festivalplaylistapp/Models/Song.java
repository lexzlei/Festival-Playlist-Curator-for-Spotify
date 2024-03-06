/**
 * Provides classes for managing song information in the Festival Playlist Curator App.
 * This package includes classes to handle user interactions, festival details, artists, 
 * songs, and playlists.
 * 
 * THIS CLASS IS CURRENTLY UNUSED UNTIL FURTHER FUNCTIONALITY IMPLEMENTATION.
 */
package com.lexlei.festivalplaylistapp.Models;
import jakarta.persistence.Entity;
import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

 /**
 * Represents a song in the Festival Playlist Curator Application.
 * This class stores information about a song including the song name and artist.
 * It also links the artist performing the song.
 */
 @Entity
 @Table(name = "songs", schema = "festival_app")
public class Song {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songID; // The unique primary key identifier.
    private String songName; // The song name.
    
    @ManyToOne
    @JoinColumn(name = "artistID")
    private Artist artist; // The artist who created the song.

    @OneToMany(mappedBy = "song")
    private Set<PlaylistSong> playlistSongs; // The playlistsong.

    /**
     * Gets the unique primary key identifier for the song.
     * @return the primary key.
     */ 
    public Integer getSongID() {
        return songID;
    }   

    /**
     * Sets the unique primary key identifier.
     */
    public void setSongID(Integer songID) {
        this.songID = songID;
    }

    /**
     * Gets the song name.
     * @return the song name.
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Sets the song name.
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Gets the artist who created the song.
     * @return the artist.
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets the artist.
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets the playlists this song appears on.
     * @return the playlists.
     */
    public Set<PlaylistSong> getPlaylistSongs() {
        return playlistSongs;
    }

    /**
     * Sets the playlists.
     */
    public void setPlaylistSongs(Set<PlaylistSong> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}

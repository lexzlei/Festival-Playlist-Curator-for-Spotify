/**
 * Provides classes for managing artist information in the Festival Playlist Curator App.
 * This package includes classes to handle user interactions, festival details, artists, 
 * songs, and playlists.
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
 * Represents an artist in the Festival Playlist Curator Application.
 * This class stores information about an artist, including their name
 * and associated festival.
 * It also links the festival the artist is performing and the songs they created.
 */
@Entity
@Table(name = "artists", schema = "festival_app")
public class Artist {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistID; // Unique primary key identifier for this artist.
    private String name; // Artist name.
    
    @ManyToOne
    @JoinColumn(name = "festivalID") // Stores the ID primary key of the associated festival.
    private Festival festival; // The foreign key identifier for the artist's associated festival

    @OneToMany(mappedBy = "artist")
    private Set<Song> songs; // Songs associated to this artist.

    /**
     * Gets the unique primary key identifier for the artist.
     * @return the primary key.
     */
    public Integer getArtistID() {
        return artistID;
    }

    /**
     * Sets the primary key identifier.
     */
    public void setArtistID(Integer artistID) {
        this.artistID = artistID;
    }

    /**
     * Gets the name of the artist.
     * @return artist name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the artist.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the festival associated with the artist.
     * @return the festival.
     */
    public Festival getFestival() {
        return festival;
    }

    /**
     * Sets the festival.
     */
    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    /**
     * Gets the songs associated with this artist.
     * @return the songs.
     */
    public Set<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs associated with this artist.
     */
    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}

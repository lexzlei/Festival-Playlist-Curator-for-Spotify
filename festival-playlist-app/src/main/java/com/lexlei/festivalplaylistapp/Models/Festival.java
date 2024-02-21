/**
 * Provides classes for managing festival information in the Festival Playlist Curator App.
 * This package includes classes to handle user interactions, festival details, artists, 
 * songs, and playlists.
 */
package com.lexlei.festivalplaylistapp.Models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a festival in the Festival Playlist Curator Application.
 * This class stores information about a festival, including its name, location, and year.
 * It also links the festival and the artists performing.
 */

@Entity
@Table(name="festivals", schema="festival_app")
public class Festival {
    // class body
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer festivalID; // Unique primary key identifier
    private String festivalName; // Name of festival
    private String location; // Location of the festival
    private Integer year; // Year the festival is taking place

    //@OneToMany(mappedBy = "festival")
    private List<String> artists; // Set of artists performing at the festival

    /**
     * Gets the unique identifier of the festival.
     * @return the festival ID.
     */
    public Integer getFestivalID() {
        return festivalID;
    }

    /**
     * Sets the unique identifier of the festival.
     */
    public void setFestivalID(Integer festivalID) {
        this.festivalID = festivalID;
    }

    /**
     * Gets the festival name.
     * @return the festival name.
     */
    public String getfestivalName() {
        return festivalName;
    }

    /**
     * Sets the festival name.
     */
    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    /**
     * Gets the location of the festival.
     * @return the festival location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the festival.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the year the festival will take place.
     * @return the year of the festival
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Sets the year of the festival.
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Gets the artists performing.
     * @return artists.
     */
    public List<String> getArtists() {
        return artists;
    }

    /**
     * Sets artists.
     */
    public void setArtists(List<String> artists) {
        this.artists = artists;
    }
}

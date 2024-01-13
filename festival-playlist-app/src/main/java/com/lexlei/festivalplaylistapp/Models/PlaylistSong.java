/**
 * Provides classes for managing playlist song information in the Festival Playlist Curator App.
 * This package includes classes to handle user interactions, festival details, artists, 
 * songs, and playlists.
 */

package com.lexlei.festivalplaylistapp.Models;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

 /**
 * Represents a song specific to a unique playlist in the Festival Playlist Curator Application.
 * This is a junction class to handing the many-many relationship between playlist and song.
 * It links a song and playlist.
 */
@Entity
@Table(name = "playlist_songs", schema = "festival_app")
public class PlaylistSong {
    /**
     * The embedded ID representing the composite primary key of the PlaylistSong entity.
     * This field is mapped to the composite key class 'PlaylistSongId', which contains
     * references to the associated playlist and song identifiers. The @EmbeddedId annotation
     * is used to indicate that this field is serving as a composite primary key for the entity.
     */
    @EmbeddedId
    private PlaylistSongId PlaylistSongID; // The unique key for this playlistsong entity.

    @ManyToOne
    @MapsId("playlistID") // Maps the playlistID column to the respective playlist.
    @JoinColumn(name = "playlistID") // Stores the ID of the corresponding playlist.
    private Playlist playlist; // The playlist associated with this object. 

    @ManyToOne
    @MapsId("songID") // Maps the songID column to the respective song.
    @JoinColumn(name = "songID") // Stores the ID of the corresponding song.
    private Song song; // The song associated with this object,

    /**
     * Gets the playlist of which the song is on.
     * @return the playlist.
     */
    public Playlist getPlaylist() {
        return playlist;
    }

    /**
     * Sets the playlist.
     */
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * Gets the song on this playlist.
     * @return the song.
     */
    public Song getSong() {
        return song;
    }

    /**
     * Sets the song.
     */
    public void setSong(Song song) {
        this.song = song;
    }
}

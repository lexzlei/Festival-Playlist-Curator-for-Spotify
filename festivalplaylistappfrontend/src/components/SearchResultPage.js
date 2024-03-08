/**
 * SearchResultPage.js
 * 
 * This module provides the functionality of the search result page.
 */
import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './SearchResultPage.css';

/**
 * SearchResultPage Component
 * 
 * Renders the search results page after the user searches for a festival.
 * The page displays the festival's name, location, and year, and lists all 
 * performing artists. Users can select artists to include in a Spotify Playlist
 * and then generate a playlist.
 */
function SearchResultPage() {
    const location = useLocation();
    const { festivalData, userId } = location.state; // Extracting the festival data and user id from the 'state' object of the current route
    const [selectedArtists, setSelectedArtists] = useState([]);
    const [playlistName, setPlaylistName] = useState("");
    const navigate = useNavigate();

    /**
     * Toggles an artist's selection status.
     * Adds or removes the artist from the selectedArtists array.
     * 
     * @param {string} artist - The name of the artist to toggle.
     */
    const handleArtistSelect = (artist) => {
        setSelectedArtists(prevArtists => {
            // Check if the artist is already selected
            if (prevArtists.includes(artist)) {
                // Artist is already selected, remove them from list
                return prevArtists.filter(a => a !== artist);
            } else {
                // Artist is not already selected, add them to the list
                return [...prevArtists, artist];
            }
        });
    };

    /**
     * Handles the playlist creation process.
     * Sends a POST request to the backend with the selected artists and desired
     * playlist name. Redirects the user to a confirmation page upon successfull
     * playlist creation.
     */
    const handleCreatePlaylist = async () => {
        // Call to create playlist with selected artists
        console.log("UserID:", userId);
        const response = await fetch(`https://www.festbeatsapp.com/api/spotify/add-playlist/${userId}/${playlistName}`, {
            method: 'POST',
            body: JSON.stringify(selectedArtists),
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            navigate('/playlist-created', {state: {userId: userId}});
        } else {
            alert('Failed to create playlist.');
        }
    };

    return (
        <div className="search-result">
            <h1>{festivalData?.festivalName} - {festivalData?.location}</h1>
            <h2>1. Select artists.<br/> 
                2. Give your new playlist a name.<br/> 
                3. Click "GENERATE PLAYLIST" button.
            </h2>
            <div className='artist-buttons-container'>
                {festivalData?.artists.map((artist, index) => (
                    <button 
                    key={index} 
                    className={`artist-button ${selectedArtists.includes(artist) ? 'selected' : ''}`}
                    onClick={() => handleArtistSelect(artist)}
                    >
                        {artist}
                    </button>
                ))}
            </div>
            <div>
                <input type="text" className="playlist-name-input" placeholder="Playlist Name" onChange={(e) => setPlaylistName(e.target.value)} />
                <button className="generate-playlist-button" onClick={handleCreatePlaylist}>GENERATE PLAYLIST</button>
            </div>
        </div>
    );
}

export default SearchResultPage;

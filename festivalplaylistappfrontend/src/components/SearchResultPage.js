// SearchResultsPage.js
import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './SearchResultPage.css';

function SearchResultPage() {
    const location = useLocation();
    const { festivalData, userId } = location.state;
    const [selectedArtists, setSelectedArtists] = useState([]);
    const [playlistName, setPlaylistName] = useState("");
    const navigate = useNavigate();

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

    const handleCreatePlaylist = async () => {
        // Call to create playlist with selected artists
        console.log("UserID:", userId);
        const response = await fetch(`http://localhost:8080/api/spotify/add-playlist/${userId}/${playlistName}`, {
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

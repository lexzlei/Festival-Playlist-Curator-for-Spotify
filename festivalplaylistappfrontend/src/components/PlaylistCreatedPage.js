/**
 * PlaylistCreatedPage.js
 * 
 * This module renders the confirmation screen after a playlist has
 * been successfully created.
 */
import React from 'react';
import {useNavigate, useLocation } from 'react-router-dom';
import './PlaylistCreatedPage.css';

/**
 * PlaylistCreatedPage Component
 * 
 * Renders the confirmation page after a user has successfully created
 * a Spotify playlist. Provide a button for the user to navigate back
 * to the home page if desired.
 * @returns 
 */
function PlaylistCreatedPage() {
    const navigate = useNavigate();
    const location = useLocation();
    const { userId } = location.state;
    /**
     * Navigates the user to return to the home page.
     */
    const returnHome = async () => {
        navigate(`/home/?id=${userId}`);
    }

    return (
        <div className='playlist-created-page'>
            <h1>Playlist Was Successfully Created!</h1>
            <p>Please check your Spotify account to verify.</p>
            <button className='return-home-button' onClick={returnHome}>Return Home</button>
        </div>
    )
}

export default PlaylistCreatedPage;
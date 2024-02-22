/* Playlist created page */
import React from 'react';
import {useNavigate, useLocation } from 'react-router-dom';
import './PlaylistCreatedPage.css';

function PlaylistCreatedPage() {
    const navigate = useNavigate();
    const location = useLocation();
    const { userId } = location.state;
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
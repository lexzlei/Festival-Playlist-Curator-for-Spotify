/**
 * SpotifyLogin.js
 * 
 * This module provides the functionality for the Spotify User Authentication.
 * It renders a login button and handles the Spotify OAuth flow with Spotify's API
 */

import React from "react";
import "./SpotifyLogin.css"
import logo from '../FestbeatsLogo.png';

/**
 * SpotifyLogin Component
 * 
 * Renders the login page for the Music Festival Playlist Creator app.
 * Includes a header with a logo, app name, and instruction for the user,
 * and has a button to initiate the Spotify OAuth process.
 */
function SpotifyLogin() {
    /**
     * Initiates the Spotify login process.
     * Makes a GET request to the backend which redirects to the Spotify login page.
     */
    const getSpotifyUserLogin = () => {
        //fetch("http://localhost:8080/api/spotify/login")
        fetch("https://www.festbeatsapp.com/api/spotify/login")
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network response was not ok' + response.statusText);
            }
            return response.text();
        })
        .then(responseText => {
            console.log("Response received", responseText);
            window.location.replace(responseText); // Redirects to Spotify's login page
        })
        .catch(error => {
            console.error('Error fetching data: ', error); // Logs errors
        })
    };

    return (
        <div className="login">
            <header className="login-header">
                <img src={logo} alt="Logo" />
                <h1>Music Festival Playlist Creator</h1>
                <p>Please login to Spotify below to continue:</p>
            </header>
            <button onClick={getSpotifyUserLogin}>Login With Spotify</button>
        </div>
    )
}

export default SpotifyLogin; //Exports the component for use in other parts of the app
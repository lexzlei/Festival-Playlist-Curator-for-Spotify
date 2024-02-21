import React from "react";
import "./SpotifyLogin.css"

function SpotifyLogin() {
    const getSpotifyUserLogin = () => {
        fetch("http://localhost:8080/api/spotify/login")
        .then((response) => response.text())
        .then(response => {
            console.log("Response received", response);
            window.location.replace(response);
        })
        .catch(error => {
            console.error('Error fetching data: ', error);
        })
    };

    return (
        <div className="login">
            <header className="login-header">
                <h1>Welcome to the Music Festival Playlist Creator!</h1>
                <p>Please login to Spotify below to continue:</p>
            </header>
            <button onClick={getSpotifyUserLogin}>LOGIN WITH SPOTIFY</button>
        </div>
    )
}

export default SpotifyLogin
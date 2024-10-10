/**
 * Home.js
 * 
 * This module provides the functionaly for the home page of the web app.
 * It renders the text input fields allowing the user to search for a festival 
 * by name and year. The user is navigated to the search result page upon
 * submitting a valid festival and pressing the search button.
 */
import React, {useState} from "react";
import "./Home.css"
import { useLocation, useNavigate } from 'react-router-dom';

/**
 * Home Component
 * 
 * Handles the user input for their festival search.
 * Navigates user to the search result page.
 */
function Home() {
    const [festivalName, setFestivalName] = useState("");
    const [year, setYear] = useState("");
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const userId = queryParams.get('id');
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(false);
    /**
     * Initiates the festival lineup search process.
     * Fetches the festival data from the backend, scraping the website "Festival Wizard".
     * Navigates to the search result page if the festival is found. Displays alerts if
     * the user enters an invalid festival.
     */
    const handleSearch = async () => {
        // Check if user has not filled out the fields
        if (!festivalName.trim() || !year.trim()) {
            alert("Please enter a festival name and year.");
            return;
        }
        try {
            setIsLoading(true);
            // Redirect to search results page with query parameters
            const url = //`https://www.festbeatsapp.com/api/festival/search/${festivalName}/${year}`; 
            `http://localhost:8080/api/festival/search/${festivalName}/${year}`;  
            const response = await fetch(url, {
                method: 'POST',
            });
            // Check if user entered invalid festival
            if (!response.ok) {
                if (response.status == 404) {
                    alert("No festival found. Please try again.");
                } else {
                    alert("No festival found. Please try again.");
                }
                return;
            }
            // Converting response to JSON format
            const data = await response.json();
            if(!data.isFound) {
                alert("No festival found. Please try again.");
            } else {
                // Pass the festival data to the search result page
                navigate('/search-result', {state: {festivalData: data, userId: userId}});
            } 
        } catch (error) {
            console.error("Error:", error);
        }
        setIsLoading(false);
    };

    return (
        <div className="home-page">
            <header className="home-page-header">
                <h1>Search Festival:</h1>
            </header>
            <input type="text" className="search-input" placeholder="Music Festival Name" onChange={(e) => setFestivalName(e.target.value)} />
            <input type="text" className="search-input" placeholder="Year" onChange={(e) => setYear(e.target.value)} />
            <button className="search-button" onClick={handleSearch} disabled={isLoading}>Search</button>
            <footer>&copy; 2024 Festbeats</footer>
        </div>
    );
}

export default Home
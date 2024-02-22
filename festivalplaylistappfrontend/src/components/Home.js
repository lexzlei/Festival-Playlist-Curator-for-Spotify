import React, {useState} from "react";
import "./Home.css"
import { useLocation, useNavigate } from 'react-router-dom';

function Home() {
    const [festivalName, setFestivalName] = useState("");
    const [year, setYear] = useState("");
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const userId = queryParams.get('id');
    const navigate = useNavigate();
    const handleSearch = async () => {
        // Check if user has not filled out the fields
        if (!festivalName.trim() || !year.trim()) {
            alert("Please enter a festival name and year.");
            return;
        }
        try {
            // Redirect to search results page with query parameters
            const url = `http://localhost:8080/api/festival/search/${festivalName}/${year}`; 
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
    };

    return (
        <div className="home-page">
            <header className="home-page-header">
                <h1>Search Festival:</h1>
            </header>
            <input type="text" className="search-input" placeholder="Music Festival Name" onChange={(e) => setFestivalName(e.target.value)} />
            <input type="text" className="search-input" placeholder="Year" onChange={(e) => setYear(e.target.value)} />
            <button className="search-button" onClick={handleSearch}>Search</button>
        </div>
    );
}

export default Home
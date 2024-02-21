// SearchResultsPage.js
import React from 'react';
import { useLocation } from 'react-router-dom';
import './SearchResultPage.css';

function SearchResultPage() {
    const location = useLocation();
    const festivalData = location.state?.festivalData;

    return (
        <div className="search-result">
            <h1>{festivalData?.festivalName} - {festivalData?.location}</h1>
            {festivalData?.artists.map((artist, index) => (
                <button key={index}>{artist}</button>
            ))}
        </div>
    );
}

export default SearchResultPage;

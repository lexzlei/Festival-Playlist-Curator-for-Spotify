// Header.js
import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../FestbeatsLogo.png'; // Adjust the path as needed
import './Header.css'

/**
 * Header component
 * 
 * Renders the header that is located at the top of each page.
 * Includes the web app logo which navigates the user back to 
 * the login page if clicked.
 */
const Header = () => {
  return (
    <div className='header-container'>
        <header>
        <Link to="/">
            <img src={logo} alt="Logo" />
        </Link>
        </header>
    </div>
  );
};

export default Header;
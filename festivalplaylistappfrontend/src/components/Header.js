// Header.js
import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../FestbeatsLogo.png'; // Adjust the path as needed
import './Header.css'

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
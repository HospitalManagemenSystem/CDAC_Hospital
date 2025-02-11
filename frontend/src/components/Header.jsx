// Header.js
import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../assets/LOGO.png';
import './Header.css';

const Header = () => {
  return (
    <nav className="custom-navbar">
      <div className="container">
        <Link className="navbar-brand" to="/">
          <img src={logo} alt="AIIMS Hospital" className="hospital-logo" />
          <span>CDAC Hospital</span>
        </Link>
        <div className="nav-links">
          <Link to="/" className="nav-link">
            Home
          </Link>
          <Link to="/about" className="nav-link">
            About Us
          </Link>
          <Link to="/contact" className="nav-link">
            Contact Us
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Header;

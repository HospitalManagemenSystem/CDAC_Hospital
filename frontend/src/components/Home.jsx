import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';
const Home = () => {
  return (
    <div className="home">
      {/* Hero Section */}
      <div className="hero-section">
        <div className="hero-content">
          <h1 className="hero-title">Welcome to CDAC Hospital</h1>
          <p className="hero-description">
            This platform for booking medical consultations with specialist
            doctors in your city online.
            <br />
            Patient can book an appointment by selecting any of the time slot
            given by doctor.
          </p>
          <div className="button-group">
            <Link to="/patient-sign-up" className="button button-green">
              USER SIGN UP
            </Link>
            <Link to="/userLogin" className="button button-blue">
              LOGIN
            </Link>
            <Link to="/SignUp-new-doctor" className="button button-green">
              DOCTOR SIGN UP
            </Link>
          </div>
        </div>
      </div>

      {/* Features Section */}
      <div className="features-section">
        <div className="feature-card">
          <div className="feature-icon">♥</div>
          <h2 className="feature-title">Save Lives</h2>
          <p className="feature-description">
            By donating blood, you can help save lives and support medical
            treatments.
          </p>
        </div>

        <div className="feature-card">
          <div className="feature-icon">👥</div>
          <h2 className="feature-title">Community</h2>
          <p className="feature-description">
            Join our blood donation community and make a positive impact in your
            community.
          </p>
        </div>

        <div className="feature-card">
          <div className="feature-icon">💊</div>
          <h2 className="feature-title">Health Benefits</h2>
          <p className="feature-description">
            Regular blood donation has numerous health benefits for the donors.
          </p>
        </div>
      </div>
    </div>
  );
};

export default Home;

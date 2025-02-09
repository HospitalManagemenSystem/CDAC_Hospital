// components/Home.js
import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';
import heartImage from '../assets/heart.png';

const Home = () => {
  return (
    <div className="home">
      <section className="hero-section">
        <div className="container">
          <div className="row">
            <div className="col-md-6 hero-text">
              <h1>Welcome to CDAC Hospital</h1>
              <p>
                This platform for booking medical consultations with specialist
                doctors in your city online. Patient can book an appointment by
                selecting any of the time slot given by doctor.
              </p>
              <div className="d-flex flex-wrap gap-3">
                <Link
                  className="btn btn-success text-uppercase"
                  to="/patient-sign-up"
                >
                  User Sign Up
                </Link>
                <Link
                  className="btn btn-info text-dark text-uppercase"
                  to="/userLogin"
                >
                  Login
                </Link>
                <Link
                  className="btn btn-success text-uppercase"
                  to="/SignUp-new-doctor"
                >
                  Doctor Sign Up
                </Link>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section className="features-section">
        <div className="container">
          <div className="row g-4">
            <div className="col-md-4">
              <div className="feature-item">
                <i className="fas fa-heart mb-3"></i>
                <h3>Save Lives</h3>
                <p>
                  By donating blood, you can help save lives and support medical
                  treatments.
                </p>
              </div>
            </div>
            <div className="col-md-4">
              <div className="feature-item">
                <i className="fas fa-users mb-3"></i>
                <h3>Community</h3>
                <p>
                  Join our blood donation community and make a positive impact
                  in your community.
                </p>
              </div>
            </div>
            <div className="col-md-4">
              <div className="feature-item">
                <i className="fas fa-medkit mb-3"></i>
                <h3>Health Benefits</h3>
                <p>
                  Regular blood donation has numerous health benefits for the
                  donors.
                </p>
              </div>
            </div>
          </div>
          <img
            src={heartImage}
            className="img-fluid mt-4"
            alt="Heart illustration"
          />
        </div>
      </section>
    </div>
  );
};

export default Home;

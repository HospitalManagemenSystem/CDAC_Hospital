// About.js
import React, { useState } from 'react';
import './About.css';
const About = () => {
  const [activeTab, setActiveTab] = useState('home');

  return (
    <div className="mx-4">
      {/* Standard Bootstrap tabs */}
      <ul className="nav nav-tabs mb-3" role="tablist">
        <li className="nav-item w-100" role="presentation">
          <button
            className={`nav-link w-100 ${activeTab === 'home' ? 'active' : ''}`}
            onClick={() => setActiveTab('home')}
            role="tab"
          >
            About US
          </button>
        </li>
      </ul>

      {/* Tab content */}
      <div className="tab-content">
        <div
          className={`tab-pane fade ${
            activeTab === 'home' ? 'show active' : ''
          }`}
          role="tabpanel"
        >
          <div className="card">
            <div className="card-body">
              <b>
                1. Welcome to CDAC Hospital, a leading healthcare institution
                dedicated to providing exceptional medical care, compassion, and
                innovation. <br /> 2. Our commitment to your health and
                well-being is unwavering, and we take pride in being a trusted
                healthcare partner for you and your loved ones.
              </b>
            </div>
          </div>
          <br />
          <br />
        </div>
      </div>
    </div>
  );
};

export default About;

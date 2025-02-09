import React from 'react';
import logo from '../assets/LOGO.png';

const Footer = () => {
  return (
    <footer className="bg-dark text-white">
      {/* Social Media Section */}
      <div className="container p-4">
        <div className="mb-4 text-center">
          <a
            className="btn btn-sm m-1"
            style={{ backgroundColor: '#3b5998' }}
            href="#!"
            role="button"
          >
            <i className="fab fa-facebook-f"></i>
          </a>

          <a
            className="btn btn-sm m-1"
            style={{ backgroundColor: '#55acee' }}
            href="#!"
            role="button"
          >
            <i className="fab fa-twitter"></i>
          </a>

          <a
            className="btn btn-sm m-1"
            style={{ backgroundColor: '#dd4b39' }}
            href="#!"
            role="button"
          >
            <i className="fab fa-google"></i>
          </a>

          <a
            className="btn btn-sm m-1"
            style={{ backgroundColor: '#ac2bac' }}
            href="#!"
            role="button"
          >
            <i className="fab fa-instagram"></i>
          </a>

          <a
            className="btn btn-sm m-1"
            style={{ backgroundColor: '#0082ca' }}
            href="#!"
            role="button"
          >
            <i className="fab fa-linkedin-in"></i>
          </a>

          <a
            className="btn btn-sm m-1"
            style={{ backgroundColor: '#333333' }}
            href="#!"
            role="button"
          >
            <i className="fab fa-github"></i>
          </a>
        </div>
      </div>

      {/* Main Footer Content */}
      <div className="container py-5">
        <div className="row gy-4">
          {/* About Section */}
          <div className="col-12 col-md-6 col-lg-3">
            <h6 className="text-uppercase fw-bold mb-4 d-flex align-items-center">
              <img
                src={logo}
                alt="CDAC Hospital"
                className="me-2"
                style={{ width: '40%', height: '50px', objectFit: 'contain' }}
              />
              CDAC Hospital
            </h6>
            <p>
              Welcome to CDAC Hospital, a leading healthcare institution
              dedicated to providing exceptional medical care, compassion, and
              innovation.
            </p>
          </div>

          {/* Services Section */}
          <div className="col-12 col-md-6 col-lg-3">
            <h6 className="text-uppercase fw-bold mb-4">Services</h6>
            <ul className="list-unstyled">
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Physician
                </a>
              </li>
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Covid Consultant
                </a>
              </li>
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Dentist
                </a>
              </li>
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Gynecologist
                </a>
              </li>
            </ul>
          </div>

          {/* Useful Links Section */}
          <div className="col-12 col-md-6 col-lg-3">
            <h6 className="text-uppercase fw-bold mb-4">Useful Links</h6>
            <ul className="list-unstyled">
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Pricing
                </a>
              </li>
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Settings
                </a>
              </li>
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Orders
                </a>
              </li>
              <li className="mb-2">
                <a href="#!" className="text-white text-decoration-none">
                  Help
                </a>
              </li>
            </ul>
          </div>

          {/* Contact Section */}
          <div className="col-12 col-md-6 col-lg-3">
            <h6 className="text-uppercase fw-bold mb-4">Contact</h6>
            <ul className="list-unstyled">
              <li className="mb-3">
                <i className="fas fa-home me-2"></i>
                Pune University Campus, Ganesh Khind Pune - 411 007 Maharashtra
                (India)
              </li>
              <li className="mb-3">
                <i className="fas fa-envelope me-2"></i>
                techsupport@cdac.in
              </li>
              <li className="mb-3">
                <i className="fas fa-phone me-2"></i>
                +91-20-2570-4100
              </li>
              <li className="mb-3">
                <i className="fas fa-print me-2"></i>
                +91-20-2569 4004
              </li>
            </ul>
          </div>
        </div>
      </div>

      {/* Copyright Section */}
      <div
        className="text-center p-3"
        style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}
      >
        © {new Date().getFullYear()} CDAC Health & Research Center | All Rights
        Reserved
      </div>
    </footer>
  );
};

export default Footer;

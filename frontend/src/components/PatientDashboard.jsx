// PatientDashboard.js
import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import PatientServiceMethods from '../service/PatientServiceMethods';
import img1 from '../assets/medical-appointment.png';
import img2 from '../assets/calendar.png';
import img3 from '../assets/online.png';
import img4 from '../assets/profile.png';
import img5 from '../assets/blood-donation.png';

const PatientDashboard = () => {
  const [patientData, setPatientData] = useState({
    patientId: '',
    firstName: '',
  });

  const navigate = useNavigate();

  useEffect(() => {
    loadPatient();
  }, []);

  const loadPatient = () => {
    let patient = JSON.parse(sessionStorage.getItem('patient'));
    setPatientData({
      patientId: patient.userId,
      firstName: patient.userFirstName,
    });
  };

  const updatePatient = () => {
    navigate('/update-profile');
  };

  const logoutPatient = () => PatientServiceMethods.logoutPatient();

  return (
    <div className="container">
      <div className="row my-3">
        <div className="col-sm-6">
          <h2 className="text-capitalize">Hello, {patientData.firstName}</h2>
        </div>
        <div className="col-sm-6">
          <Link
            onClick={logoutPatient}
            className="btn btn-link btn-danger text-light offset-10 text-uppercase text-decoration-none"
            to="/"
          >
            Logout
          </Link>
        </div>
      </div>

      <div className="row">
        <div className="col-sm-4">
          <div className="card">
            <img
              src={img1}
              className="card-img-top"
              alt="Book Appointment"
              style={{ width: '80%', height: '250px' }}
            />
            <div className="card-body">
              <h5 className="card-title">Book Appointment</h5>
              <p className="card-text">
                Book appointments with the best doctors in the city.
              </p>
              <button
                onClick={() => navigate('/specialization-list-by-city')}
                className="btn btn-primary"
              >
                Book
              </button>
            </div>
          </div>
        </div>

        <div className="col-sm-4">
          <div className="card">
            <img
              src={img2}
              className="card-img-top"
              alt="Show Current Appointment"
              style={{ width: '80%', height: '250px' }}
            />
            <div className="card-body">
              <h5 className="card-title">Show Current Appointment</h5>
              <p className="card-text">View your current appointment.</p>
              <button
                onClick={() => navigate('/current-app')}
                className="btn btn-warning"
              >
                View
              </button>
            </div>
          </div>
        </div>

        <div className="col-sm-4">
          <div className="card">
            <img
              src={img3}
              className="card-img-top"
              alt="Book Appointment"
              style={{ width: '80%', height: '250px' }}
            />
            <div className="card-body">
              <h5 className="card-title">View Appointment History</h5>
              <p className="card-text">
                Click to view your till date appointment history.
              </p>
              <button
                onClick={() => navigate('/specialization-list-by-city')}
                className="btn btn-primary"
              >
                View
              </button>
            </div>
          </div>
        </div>

        <div className="col-sm-4">
          <div className="card">
            <img
              src={img4}
              className="card-img-top"
              alt="Book Appointment"
              style={{ width: '80%', height: '250px' }}
            />
            <div className="card-body">
              <h5 className="card-title">Update Profile</h5>
              <p className="card-text">Edit your account details.</p>
              <button
                onClick={() => navigate('/specialization-list-by-city')}
                className="btn btn-primary"
              >
                Update
              </button>
            </div>
          </div>
        </div>

        <div className="col-sm-4">
          <div className="card">
            <img
              src={img5}
              className="card-img-top"
              alt="Book Appointment"
              style={{ width: '80%', height: '250px' }}
            />
            <div className="card-body">
              <h5 className="card-title">Get Blood Donor Info</h5>
              <p className="card-text">
                Click to view details of available blood donors.
              </p>
              <button
                onClick={() => navigate('/specialization-list-by-city')}
                className="btn btn-primary"
              >
                View
              </button>
            </div>
          </div>
        </div>

        <div className="col-sm-4">
          <div className="card">
            <img
              src="https://cdn-icons-png.flaticon.com/512/10415/10415623.png"
              className="card-img-top"
              alt="Add Donor"
              style={{ width: '80%', height: '250px' }}
            />
            <div className="card-body">
              <h5 className="card-title">Add New Donor</h5>
              <p className="card-text">
                Add a new blood donor to the database.
              </p>
              <button
                onClick={() => navigate('/specialization-list-by-city')}
                className="btn btn-primary"
              >
                ADD
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PatientDashboard;

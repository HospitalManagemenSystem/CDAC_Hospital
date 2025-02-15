import React, { useState, useEffect } from 'react';
import moment from 'moment';
import AppointmentService from '../service/AppointmentService';
import { useNavigate, useLocation } from 'react-router-dom';

const ShowAppointmentSlots = () => {
  const [slots, setSlots] = useState([]);
  const [message, setMessage] = useState(null);

  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    getSlots();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getSlots = () => {
    const doctorId = location.state?.doctorId;
    console.log(doctorId);
    AppointmentService.getAllAppointmentSlots(doctorId)
      .then(response => {
        console.log(response.data);
        setSlots(response.data);
        setMessage("Slots retrieved successfully");
      })
      .catch(error => {
        console.error(error);
      });
  };

  // Sort the slots before mapping
  const sortedSlots = [...slots].sort((a, b) => Date.parse(a) - Date.parse(b));

  return (
    <div className="container">
      <button
        className="btn btn-secondary offset-11 mt-3"
        style={{ minWidth: "7vw" }}
        onClick={() => navigate('/doctorDashboard')}
      >
        Go Back
      </button>
      <h3 className="bg-dark text-light py-2 mt-3 text-center">
        Today's Available Slots
      </h3>
      <div className="container d-flex justify-content-around">
        <div>
          {sortedSlots.map(slot => (
            <button
              key={slot}
              className="btn btn-success my-3 mx-3 btn-link text-decoration-none text-light"
              style={{ minWidth: "7vw" }}
            >
              {moment(Date.parse(slot)).format("LT")}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
};

export default ShowAppointmentSlots;

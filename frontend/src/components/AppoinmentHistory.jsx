import React, { useState, useEffect } from 'react';
import AppointmentService from '../service/AppointmentService';
import moment from 'moment';
import { NavLink, useNavigate } from 'react-router-dom';

const AppointmentHistory = () => {
  const [appointments, setAppointments] = useState([]);
  const [message, setMessage] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    getAllAppointments();
  }, []);

  const getAllAppointments = () => {
    const patient = JSON.parse(sessionStorage.getItem('patient'));
    const patientId = patient.userId;

    AppointmentService.getAllAppointmentsHistory(patientId)
      .then((response) => {
        console.log(response.data);
        setAppointments(response.data);
        setMessage('Appointments retrieved successfully');
      })
      .catch((error) => {
        console.error('Error:', error.response.data);
        alert(error.response.data.message);
      });
  };

  return (
       <div className="container my-4">
       <button
        className="btn btn-secondary offset-11"
        onClick={() => navigate('/patientDashboard')}
      >
        Go Back
      </button>

      {appointments.length === 0 ? (
        <h3>You have no appointment history</h3>
      ) : (
        <div>
          <h3>Your Appointment History</h3>
           <table className="table table-bordered">
            <thead className="bg-dark text-light">
              <tr>
                <th>S. No.</th>
                <th>Date</th>
                <th>Time</th>
                <th>Appointment Type</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {appointments.map((appointment, index) => (
                <tr key={appointment.id}>
                  <td>{index + 1}</td>
                  <td>
                    {moment(Date.parse(appointment.appointmentTime)).format(
                      'D MMMM,YYYY'
                    )}
                  </td>
                  <td>
                    {moment(Date.parse(appointment.appointmentTime)).format(
                      'LT'
                    )}
                  </td>
                  <td>{appointment.appointmentType}</td>
                  <td>
                    <NavLink
                      className="btn btn-info btn-link text-dark text-decoration-none offset-1"
                      to="/doctor-details-for-patient"
                      state={{ appointmentId: appointment.id }}
                    >
                      Doctor Details
                    </NavLink>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default AppointmentHistory;

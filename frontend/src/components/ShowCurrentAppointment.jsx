import React, { useState, useEffect } from 'react';
import AppointmentService from '../service/AppointmentService';
import moment from 'moment';
import { NavLink, useNavigate } from 'react-router-dom';
import Card from 'react-bootstrap/Card';

const ShowCurrentAppointment = () => {
  const [appointments, setAppointments] = useState([]);
  const [message, setMessage] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const appointmentsPerPage = 5;

  const navigate = useNavigate();

  useEffect(() => {
    getCurrentAppointments();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const getCurrentAppointments = () => {
    const patient = JSON.parse(sessionStorage.getItem('patient'));
    const patientId = patient.userId;

    AppointmentService.getAllCurrentAppointments(patientId)
      .then((response) => {
        setAppointments(response.data);
        setMessage('Appointments retrieved successfully');
      })
      .catch((error) => {
        console.error('in err ', error.response.data);
        alert(error.response.data.message);
      });
  };

  const cancelAppointment = (appointmentId) => {
    if (window.confirm('Are you sure you want to cancel this appointment?')) {
      AppointmentService.cancelAppointment(appointmentId)
        .then(() => {
          setMessage('Appointment cancelled!!!');
          setAppointments((prevAppointments) =>
            prevAppointments.filter((appointment) => appointment.id !== appointmentId)
          );
        });
    } else {
      navigate('#');
    }
  };

  // Pagination logic
  const indexOfLastAppointment = currentPage * appointmentsPerPage;
  const indexOfFirstAppointment = indexOfLastAppointment - appointmentsPerPage;
  const currentAppointments = appointments.slice(indexOfFirstAppointment, indexOfLastAppointment);

  const renderAppointments = currentAppointments.map((appointment, index) => (
    <tr key={appointment.id}>
      <td>{index + 1 + indexOfFirstAppointment}</td>
      <td>{moment(Date.parse(appointment.appointmentTime)).format('D MMMM, YYYY')}</td>
      <td>{moment(Date.parse(appointment.appointmentTime)).format('LT')}</td>
      <td>{appointment.appointmentType}</td>
      <td>
        <NavLink
          className="btn btn-info btn-link text-dark text-decoration-none"
          to="/doctor-details-for-patient"
          state={{ appointmentId: appointment.id }}
        >
          Doctor Details
        </NavLink>
        <button
          className="btn btn-danger mx-1"
          onClick={() => cancelAppointment(appointment.id)}
        >
          Cancel
        </button>
      </td>
    </tr>
  ));

  const totalPages = Math.ceil(appointments.length / appointmentsPerPage);
  const pageNumbers = Array.from({ length: totalPages }, (_, i) => i + 1);

  return (
    <div className="mx-4 mt-4">
      <Card>
        <Card.Body>
          <div className="container my-4">
            <button
              className="btn btn-secondary offset-11"
              onClick={() => navigate('/patientDashboard')}
            >
              Go Back
            </button>
            {appointments.length === 0 ? (
              <h3>You have no current appointments</h3>
            ) : (
              <div>
                <h3>Your Active Appointments</h3>
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
                  <tbody>{renderAppointments}</tbody>
                </table>
              </div>
            )}
            <div className="text-center">
              <ul className="pagination justify-content-center">
                <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
                  <button
                    className="page-link"
                    onClick={() => setCurrentPage(currentPage - 1)}
                  >
                    Previous
                  </button>
                </li>
                {pageNumbers.map((number) => (
                  <li
                    key={number}
                    className={`page-item ${currentPage === number ? 'active' : ''}`}
                  >
                    <button className="page-link" onClick={() => setCurrentPage(number)}>
                      {number}
                    </button>
                  </li>
                ))}
                <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
                  <button
                    className="page-link"
                    onClick={() => setCurrentPage(currentPage + 1)}
                  >
                    Next
                  </button>
                </li>
              </ul>
            </div>
          </div>
        </Card.Body>
      </Card>
    </div>
  );
};

export default ShowCurrentAppointment;

import React, { useState, useEffect } from 'react';
import AppointmentService from '../service/AppointmentService';
import { NavLink, useNavigate } from 'react-router-dom';
import moment from 'moment';

const DoctorCurrentAppointments = () => {
    const [appointments, setAppointments] = useState([]);
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        getCurrentAppointments();
    }, []);

    const getCurrentAppointments = () => {
        const doctor = JSON.parse(sessionStorage.getItem("doctor"));
        
        AppointmentService.getCurrentAppointmentsForDoctor(doctor.userId)
            .then(response => {
                console.log(response.data);
                setAppointments(response.data);
                setMessage("Appointment list rendered successfully");
            })
            .catch(error => {
                console.error("in err ", error.response.data);
                alert(error.response.data.message);
            });
    };

    const cancelAppointment = (appointmentId) => {
        if (window.confirm("Are you sure you want to cancel this appointment")) {
            AppointmentService.cancelAppointment(appointmentId)
                .then(res => {
                    setMessage('Appointment cancelled!!!');
                    console.log('Appointment cancelled!!!', 'Appointment ID: ', appointmentId);
                    setAppointments(prevAppointments => 
                        prevAppointments.filter(appointment => appointment.id !== appointmentId)
                    );
                });
        }
    };

    const renderAppointmentTable = () => (
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
                        <td>{`${index + 1}`}</td>
                        <td>{moment(Date.parse(appointment.appointmentTime)).format("D MMMM,YYYY")}</td>
                        <td>{moment(Date.parse(appointment.appointmentTime)).format("LT")}</td>
                        <td>{appointment.appointmentType}</td>
                        <td>
                            <NavLink 
                                className="btn btn-info btn-link text-dark text-decoration-none"
                                to="/patient-details-for-doctor"
                                state={{ appointmentId: appointment.id }}
                            >
                                Patient Details
                            </NavLink>
                            <button 
                                className="btn btn-danger mx-1" 
                                onClick={() => cancelAppointment(appointment.id)}
                            >
                                Cancel
                            </button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );

    return (
        <div className="container my-4">
            <button 
                className="btn btn-secondary offset-11" 
                onClick={() => navigate('/doctorDashboard')}
            >
                Go Back
            </button>
            
            {appointments.length === 0 ? (
                <h3>You have no active appointments</h3>
            ) : (
                <div>
                    <h3>Your Active Appointments</h3>
                    {renderAppointmentTable()}
                </div>
            )}
        </div>
    );
};

export default DoctorCurrentAppointments;

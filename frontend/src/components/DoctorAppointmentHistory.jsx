import React, { useState, useEffect } from 'react';
import AppointmentService from '../service/AppointmentService';
import moment from 'moment';
import { NavLink, useNavigate } from 'react-router-dom';

const DoctorAppointmentHistory = () => {
    const [appointments, setAppointments] = useState([]);
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        getAllAppointments();
    }, []); // Empty dependency array means this effect runs once on mount

    const getAllAppointments = () => {
        const doctor = JSON.parse(sessionStorage.getItem("doctor"));

        AppointmentService.getAllAppoinmentsHistoryForDoctor(doctor.userId)
            .then(response => {
                console.log(response.data);
                setAppointments(response.data);
                setMessage("Appointments retrieved successfully");
            })
            .catch(error => {
                console.error("in err ", error.response.data);
                alert(error.response.data.message);
            });
    };

    const appointmentTableBody = () => {
        return appointments.map((appointment, index) => (
            <tr key={appointment.id}>
                <td>{`${index + 1}`}</td>
                <td>{moment(Date.parse(appointment.appointmentTime)).format("D MMMM,YYYY")}</td>
                <td>{moment(Date.parse(appointment.appointmentTime)).format("LT")}</td>
                <td>{appointment.appointmentType}</td>
                <td>
                    <NavLink 
                        className="btn btn-info btn-link text-dark text-decoration-none offset-1" 
                        to="/patient-details-for-doctor"
                        state={{ appointmentId: appointment.id }}
                    >
                        Patient Details
                    </NavLink>
                </td>
            </tr>
        ));
    };

    return (
        <div className="container my-4">
            <button 
                className="btn btn-secondary offset-11" 
                onClick={() => navigate('/doctorDashboard')}
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
                            {appointmentTableBody()}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default DoctorAppointmentHistory;
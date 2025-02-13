import React, { useState, useEffect } from 'react';
import { NavLink, useLocation, useNavigate } from "react-router-dom";
import AppointmentService from '../service/AppointmentService';

const DoctorListForPatient = () => {
    const [doctors, setDoctors] = useState([]);
    const [message, setMessage] = useState(null);
    const location = useLocation();
    const navigate = useNavigate();

    const { city, specialization } = location.state || {};

    useEffect(() => {
        getDoctorList();
    }, [city, specialization]);

    const getDoctorList = () => {
        console.log(city);
        console.log(specialization);

        AppointmentService.getDoctorsBySpecializationAndCity(specialization, city)
            .then(response => {
                console.log(response.data);
                setDoctors(response.data);
                setMessage("Doctor list rendered successfully");
            })
            .catch(error => {
                console.error("in err ", error.response.data);
                alert(error.response.data.message);
            });
    };

    const DoctorTableHeader = () => (
        <thead className="bg-dark text-light">
            <tr>
                <th className="visually-hidden">Id</th>
                <th>Name</th>
                <th>Qualification</th>
                <th>Consultation Fee</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Area</th>
                <th>Action</th>
            </tr>
        </thead>
    );

    const DoctorTableRow = ({ doctor }) => (
        <tr key={doctor.id}>
            <td className="visually-hidden">{doctor.id}</td>
            <td>{`Dr. ${doctor.firstName} ${doctor.lastName}`}</td>
            <td>{doctor.qualification}</td>
            <td>{doctor.fees}</td>
            <td>{doctor.email}</td>
            <td>{doctor.mobileNumber}</td>
            <td>{doctor.area}</td>
            <td>
                <NavLink 
                    className="btn btn-success btn-link text-decoration-none text-light" 
                    to="/doctor-appointment-slots"
                    state={{ doctor: doctor }}
                >
                    Book Appointment
                </NavLink>
            </td>
        </tr>
    );

    return (
        <div className="container my-4">
            <button 
                className="btn btn-secondary offset-11" 
                onClick={() => navigate('/specialization-list-by-city')}
            >
                Go Back
            </button>
            
            <h3>Available {specialization}s in {city}</h3>
            
            <table className="table table-bordered">
                <DoctorTableHeader />
                <tbody>
                    {doctors.map(doctor => (
                        <DoctorTableRow key={doctor.id} doctor={doctor} />
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default DoctorListForPatient;

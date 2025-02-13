import React, { useState, useEffect } from 'react';
import moment from 'moment';
import AppointmentService from '../service/AppointmentService';
import { NavLink, useLocation, useNavigate } from "react-router-dom";

const DoctorAppointmentSlots = () => {
    const [slots, setSlots] = useState([]);
    const [message, setMessage] = useState(null);
    
    const location = useLocation();
    const navigate = useNavigate();
    const { doctor } = location.state || {};

    useEffect(() => {
        if (doctor) {
            getSlots();
        }
    }, [doctor]);

    const getSlots = () => {
        console.log(doctor.id);
        sessionStorage.setItem("doctor", JSON.stringify(doctor));

        AppointmentService.getAllAppointmentSlots(doctor.id)
            .then(response => {
                console.log(response.data);
                console.log(typeof response.data[0]);
                setSlots(response.data);
                setMessage("Slots retrieved successfully");
            })
            .catch(error => {
                console.error("in err ", error.response.data);
            });
    };

    const sortedSlots = () => {
        return slots
            .slice() // Create a copy of the array to avoid mutating the original
            .sort((a, b) => Date.parse(a) - Date.parse(b))
            .map(slot => (
                <NavLink
                    key={slot}
                    to="/book-slot-for-patient"
                    state={{
                        doctor: doctor,
                        time: slot
                    }}
                    className="btn btn-success my-3 mx-3 btn-link text-decoration-none text-light"
                    style={{ minWidth: "12vw" }}
                >
                    {moment(Date.parse(slot)).format("LT")}
                </NavLink>
            ));
    };

    return (
        <div className="container my-4">
            <button 
                className="btn btn-secondary offset-11" 
                style={{ minWidth: "7vw" }} 
                onClick={() => navigate('/patientDashboard')}
            >
                Go Back
            </button>

            <h3 className="bg-dark text-light py-2 my-3 text-center">
                Today's Available Slots
            </h3>

            <div className="container d-flex justify-content-around">
                <div>
                    {slots.length === 0 ? (
                        <h3>No slots available right now</h3>
                    ) : (
                        sortedSlots()
                    )}
                </div>
            </div>
        </div>
    );
};

export default DoctorAppointmentSlots;
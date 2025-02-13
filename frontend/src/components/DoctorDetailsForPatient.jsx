import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import AppointmentService from '../service/AppointmentService';

const DoctorDetailsForPatient = () => {
    const [doctor, setDoctor] = useState({});
    const [message, setMessage] = useState('');
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        getDoctor();
    }, []);

    const getDoctor = () => {
        console.log("Hello");
        const appointmentId = location.state?.appointmentId;
        console.log(appointmentId);

        AppointmentService.getDoctorByAppointmentId(appointmentId)
            .then(response => {
                console.log(response.data);
                setDoctor(response.data);
                setMessage("Doctor retrieved successfully");
            })
            .catch(error => {
                console.error("in err ", error.response.data);
                alert(error.response.data.message);
            });
    };

    const DoctorInfoRow = ({ label, value }) => (
        <tr>
            <td>{label}:</td>
            <td>{value}</td>
        </tr>
    );

    const doctorDetails = [
        { label: "FirstName", value: doctor.firstName },
        { label: "LastName", value: doctor.lastName },
        { label: "Mobile No", value: doctor.mobileNumber },
        { label: "Email", value: doctor.email },
        { label: "State", value: doctor.state },
        { label: "Area", value: doctor.area },
        { label: "City", value: doctor.city }
    ];

    return (
        <div className="container my-4">
            <button 
                className="btn btn-secondary my-3 offset-10" 
                onClick={() => navigate(-1)}
            >
                Go Back
            </button>

            <h3 style={{ textAlign: 'center' }}>Doctor Details</h3>

            <div style={{ marginLeft: '300px' }}>
                <table 
                    className="table table-striped table-sm table-bordered" 
                    style={{ width: '700px' }}
                >
                    <tbody>
                        {doctorDetails.map(({ label, value }) => (
                            <DoctorInfoRow 
                                key={label} 
                                label={label} 
                                value={value} 
                            />
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default DoctorDetailsForPatient;

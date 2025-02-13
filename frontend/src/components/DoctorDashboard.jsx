import React, { useState, useEffect } from 'react';
import DoctorServiceMethods from '../service/DoctorServiceMethods';
import { NavLink, useNavigate } from 'react-router-dom';

const DoctorDashboard = () => {
    const [doctorId, setDoctorId] = useState('');
    const [firstName, setFirstName] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        loadDoctor();
    }, []);

    const logout = () => DoctorServiceMethods.doctorLogout();

    const loadDoctor = () => {
        const doctor = JSON.parse(sessionStorage.getItem("doctor"));
        setDoctorId(doctor.userId);
        setFirstName(doctor.userFirstName);
    };

    const doctor = JSON.parse(sessionStorage.getItem("doctor"));

    const DashboardCard = ({ title, description, buttonText, buttonClass, onClick }) => (
        <div className="col-sm-6">
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">{title}</h5>
                    <p className="card-text">{description}</p>
                    <button 
                        onClick={onClick} 
                        className={`btn ${buttonClass}`}
                    >
                        {buttonText}
                    </button>
                </div>
            </div>
        </div>
    );

    return (
        <div className="container">
            <div className="row my-3">
                <div className="col-sm-6">
                    <h2 className="text-capitalize">Hello, Dr. {firstName}</h2>
                </div>
                <div className="col-sm-6">
                    <NavLink 
                        onClick={logout} 
                        className="btn btn-link btn-danger text-light offset-10 text-uppercase text-decoration-none" 
                        to="/userLogin"
                    >
                        Logout
                    </NavLink>
                </div>
            </div>

            <div className="row">
                <DashboardCard 
                    title="Active Appointments"
                    description="View all your active appointments at present."
                    buttonText="VIEW"
                    buttonClass="btn-primary"
                    onClick={() => navigate('/doctor-current-app')}
                />

                <DashboardCard 
                    title="Appointment History"
                    description="View your appointment history."
                    buttonText="VIEW"
                    buttonClass="btn-info"
                    onClick={() => navigate('/doctor-app-history')}
                />
            </div>

            <div className="row my-3">
                <DashboardCard 
                    title="Create Slots"
                    description="Fill a form to create your slot time-table according to your convenience."
                    buttonText="CREATE"
                    buttonClass="btn-success"
                    onClick={() => navigate('/create-appointment-slots')}
                />

                <DashboardCard 
                    title="Show Todays Slots"
                    description="Display all slots available for today"
                    buttonText="VIEW"
                    buttonClass="btn-warning"
                    onClick={() => navigate('/show-appointment-slots-doctor', { 
                        state: { doctorId: doctor.userId } 
                    })}
                />
            </div>

            <div className="row my-3">
                <DashboardCard 
                    title="Update Profile"
                    description="Update your account details."
                    buttonText="UPDATE"
                    buttonClass="btn-danger"
                    onClick={() => navigate('/update-doctor-profile')}
                />
            </div>
        </div>
    );
};

export default DoctorDashboard;

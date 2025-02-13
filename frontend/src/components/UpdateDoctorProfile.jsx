import React, { useState, useEffect } from 'react';
import DoctorServiceMethods from '../service/DoctorServiceMethods';
import { useNavigate } from 'react-router-dom';

const UpdateDoctorProfile = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        id: '',
        username: '',
        firstName: '',
        lastName: '',
        email: '',
        dob: '',
        gender: '',
        mobileNumber: '',
        beganPractice: '',
        area: '',
        city: '',
        state: '',
        languages: '',
        fees: '',
        qualification: '',
        specialization: '',
    });
    const [message, setMessage] = useState(null);

    useEffect(() => {
        loadDoctor();
    }, []);

    const loadDoctor = () => {
        const doctor = JSON.parse(sessionStorage.getItem("doctor"));
        DoctorServiceMethods.getDoctorById(doctor.userId)
            .then(response => {
                const doctorFull = response.data;
                console.log(doctorFull);
                setFormData(doctorFull);
            });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const saveDoctor = (e) => {
        e.preventDefault();
        DoctorServiceMethods.updateDoctorDetails(formData.id, formData)
            .then(response => {
                setMessage('Doctor details updated successfully.');
                alert('Doctor details updated successfully.');
                navigate('/doctorDashboard');
            });
    };

    const FormField = ({ label, name, type = "text", readOnly = false, ...props }) => (
        <div className="form-group row my-3 justify-content-center">
            <label htmlFor={name} className="col-2 col-form-label">{label}</label>
            <div className="col-5">
                <input
                    type={type}
                    id={name}
                    className="form-control"
                    name={name}
                    value={formData[name] || ''}
                    onChange={handleChange}
                    readOnly={readOnly}
                    {...props}
                />
            </div>
        </div>
    );

    return (
        <div className="container overflow-hidden" style={{ minHeight: "100vh" }}>
            <div className="row my-3">
                <div className="col-sm-8">
                    <h2 className="text-muted offset-8">Update Profile</h2>
                </div>
                <div className="col-sm-4">
                    <button 
                        className="btn btn-secondary text-uppercase offset-8" 
                        onClick={() => navigate('/doctorDashboard')}
                    >
                        Go Back
                    </button>
                </div>
            </div>

            <form className="mb-5">
                <FormField 
                    label="Username" 
                    name="username" 
                    readOnly={true} 
                />
                <FormField 
                    label="First Name" 
                    name="firstName" 
                    placeholder="Doctor's first name" 
                    readOnly={true} 
                />
                <FormField 
                    label="Last Name" 
                    name="lastName" 
                    placeholder="Doctor's last name" 
                    readOnly={true} 
                />
                <FormField 
                    label="Email" 
                    name="email" 
                    type="email" 
                    placeholder="e.g. abc@xyz.com" 
                    readOnly={true} 
                />
                <FormField 
                    label="Date of Birth" 
                    name="dob" 
                    type="date" 
                    readOnly={true} 
                />
                <FormField 
                    label="Mobile" 
                    name="mobileNumber" 
                    placeholder="Doctor's mobile number" 
                    pattern="[0-9]{10}" 
                />
                <FormField 
                    label="Area" 
                    name="area" 
                    placeholder="Doctor's Clinic Area" 
                />
                <FormField 
                    label="City" 
                    name="city" 
                    placeholder="Doctor's city" 
                />
                <FormField 
                    label="State" 
                    name="state" 
                    placeholder="Doctor's State" 
                />
                <FormField 
                    label="Languages" 
                    name="languages" 
                    placeholder="Languages known by doctor" 
                />
                <FormField 
                    label="Consultation Fee" 
                    name="fees" 
                    type="number" 
                    min="200" 
                    max="1000" 
                    step="50" 
                />
                <FormField 
                    label="Qualification" 
                    name="qualification" 
                    placeholder="Doctor's qualification" 
                />
                <FormField 
                    label="Specialization" 
                    name="specialization" 
                    placeholder="Doctor's specialization" 
                />

                <button 
                    className="btn btn-lg btn-primary text-uppercase mt-3 mb-5 offset-6" 
                    onClick={saveDoctor}
                >
                    Update
                </button>
            </form>
        </div>
    );
};

export default UpdateDoctorProfile;
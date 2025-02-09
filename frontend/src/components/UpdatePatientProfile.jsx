import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const UpdatePatientProfile = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    id: '',
    username: '',
    firstName: '',
    lastName: '',
    email: '',
    dob: '',
    gender: '',
    bloodGroup: '',
    mobileNumber: '',
    area: '',
    city: '',
    state: '',
  });

  const [errors, setErrors] = useState({});
  const [message, setMessage] = useState('');

  useEffect(() => {
    loadPatientData();
  }, []);

  const loadPatientData = async () => {
    try {
      const patient = JSON.parse(sessionStorage.getItem('patient'));
      if (!patient?.userId) {
        navigate('/');
        return;
      }

      const response = await fetch(`/api/patients/${patient.userId}`);
      const data = await response.json();

      setFormData({
        id: data.id,
        username: data.username,
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
        dob: data.dob,
        gender: data.gender,
        bloodGroup: data.bloodGroup,
        mobileNumber: data.mobileNumber,
        area: data.area,
        city: data.city,
        state: data.state,
      });
    } catch (error) {
      setMessage('Error loading patient data');
      console.error('Error:', error);
    }
  };

  const validateForm = () => {
    const newErrors = {};

    if (!formData.mobileNumber.match(/^\d{10}$/)) {
      newErrors.mobileNumber = 'Please enter a valid 10-digit mobile number';
    }

    if (!formData.area.trim()) {
      newErrors.area = 'Area is required';
    }

    if (!formData.city.trim()) {
      newErrors.city = 'City is required';
    }

    if (!formData.state.trim()) {
      newErrors.state = 'State is required';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    try {
      const response = await fetch(`/api/patients/${formData.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setMessage('Profile updated successfully');
        alert('Profile updated successfully');
        navigate('/patientDashboard');
      } else {
        throw new Error('Update failed');
      }
    } catch (error) {
      setMessage('Error updating profile');
      console.error('Error:', error);
    }
  };

  return (
    <div className="container py-4">
      <div className="row mb-4">
        <div className="col-md-8">
          <h2 className="text-muted">Update Profile</h2>
        </div>
        <div className="col-md-4">
          <button
            className="btn btn-secondary"
            onClick={() => navigate('/patientDashboard')}
          >
            Go Back
          </button>
        </div>
      </div>

      {message && <div className="alert alert-info mb-4">{message}</div>}

      <div className="card shadow-sm">
        <div className="card-body">
          <form onSubmit={handleSubmit}>
            {/* Read-only Fields */}
            <div className="row mb-3">
              <div className="col-md-6">
                <label className="form-label">Username</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.username}
                  readOnly
                />
              </div>
              <div className="col-md-6">
                <label className="form-label">Email</label>
                <input
                  type="email"
                  className="form-control"
                  value={formData.email}
                  readOnly
                />
              </div>
            </div>

            <div className="row mb-3">
              <div className="col-md-6">
                <label className="form-label">First Name</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.firstName}
                  readOnly
                />
              </div>
              <div className="col-md-6">
                <label className="form-label">Last Name</label>
                <input
                  type="text"
                  className="form-control"
                  value={formData.lastName}
                  readOnly
                />
              </div>
            </div>

            <div className="row mb-3">
              <div className="col-md-6">
                <label className="form-label">Date of Birth</label>
                <input
                  type="date"
                  className="form-control"
                  value={formData.dob}
                  readOnly
                />
              </div>
              <div className="col-md-6">
                <label className="form-label">Blood Group</label>
                <select
                  className="form-select"
                  value={formData.bloodGroup}
                  readOnly
                >
                  <option value="A_POSITIVE">A+</option>
                  <option value="A_NEGATIVE">A-</option>
                  <option value="B_POSITIVE">B+</option>
                  <option value="B_NEGATIVE">B-</option>
                  <option value="O_POSITIVE">O+</option>
                  <option value="O_NEGATIVE">O-</option>
                  <option value="AB_POSITIVE">AB+</option>
                  <option value="AB_NEGATIVE">AB-</option>
                </select>
              </div>
            </div>

            {/* Editable Fields */}
            <div className="row mb-3">
              <div className="col-md-6">
                <label className="form-label">Mobile Number</label>
                <input
                  type="tel"
                  className={`form-control ${
                    errors.mobileNumber ? 'is-invalid' : ''
                  }`}
                  name="mobileNumber"
                  value={formData.mobileNumber}
                  onChange={handleChange}
                />
                {errors.mobileNumber && (
                  <div className="invalid-feedback">{errors.mobileNumber}</div>
                )}
              </div>
            </div>

            <div className="row mb-3">
              <div className="col-md-12">
                <label className="form-label">Area</label>
                <input
                  type="text"
                  className={`form-control ${errors.area ? 'is-invalid' : ''}`}
                  name="area"
                  value={formData.area}
                  onChange={handleChange}
                />
                {errors.area && (
                  <div className="invalid-feedback">{errors.area}</div>
                )}
              </div>
            </div>

            <div className="row mb-3">
              <div className="col-md-6">
                <label className="form-label">City</label>
                <input
                  type="text"
                  className={`form-control ${errors.city ? 'is-invalid' : ''}`}
                  name="city"
                  value={formData.city}
                  onChange={handleChange}
                />
                {errors.city && (
                  <div className="invalid-feedback">{errors.city}</div>
                )}
              </div>
              <div className="col-md-6">
                <label className="form-label">State</label>
                <input
                  type="text"
                  className={`form-control ${errors.state ? 'is-invalid' : ''}`}
                  name="state"
                  value={formData.state}
                  onChange={handleChange}
                />
                {errors.state && (
                  <div className="invalid-feedback">{errors.state}</div>
                )}
              </div>
            </div>

            <div className="text-end mt-4">
              <button type="submit" className="btn btn-primary btn-lg">
                Update Profile
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default UpdatePatientProfile;

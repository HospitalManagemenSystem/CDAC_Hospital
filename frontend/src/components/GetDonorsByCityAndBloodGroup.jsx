import React, { useState, useEffect } from 'react';
import BloodDonorService from '../service/BloodDonorService';
import { useNavigate } from 'react-router-dom';

const GetDonorsByCityAndBloodGroup = () => {
  const [donors, setDonors] = useState([]);
  const [city, setCity] = useState('Pune');
  const [bloodGroup, setBloodGroup] = useState('A_POSITIVE');
  const [message, setMessage] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    displayFirst();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const displayFirst = () => {
    BloodDonorService.getAllBloodDonorsByCityAndBloodGroup(city, bloodGroup)
      .then((response) => {
        console.log(response.data);
        setDonors(response.data);
        setMessage('Donor list retrieved successfully');
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const getDonorsList = (e) => {
    e.preventDefault();
    BloodDonorService.getAllBloodDonorsByCityAndBloodGroup(city, bloodGroup)
      .then((response) => {
        console.log(response.data);
        setDonors(response.data);
        setMessage('Donor list retrieved successfully');
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handleCityChange = (e) => {
    setCity(e.target.value);
  };

  const handleBloodGroupChange = (e) => {
    setBloodGroup(e.target.value);
  };

  return (
    <div className="container overflow-hidden">
      <div className="row my-3">
        <div className="col-sm-10">
          <h2 className="text-muted offset-6">Blood Donor Information</h2>
        </div>
        <div className="col-sm-2">
          <button
            className="btn btn-secondary text-uppercase offset-4"
            onClick={() => navigate('./patientDashboard')}
          >
            Go Back
          </button>
        </div>
      </div>
      <form>
        <div className="form-group row mt-3 justify-content-center">
          <label htmlFor="city" className="col-1 col-form-label">
            City
          </label>
          <div className="col-5">
            <input
              type="text"
              id="city"
              className="form-control"
              name="city"
              value={city}
              onChange={handleCityChange}
            />
          </div>
        </div>

        <div className="form-group row my-3 justify-content-center">
          <label className="col-3 col-form-label">Blood Group</label>
          <div className="col-4">
            <select
              value={bloodGroup}
              onChange={handleBloodGroupChange}
              style={{ width: '7vw', height: '7vh' }}
            >
              <option value="" disabled>
                --select--
              </option>
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
        <button className="btn btn-primary mt-3 offset-6" onClick={getDonorsList}>
          Submit
        </button>
      </form>

      {donors.length === 0 ? (
        <h3 className="mt-3">No donors in given criteria</h3>
      ) : (
        <table className="table table-bordered my-3">
          <thead className="bg-dark text-light">
            <tr>
              <th className="visually-hidden">Id</th>
              <th>Name</th>
              <th>Email</th>
              <th>Contact Number</th>
              <th>Blood Group</th>
            </tr>
          </thead>
          <tbody>
            {donors.map((donor) => (
              <tr key={donor.id}>
                <td className="visually-hidden">{donor.id}</td>
                <td>{donor.name}</td>
                <td>{donor.email}</td>
                <td>{donor.contactNumber}</td>
                <td>{donor.bloodGroup}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default GetDonorsByCityAndBloodGroup;

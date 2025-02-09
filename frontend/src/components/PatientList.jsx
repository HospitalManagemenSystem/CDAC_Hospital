// PatientList.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AdminServiceMethods from '../service/AdminServiceMethods';

const PatientList = () => {
  const [patients, setPatients] = useState([]);
  const [message, setMessage] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [patientsPerPage] = useState(5);
  const navigate = useNavigate();

  useEffect(() => {
    reloadPatientList();
  }, []);

  const reloadPatientList = () => {
    AdminServiceMethods.fetchAllPatients().then((resp) => {
      setPatients(resp.data);
      setMessage('Patient list rendered successfully');
    });
  };

  const deletePatient = (patientId) => {
    if (
      window.confirm(
        'Are you sure you want to delete this patient? This action cannot be undone.'
      )
    ) {
      AdminServiceMethods.deletePatient(patientId)
        .then(() => {
          setMessage('Patient deleted successfully.');
          setPatients(patients.filter((patient) => patient.id !== patientId));
          alert('The patient has been deleted successfully.');
        })
        .catch((error) => {
          console.error('Error deleting patient: ', error.response.data);
          alert('An error occurred while deleting the patient.');
        });
    }
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // Calculate pagination
  const indexOfLastPatient = currentPage * patientsPerPage;
  const indexOfFirstPatient = indexOfLastPatient - patientsPerPage;
  const currentPatients = patients.slice(
    indexOfFirstPatient,
    indexOfLastPatient
  );
  const totalPages = Math.ceil(patients.length / patientsPerPage);
  const pageNumbers = Array.from({ length: totalPages }, (_, i) => i + 1);

  return (
    <div className="container my-4">
      <button
        className="btn btn-secondary offset-11"
        onClick={() => navigate('/adminDashboard')}
      >
        Go Back
      </button>
      {patients.length === 0 ? (
        <h3>No patients in database</h3>
      ) : (
        <div>
          <h3>Patient List</h3>
          <table className="table table-bordered">
            <thead className="bg-dark text-light">
              <tr>
                <th className="visually-hidden">Id</th>
                <th>Name</th>
                <th>D.O.B.</th>
                <th>City</th>
                <th>Gender</th>
                <th>Blood Group</th>
                <th>Email</th>
                <th>Mobile Number</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {currentPatients.map((patient) => (
                <tr key={patient.id}>
                  <td className="visually-hidden">{patient.id}</td>
                  <td>{`${patient.firstName} ${patient.lastName}`}</td>
                  <td>{patient.dob}</td>
                  <td>{patient.city}</td>
                  <td>{patient.gender}</td>
                  <td>{patient.bloodGroup}</td>
                  <td>{patient.email}</td>
                  <td>{patient.mobileNumber}</td>
                  <td>
                    <button
                      className="btn btn-danger"
                      onClick={() => deletePatient(patient.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="pagination justify-content-center">
            <ul className="pagination">
              <li
                className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}
              >
                <button
                  className="page-link"
                  onClick={() => handlePageChange(currentPage - 1)}
                >
                  Previous
                </button>
              </li>
              {pageNumbers.map((number) => (
                <li
                  key={number}
                  className={`page-item ${
                    currentPage === number ? 'active' : ''
                  }`}
                >
                  <button
                    className="page-link"
                    onClick={() => handlePageChange(number)}
                  >
                    {number}
                  </button>
                </li>
              ))}
              <li
                className={`page-item ${
                  currentPage === totalPages ? 'disabled' : ''
                }`}
              >
                <button
                  className="page-link"
                  onClick={() => handlePageChange(currentPage + 1)}
                >
                  Next
                </button>
              </li>
            </ul>
          </div>
        </div>
      )}
    </div>
  );
};

export default PatientList;

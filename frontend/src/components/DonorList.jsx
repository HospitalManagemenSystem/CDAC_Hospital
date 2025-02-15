import React, { useState, useEffect } from 'react';
import AdminServiceMethods from '../service/AdminServiceMethods';
import { useNavigate } from 'react-router-dom';

const DonorList = () => {
  const [donors, setDonors] = useState([]);
  const [message, setMessage] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const donorsPerPage = 5; // constant value

  const navigate = useNavigate();

  useEffect(() => {
    loadDonorList();
  }, []);

  const loadDonorList = () => {
    AdminServiceMethods.fetchAllBloodDonors()
      .then((resp) => {
        setDonors(resp.data);
        setMessage("Donor list rendered successfully");
        console.log("Donor list rendered successfully");
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // Pagination logic
  const indexOfLastDonor = currentPage * donorsPerPage;
  const indexOfFirstDonor = indexOfLastDonor - donorsPerPage;
  const currentDonors = donors.slice(indexOfFirstDonor, indexOfLastDonor);

  const totalPages = Math.ceil(donors.length / donorsPerPage);
  const pageNumbers = Array.from({ length: totalPages }, (_, i) => i + 1);

  return (
    <div className="container my-4">
      <button 
        className="btn btn-secondary offset-11" 
        onClick={() => navigate('/adminDashboard')}
      >
        Go Back
      </button>
      {donors.length === 0 ? (
        <h3>No donors in the database</h3>
      ) : (
        <div>
          <h3>Donor List</h3>
          <table className="table table-bordered">
            <thead className="bg-dark text-light">
              <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>City</th>
                <th>Contact Number</th>
                <th>Blood Group</th>
              </tr>
            </thead>
            <tbody>
              {currentDonors.map((donor) => (
                <tr key={donor.id}>
                  <td>{donor.id}</td>
                  <td>{donor.name}</td>
                  <td>{donor.email}</td>
                  <td>{donor.city}</td>
                  <td>{donor.contactNumber}</td>
                  <td>{donor.bloodGroup}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="pagination justify-content-center">
            <ul className="pagination">
              <li className={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
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
                  className={`page-item ${currentPage === number ? 'active' : ''}`}
                >
                  <button 
                    className="page-link" 
                    onClick={() => handlePageChange(number)}
                  >
                    {number}
                  </button>
                </li>
              ))}
              <li className={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
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

export default DonorList;

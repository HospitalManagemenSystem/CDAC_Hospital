import React, { useState, useEffect } from 'react';
import AppointmentService from '../service/AppointmentService';
import { NavLink, useNavigate } from 'react-router-dom';
import Card from 'react-bootstrap/Card';

const SpecializationListByCity = () => {
  const [specializations, setSpecializations] = useState([]);
  const [city, setCity] = useState('Pune');
  const [message, setMessage] = useState(null);

  const navigate = useNavigate();

  useEffect(() => {
    searchFirst();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const searchFirst = () => {
    AppointmentService.getSpecializationListByCity(city)
      .then((response) => {
        console.log(city);
        setSpecializations(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const search = (e) => {
    e.preventDefault();
    AppointmentService.getSpecializationListByCity(city)
      .then((response) => {
        console.log(city);
        setSpecializations(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  const onChange = (e) => setCity(e.target.value);

  return (
    <div className="mx-4 mt-4">
      <Card style={{ backgroundColor: "aliceblue" }}>
        <Card.Body>
          <div className="container overflow-hidden">
            <div className="row my-3">
              <div className="col-md-6 offset-md-3 text-center">
                <h3 className="text-muted">Select Specialization</h3>
              </div>
              <div className="col-sm-4">
                <button
                  className="btn btn-secondary text-uppercase"
                  onClick={() => navigate(-1)}
                >
                  Go Back
                </button>
              </div>
            </div>
            <div className="row">
              <div className="col-md-4 offset-md-4">
                <form>
                  <div className="form-group">
                    <label htmlFor="city" style={{ fontWeight: 'bold' }}>
                      City:
                    </label>
                    <input
                      type="text"
                      id="city"
                      className="form-control"
                      name="city"
                      value={city}
                      onChange={onChange}
                    />
                  </div>
                  <div className="form-group text-center">
                    <button className="btn btn-primary mt-3" onClick={search}>
                      Search
                    </button>
                  </div>
                </form>
              </div>
            </div>
            <div className="row mt-3">
              {specializations.length === 0 ? (
                <h3 className="text-center col-12">
                  We will be in your city soon
                </h3>
              ) : (
                specializations.map((specialization, index) => (
                  <div className="col-md-4 mb-3" key={index}>
                    <Card>
                      <Card.Body className="text-center">
                        <NavLink
                          className="btn btn-lg btn-outline-secondary text-decoration-none"
                          to="/doctor-list-patient"
                          state={{ city: city, specialization: specialization }}
                        >
                          {specialization}
                        </NavLink>
                      </Card.Body>
                    </Card>
                  </div>
                ))
              )}
            </div>
          </div>
        </Card.Body>
      </Card>
    </div>
  );
};

export default SpecializationListByCity;

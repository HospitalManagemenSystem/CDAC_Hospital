import React, { useState } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import Swal from 'sweetalert2';
import AdminServiceMethods from '../service/AdminServiceMethods';
import Card from 'react-bootstrap/Card';
import { useNavigate } from 'react-router-dom';

const donorValidationSchema = Yup.object().shape({
  name: Yup.string().min(3, 'name must 3 charecter').required('Donor Name is Required'),
  email: Yup.string().email('Invalid email').required('Donor email is Required'),
  bloodGroup: Yup.string().required('Blood group is Required'),
  contactNumber: Yup.string().matches(/^\d{10}$/, 'Must be 10 digits').required('Mobile number is Required'),
  city: Yup.string().min(3, 'City must 3 charecter').required('City is Required'),
  state: Yup.string().min(3, 'State must 3 charecter').required('State is Required')
});

const AddNewDonor = () => {
  const [message, setMessage] = useState(null);
  const navigate = useNavigate();

  const addDonor = async (values, actions) => {
    try {
      const response = await AdminServiceMethods.saveDonor(values);
      console.log(response.data);
      setMessage('Donor added successfully.');
      Swal.fire('Success', 'Donor added successfully.', 'success');
      navigate('/adminDashboard');
    } catch (error) {
      console.error('Error:', error.response.data);
      Swal.fire('Error', error.response.data.message, 'error');
      navigate('/adminDashboard');
    }
  };

  return (
    <div className="mx-5 mt-5">
      <Card>
        <Card.Body>
          <div className="container overflow-hidden" style={{ minHeight: '50vh', backgroundColor: "aliceblue" }}>
            <div className="row mt-3">
              <div className="col-sm-8">
                <h2 className="text-muted offset-9">Add Donor</h2>
              </div>
              <div className="col-sm-4">
                <button
                  className="btn btn-secondary text-uppercase offset-8"
                  onClick={() => navigate(-1)}
                >
                  Go Back
                </button>
              </div>
            </div>
            <Formik
              initialValues={{
                name: '',
                email: '',
                contactNumber: '',
                city: '',
                state: '',
                bloodGroup: ''
              }}
              validationSchema={donorValidationSchema}
              onSubmit={addDonor}
            >
              {({ errors, touched }) => (
                <Form>
                  <div className="form-group row my-3 justify-content-center">
                    <label htmlFor="name" className="col-2 col-form-label">
                      Donor Name
                    </label>
                    <div className="col-5">
                      <Field
                        type="text"
                        id="name"
                        name="name"
                        placeholder="Enter donor's name"
                        className={`form-control ${touched.name && errors.name ? 'is-invalid' : ''}`}
                      />
                      <ErrorMessage name="name" component="div" className="text-danger" />
                    </div>
                  </div>
                  <div className="form-group row my-3 justify-content-center">
                    <label htmlFor="email" className="col-2 col-form-label">
                      Email
                    </label>
                    <div className="col-5">
                      <Field
                        type="email"
                        id="email"
                        name="email"
                        placeholder="e.g. abc@xyz.com"
                        className={`form-control ${touched.email && errors.email ? 'is-invalid' : ''}`}
                      />
                      <ErrorMessage name="email" component="div" className="text-danger" />
                      </div>
                  </div>
                  <div className="form-group row mt-3 justify-content-center">
                    <label htmlFor="contactNumber" className="col-2 col-form-label">
                      Contact Number
                    </label>
                    <div className="col-5">
                      <Field
                        type="text"
                        id="contactNumber"
                        name="contactNumber"
                        placeholder="Donor's contact number"
                        className={`form-control ${touched.contactNumber && errors.contactNumber ? 'is-invalid' : ''}`}
                      />
                      <ErrorMessage name="contactNumber" component="div" className="text-danger" />
                    </div>
                  </div>
                  <div className="form-group row mt-3 justify-content-center">
                    <label htmlFor="city" className="col-2 col-form-label">
                      City
                    </label>
                    <div className="col-5">
                      <Field
                        type="text"
                        id="city"
                        name="city"
                        placeholder="Donor's city"
                        className={`form-control ${touched.city && errors.city ? 'is-invalid' : ''}`}
                      />
                      <ErrorMessage name="city" component="div" className="text-danger" />
                    </div>
                  </div>
                  <div className="form-group row mt-3 justify-content-center">
                    <label htmlFor="state" className="col-2 col-form-label">
                      State
                    </label>
                    <div className="col-5">
                      <Field
                        type="text"
                        id="state"
                        name="state"
                        placeholder="Donor's State"
                        className={`form-control ${touched.state && errors.state ? 'is-invalid' : ''}`}
                      />
                      <ErrorMessage name="state" component="div" className="text-danger" />
                    </div>
                  </div>
                  <div className="form-group row mt-3 justify-content-center">
                    <label htmlFor="bloodGroup" className="col-2 col-form-label">
                      Blood Group
                    </label>
                    <div className="col-5 d-flex justify-content-between">
                      <Field
                        as="select"
                        name="bloodGroup"
                        className={`form-control ${touched.bloodGroup && errors.bloodGroup ? 'is-invalid' : ''}`}
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
                      </Field>
                      <ErrorMessage name="bloodGroup" component="div" className="text-danger" />
                    </div>
                  </div>
                  <div className="form-group row mt-3 justify-content-center">
                    <div className="col-5 offset-2">
                      <button type="submit" className="btn btn-lg btn-primary text-uppercase mt-3">
                        Submit
                      </button>
                    </div>
                  </div>
                </Form>
              )}
            </Formik>
          </div>
        </Card.Body>
      </Card>
    </div>
  );
};

export default AddNewDonor;

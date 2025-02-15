import React, { useState } from 'react';
import UserLoginAPI from '../service/UserLoginAPI';
import ReactModuleLoader from 'react-module-loader';
import { Form, Button, Card } from 'react-bootstrap';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';

const EmailForForgotPassword = () => {
  const [loading, setLoading] = useState(false);
  const [email, setEmail] = useState('');
  const [token, setToken] = useState('');
  const [message, setMessage] = useState(null);
  
  const navigate = useNavigate();

  const submit = e => {
    e.preventDefault();

    setLoading(true);

    // Faking API call here
    setTimeout(() => {
      setLoading(false);
    }, 1000);

    UserLoginAPI.generateToken(email)
      .then(response => {
        setToken(response.data);
        setMessage('Token received');

        Swal.fire({
          icon: 'success',
          title: 'Token Sent',
          text: 'Token has been sent to the registered email!',
        }).then(() => {
          navigate('/enter-token', {
            state: {
              email: email,
              token: response.data,
            },
          });
        });
      })
      .catch(error => {
        console.error('in err ', error.response.data);
        Swal.fire({
          icon: 'error',
          title: 'Invalid Email',
          text: 'Please enter a valid email address.',
        }).then(() => {
          navigate('#');
        });
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const validateEmail = () => {
    const emailInput = document.getElementById("email1").value;
    const emailRegex = /\S+@\S+\.\S+/;
    if (emailRegex.test(emailInput) || emailInput === '') {
      return true;
    } else {
      document.getElementById("emailVR").innerHTML = "Email format should be abc@xyz.com";
      return false;
    }
  };

  const removeAlert = () => {
    document.getElementById("emailVR").innerHTML = "";
  };

  const onChange = e => setEmail(e.target.value);

  return (
    <div className="container mt-5 d-flex justify-content-center align-items-center vh-50">
      <Card style={{ width: '30vw', backgroundColor: "antiquewhite" }}>
        <Card.Body>
          <Card.Title className="text-center mt-3 mb-3">Forgot Password?</Card.Title>
          <Form>
            <Form.Group>
              <Form.Control
                type="email"
                id="email1"
                placeholder="Enter Registered Email"
                name="email"
                value={email}
                onChange={onChange}
                onBlur={validateEmail}
                onFocus={removeAlert}
                required
              />
              <span id="emailVR" style={{ color: 'red' }}></span>
            </Form.Group>
            <Button className="my-3 offset-5" onClick={submit} disabled={loading}>
              {loading ? (
                <>
                  <i className="fas fa-spinner" style={{ marginRight: '5px' }} />Submitting
                </>
              ) : (
                <>Submit</>
              )}
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </div>
  );
};

export default EmailForForgotPassword;

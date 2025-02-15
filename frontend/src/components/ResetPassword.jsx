import React, { useState } from 'react';
import UserLoginAPI from '../service/UserLoginAPI';
import Swal from 'sweetalert2';
import Card from 'react-bootstrap/Card';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { useLocation, useNavigate } from 'react-router-dom';

const ResetPassword = () => {
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [message, setMessage] = useState(null);

  const location = useLocation();
  const navigate = useNavigate();

  const submit = (e) => {
    e.preventDefault();
    const email = location.state?.email;
    console.log(email);
    console.log(password);

    if (password === confirmPassword) {
      UserLoginAPI.resetPassword(email, password)
        .then((response) => {
          console.log(response.data);
          setMessage('Password changed successfully!!!');

          Swal.fire({
            icon: 'success',
            title: 'Password Changed',
            text: 'Password changed successfully!!!',
          }).then(() => {
            navigate('/userLogin');
          });
        })
        .catch((error) => {
          Swal.fire({
            icon: 'error',
            title: 'Same Password',
            text: 'Password is same as the previous password.',
          }).then(() => {
            navigate('/userLogin');
          });
        });
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Password Mismatch',
        text: 'Passwords do not match. Please try again.',
      }).then(() => {
        navigate('/reset-password', {
          state: { email },
        });
      });
    }
  };

  const onChange = (e) => {
    const { name, value } = e.target;
    if (name === 'password') setPassword(value);
    else if (name === 'confirmPassword') setConfirmPassword(value);
  };

  return (
    <div className="container mt-5 d-flex justify-content-center align-items-center vh-50">
      <Card className="w-50" style={{ backgroundColor: "bisque" }}>
        <Card.Body>
          <h2 className="text-center mt-3">Reset Password</h2>
          <Form>
            <Form.Group>
              <Form.Control
                type="password"
                className="text-center mt-3"
                placeholder="New Password"
                name="password"
                value={password}
                onChange={onChange}
                required
              />
            </Form.Group>
            <Form.Group className="my-3">
              <Form.Control
                type="password"
                className="text-center"
                name="confirmPassword"
                placeholder="Confirm Password"
                value={confirmPassword}
                onChange={onChange}
                required
              />
            </Form.Group>
            <Button variant="primary" className="my-3 offset-5" onClick={submit}>
              SUBMIT
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </div>
  );
};

export default ResetPassword;

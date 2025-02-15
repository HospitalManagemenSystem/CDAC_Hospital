import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const EnterToken = () => {
  const [token, setToken] = useState('');
  const [counter, setCounter] = useState(0);

  const location = useLocation();
  const navigate = useNavigate();

  const submit = (e) => {
    e.preventDefault();

    // Compare the entered token with the one passed via location.state
    if (token === location.state?.token) {
      navigate('/reset-password', {
        state: {
          email: location.state.email,
        },
      });
    } else {
      const newCounter = counter + 1;
      setCounter(newCounter);

      if (newCounter < 2) {
        alert(`Incorrect token: ${2 - newCounter} tries left`);
        navigate('/enter-token', {
          state: {
            token: location.state?.token,
            counter: newCounter,
          },
        });
      } else {
        navigate('/userLogin');
      }
    }
  };

  const onChange = (e) => setToken(e.target.value);

  return (
    <>
      <h2 className="text-center mt-3">Enter Token</h2>
      <form className="container bg-dark pt-2 mt-3" style={{ width: '30vw' }}>
        <div className="form-group">
          <input
            type="text"
            className="form-control text-center mt-3"
            placeholder="Enter Token"
            name="token"
            value={token}
            onChange={onChange}
            required
          />
        </div>
        <button className="btn btn-primary my-3 offset-5" onClick={submit}>
          SUBMIT
        </button>
      </form>
    </>
  );
};

export default EnterToken;

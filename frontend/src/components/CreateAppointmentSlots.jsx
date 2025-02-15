import React, { useState } from 'react';
import DoctorServiceMethods from '../service/DoctorServiceMethods';
import Swal from 'sweetalert2';
import Card from 'react-bootstrap/Card';
import { useNavigate } from 'react-router-dom';

const CreateAppointmentSlots = () => {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const [slotDuration, setSlotDuration] = useState('30');
  const [breakTime, setBreakTime] = useState('');
  const [holidays, setHolidays] = useState([]);
  const [message, setMessage] = useState(null);

  const navigate = useNavigate();

  const createTimeTable = e => {
    e.preventDefault();
    console.log(holidays);
    const doctor = JSON.parse(sessionStorage.getItem('doctor'));
    const doctorId = doctor.userId;

    const doctorTimeTable = {
      startDate,
      endDate,
      startTime,
      endTime,
      slotDuration,
      breakTime,
      holidays,
    };

    const date = new Date();
    const sDate = new Date(startDate);
    const eDate = new Date(endDate);

    const str1 = startTime.split(':');
    const str2 = endTime.split(':');

    console.log(sDate.getTime() === date.getTime());

    if (
      sDate.getDate() === date.getDate() &&
      eDate.getDate() === date.getDate() &&
      parseInt(str1[0]) < date.getHours()
    ) {
      alert('Time is not valid');
    } else if (
      (sDate.getDate() !== date.getDate() && eDate.getDate() !== date.getDate() && sDate < date) ||
      eDate < sDate
    ) {
      alert('Date is not valid');
    } else if (parseInt(str1[0]) > parseInt(str2[0])) {
      alert('Time is not valid');
    } else {
      DoctorServiceMethods.createAppointmentSlots(doctorTimeTable, doctorId)
        .then(() => {
          setMessage('Your slot time-table is created successfully.');
          Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: 'Your slot time-table is created successfully.',
            onClose: () => {
              navigate('/doctorDashboard');
            },
          });
        })
        .catch(error => {
          console.error('Error:', error.response.data);
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: error.response.data.message,
            onClose: () => {
              navigate('/doctorDashboard');
            },
          });
        });
    }
  };

  const onChange = e => {
    const { name, value } = e.target;
    switch (name) {
      case 'startDate':
        setStartDate(value);
        break;
      case 'endDate':
        setEndDate(value);
        break;
      case 'startTime':
        setStartTime(value);
        break;
      case 'endTime':
        setEndTime(value);
        break;
      case 'slotDuration':
        setSlotDuration(value);
        break;
      case 'breakTime':
        setBreakTime(value);
        break;
      default:
        break;
    }
  };

  const handleChecklist = e => {
    if (e.target.checked) {
      switch (e.target.value) {
        case '0':
          setHolidays(prev => [...prev, 'Sunday']);
          break;
        case '1':
          setHolidays(prev => [...prev, 'Monday']);
          break;
        case '2':
          setHolidays(prev => [...prev, 'Tuesday']);
          break;
        case '3':
          setHolidays(prev => [...prev, 'Wednesday']);
          break;
        case '4':
          setHolidays(prev => [...prev, 'Thursday']);
          break;
        case '5':
          setHolidays(prev => [...prev, 'Friday']);
          break;
        case '6':
          setHolidays(prev => [...prev, 'Saturday']);
          break;
        default:
          break;
      }
    }
  };

  return (
    <div className="mx-4 mt-4">
      <Card style={{ backgroundColor: 'aliceblue' }}>
        <Card.Body>
          <div className="container overflow-hidden" style={{ minHeight: '100vh' }}>
            <div className="row mt-3">
              <div className="col-sm-8">
                <h2 className="text-muted offset-8">Create Your Slots</h2>
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
            <form>
              <div className="form-group row my-3 justify-content-center">
                <label htmlFor="startDate" className="col-2 col-form-label">
                  Start Date
                </label>
                <div className="col-5">
                  <input
                    type="date"
                    id="startDate"
                    className="form-control"
                    name="startDate"
                    value={startDate}
                    onChange={onChange}
                  />
                </div>
              </div>

              <div className="form-group row my-3 justify-content-center">
                <label htmlFor="endDate" className="col-2 col-form-label">
                  End Date
                </label>
                <div className="col-5">
                  <input
                    type="date"
                    id="endDate"
                    className="form-control"
                    name="endDate"
                    value={endDate}
                    onChange={onChange}
                  />
                </div>
              </div>

              <div className="form-group row my-3 justify-content-center">
                <label htmlFor="startTime" className="col-2 col-form-label">
                  Start Time
                </label>
                <div className="col-5">
                  <input
                    type="time"
                    id="startTime"
                    className="form-control"
                    name="startTime"
                    value={startTime}
                    onChange={onChange}
                  />
                </div>
              </div>

              <div className="form-group row my-3 justify-content-center">
                <label htmlFor="endTime" className="col-2 col-form-label">
                  End Time
                </label>
                <div className="col-5">
                  <input
                    type="time"
                    id="endTime"
                    className="form-control"
                    name="endTime"
                    value={endTime}
                    onChange={onChange}
                  />
                </div>
              </div>

              <div className="form-group row my-3 justify-content-center">
                <label htmlFor="slotDuration" className="col-2 col-form-label">
                  Slot Duration(minutes)
                </label>
                <div className="col-5">
                  <input
                    type="number"
                    id="slotDuration"
                    min="15"
                    max="30"
                    className="form-control"
                    name="slotDuration"
                    value={slotDuration}
                    onChange={onChange}
                  />
                </div>
              </div>

              <div className="form-group row my-3 justify-content-center">
                <label htmlFor="breakTime" className="col-2 col-form-label">
                  Break Time
                </label>
                <div className="col-5">
                  <input
                    type="time"
                    id="breakTime"
                    className="form-control"
                    name="breakTime"
                    value={breakTime}
                    onChange={onChange}
                  />
                </div>
              </div>

              <div className="form-group row my-3 justify-content-center">
                <label className="col-2 col-form-label">Holidays</label>
                <div className="col-5">
                  <div className="form-check form-check-inline">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="0"
                      name="holidays"
                      value="0"
                      onChange={handleChecklist}
                    />
                    <label className="form-check-label" htmlFor="0">
                      Sunday
                    </label>
                  </div>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="1"
                      name="holidays"
                      value="1"
                      onChange={handleChecklist}
                    />
                    <label className="form-check-label" htmlFor="1">
                      Monday
                    </label>
                  </div>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="2"
                      name="holidays"
                      value="2"
                      onChange={handleChecklist}
                    />
                    <label className="form-check-label" htmlFor="2">
                      Tuesday
                    </label>
                  </div>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="3"
                      name="holidays"
                      value="3"
                      onChange={handleChecklist}
                    />
                    <label className="form-check-label" htmlFor="3">
                      Wednesday
                    </label>
                  </div>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="4"
                      name="holidays"
                      value="4"
                      onChange={handleChecklist}
                    />
                    <label className="form-check-label" htmlFor="4">
                      Thursday
                    </label>
                  </div>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="5"
                      name="holidays"
                      value="5"
                      onChange={handleChecklist}
                    />
                    <label className="form-check-label" htmlFor="5">
                      Friday
                    </label>
                  </div>
                  <div className="form-check">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="6"
                      name="holidays"
                      value="6"
                      onChange={handleChecklist}
                    />
                    <label className="form-check-label" htmlFor="6">
                      Saturday
                    </label>
                  </div>
                </div>
              </div>
              <button
                className="btn btn-lg btn-primary text-uppercase mt-3 mb-5 offset-6"
                onClick={createTimeTable}
              >
                Submit
              </button>
            </form>
          </div>
        </Card.Body>
      </Card>
    </div>
  );
};

export default CreateAppointmentSlots;

import './App.css';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import About from './components/About';
import Contact from './components/Contact';
import HorizontalCard from './components/HorizontalCard';
import Services from './components/Services';
// import UserLogin from './components/UserLogin';
// import EmailForForgotPassword from './components/EmailForForgotPassword';
// import EnterToken from './components/EnterToken';
// import ResetPassword from './components/ResetPassword';
// import PatientSignUp from './components/PatientSignUp';
// import PatientDashboard from './components/PatientDashboard';
// import AdminDashboard from './components/AdminDashboard';
// import DoctorDashboard from './components/DoctorDashboard';
// import SpecializationListByCity from './components/SpecializationListByCity';
// import ShowCurrentAppointment from './components/ShowCurrentAppointment';
// import AppointmentHistory from './components/AppointmentHistory';
// import UpdatePatientProfile from './components/UpdatePatientProfile';
// import DoctorListForPatient from './components/DoctorListForPatient';
// import DoctorListForAdmin from './components/DoctorListForAdmin';
// import PatientList from './components/PatientList';
// import AddNewDoctor from './components/AddNewDoctor';
// import AddNewDonor from './components/AddNewDonor';
// import DonorList from './components/DonorList';
// import DoctorCurrentAppointments from './components/DoctorCurrentAppointments';
// import DoctorAppointmentHistory from './components/DoctorAppointmentHistory';
// import DoctorAppointmentSlots from './components/DoctorAppointmentSlots';
// import CreateAppointmentSlots from './components/CreateAppointmentSlots';
// import BookSlotForPatient from './components/BookSlotForPatient';
// import GetDonorsByCityAndBloodGroup from './components/GetDonorsByCityAndBloodGroup';
// import UpdateDoctorProfile from './components/UpdateDoctorProfile';
// import PatientDetailsForDoctor from './components/PatientDetailsForDoctor';
// import ShowAppointmentSlots from './components/ShowAppointmentSlots';
// import DoctorDetailsForPatient from './components/DoctorDetailsForPatient';
// import Services from './components/Services';
// import HorizontalCard from './components/HorizontalCard';
// import SignUpNewDoctor from './components/SignUpDoctor';

function App() {
  return (
    <div className="app">
      <Header title="CDAC Health & Research Center" />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        {/* <Route path="/patient-sign-up" element={<PatientSignUp />} />
        <Route path="/userLogin" element={<UserLogin />} />
        <Route path="/email-for-forgot-password" element={<EmailForForgotPassword />} />
        <Route path="/enter-token" element={<EnterToken />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        <Route path="/patientDashboard" element={<PatientDashboard />} />
        <Route path="/adminDashboard" element={<AdminDashboard />} />
        <Route path="/doctorDashboard" element={<DoctorDashboard />} />
        <Route path="/specialization-list-by-city" element={<SpecializationListByCity />} />
        <Route path="/current-app" element={<ShowCurrentAppointment />} />
        <Route path="/app-history" element={<AppointmentHistory />} />
        <Route path="/update-profile" element={<UpdatePatientProfile />} />
        <Route path="/doctor-list-patient" element={<DoctorListForPatient />} />
        <Route path="/doctor-list-admin" element={<DoctorListForAdmin />} />
        <Route path="/patientList" element={<PatientList />} />
        <Route path="/add-new-doctor" element={<AddNewDoctor />} />
        <Route path="/SignUp-new-doctor" element={<SignUpNewDoctor />} />
        <Route path="/add-new-donor" element={<AddNewDonor />} />
        <Route path="/donorList" element={<DonorList />} />
        <Route path="/doctor-current-app" element={<DoctorCurrentAppointments />} />
        <Route path="/doctor-app-history" element={<DoctorAppointmentHistory />} />
        <Route path="/doctor-appointment-slots" element={<DoctorAppointmentSlots />} />
        <Route path="/create-appointment-slots" element={<CreateAppointmentSlots />} />
        <Route path="/book-slot-for-patient" element={<BookSlotForPatient />} />
        <Route path="/get-donors-by-city-and-blood-group" element={<GetDonorsByCityAndBloodGroup />} />
        <Route path="/update-doctor-profile" element={<UpdateDoctorProfile />} />
        <Route path="/patient-details-for-doctor" element={<PatientDetailsForDoctor />} />
        <Route path="/show-appointment-slots-doctor" element={<ShowAppointmentSlots />} />
        <Route path="/doctor-details-for-patient" element={<DoctorDetailsForPatient />} /> */}
      </Routes>
      <HorizontalCard />
      <Services />
      <Footer />
    </div>
  );
}

export default App;

// services/doctorService.js
import axios from 'axios';

const DOCTOR_BASE_URL = 'http://localhost:8080/doctor';

const DoctorService = {
    createAppointmentSlots: async (doctorTimeTable, doctorId) => {
        try {
            const response = await axios.post(DOCTOR_BASE_URL + '/createAppointmentSlot/' + doctorId, doctorTimeTable);
            console.log('Appointment slots created:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error creating appointment slots:', error);
            throw error;
        }
    },

    getDoctorById: async (doctorId) => {
        try {
            const response = await axios.get(DOCTOR_BASE_URL + '/getDoctorDetails/' + doctorId);
            console.log('Doctor details fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching doctor details:', error);
            throw error;
        }
    },

    updateDoctorDetails: async (id, doctor) => {
        try {
            const response = await axios.put(DOCTOR_BASE_URL + '/updateDoctor/' + id, doctor);
            console.log('Doctor details updated:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error updating doctor details:', error);
            throw error;
        }
    },

    doctorLogout: () => {
        try {
            sessionStorage.removeItem("doctor");
            console.log('Doctor logged out successfully');
        } catch (error) {
            console.error('Error during logout:', error);
            throw error;
        }
    }
};

export default DoctorService;

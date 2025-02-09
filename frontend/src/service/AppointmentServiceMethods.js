// services/appointmentService.js
import axios from 'axios';

const APPOINTMENT_BASE_URL = 'http://localhost:8080/appointment';

const AppointmentService = {
    getSpecializationListByCity: async (city) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + "/specialization/" + city);
            console.log('Specializations fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching specializations:', error);
            throw error;
        }
    },

    getDoctorsBySpecializationAndCity: async (specialization, city) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + "/search/" + specialization + "/" + city);
            console.log('Doctors fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching doctors:', error);
            throw error;
        }
    },

    getAllCurrentAppointments: async (patientId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + "/currAppointmentP/" + patientId);
            console.log('Current appointments fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching current appointments:', error);
            throw error;
        }
    },

    getAllAppointmentsHistory: async (patientId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/appointementHistoryP/' + patientId);
            console.log('Appointment history fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching appointment history:', error);
            throw error;
        }
    },

    bookAppointmentForPatient: async (doctorId, patientId, time) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/bookAppointment/' + doctorId + '/' + patientId + '/' + time);
            console.log('Appointment booked:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error booking appointment:', error);
            throw error;
        }
    },

    cancelAppointment: async (appointmentId) => {
        try {
            const response = await axios.delete(APPOINTMENT_BASE_URL + '/cancelAppointment/' + appointmentId);
            console.log('Appointment cancelled:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error cancelling appointment:', error);
            throw error;
        }
    },

    getAllAppointmentSlots: async (doctorId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/getAppointmentSlots/' + doctorId);
            console.log('Appointment slots fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching appointment slots:', error);
            throw error;
        }
    },

    getCurrentAppointmentsForDoctor: async (doctorId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/currAppointmentD/' + doctorId);
            console.log('Current appointments for doctor fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching current appointments for doctor:', error);
            throw error;
        }
    },

    getPatientByAppointmentId: async (appointmentId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/patient/' + appointmentId);
            console.log('Patient details fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching patient details:', error);
            throw error;
        }
    },

    getDoctorByAppointmentId: async (appointmentId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/doctor/' + appointmentId);
            console.log('Doctor details fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching doctor details:', error);
            throw error;
        }
    },

    getAppoinmentsHistoryOfPatientForDoctor: async (doctorId, patientId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/appointementHistoryD/' + doctorId + '/' + patientId);
            console.log('Appointment history fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching appointment history:', error);
            throw error;
        }
    },

    getAllAppoinmentsHistoryForDoctor: async (doctorId) => {
        try {
            const response = await axios.get(APPOINTMENT_BASE_URL + '/appointementHistoryD/' + doctorId);
            console.log('All appointment history fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching all appointment history:', error);
            throw error;
        }
    }
};

export default AppointmentService;

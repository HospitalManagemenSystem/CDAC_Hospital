// services/patientService.js
import axios from 'axios';

const PATIENT_API_BASE_URL = 'http://localhost:8080/patient';

const PatientService = {
    addPatient: async (patient) => {
        try {
            const response = await axios.post(PATIENT_API_BASE_URL + "/patientSignUp", patient);
            console.log('Patient added successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error adding patient:', error);
            throw error;
        }
    },

    getPatientById: async (patientId) => {
        try {
            const response = await axios.get(PATIENT_API_BASE_URL + '/getPatientDetails/' + patientId);
            console.log('Patient details fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching patient details:', error);
            throw error;
        }
    },

    updatePatientDetails: async (id, patient) => {
        try {
            const response = await axios.put(PATIENT_API_BASE_URL + '/updatePatientDetails/' + id, patient);
            console.log('Patient details updated:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error updating patient details:', error);
            throw error;
        }
    },

    logoutPatient: () => {
        try {
            sessionStorage.removeItem("patient");
            console.log('Patient logged out successfully');
        } catch (error) {
            console.error('Error during logout:', error);
            throw error;
        }
    }
};

export default PatientService;

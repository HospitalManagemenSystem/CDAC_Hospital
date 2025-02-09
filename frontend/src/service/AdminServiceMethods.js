// services/adminService.js
import axios from 'axios';

const ADMIN_API_BASE_URL = 'http://localhost:8080/admin';

const AdminService = {
    logoutAdmin: () => {
        try {
            sessionStorage.removeItem("admin");
            console.log('Admin logged out successfully');
        } catch (error) {
            console.error('Error during logout:', error);
            throw error;
        }
    },

    addNewDoctor: async (doctor) => {
        try {
            const response = await axios.post(ADMIN_API_BASE_URL + '/doctorSignUp', doctor);
            console.log('Doctor added successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error adding doctor:', error);
            throw error;
        }
    },

    fetchAllDoctors: async () => {
        try {
            const response = await axios.get(ADMIN_API_BASE_URL + '/getAllDoctors');
            console.log('All doctors fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching doctors:', error);
            throw error;
        }
    },

    deleteDoctor: async (doctorId) => {
        try {
            const response = await axios.delete(ADMIN_API_BASE_URL + '/removeDoctor/' + doctorId);
            console.log('Doctor deleted successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error deleting doctor:', error);
            throw error;
        }
    },

    fetchAllPatients: async () => {
        try {
            const response = await axios.get(ADMIN_API_BASE_URL + '/getAllPatients');
            console.log('All patients fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching patients:', error);
            throw error;
        }
    },

    deletePatient: async (patientId) => {
        try {
            const response = await axios.delete(ADMIN_API_BASE_URL + '/removePatient/' + patientId);
            console.log('Patient deleted successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error deleting patient:', error);
            throw error;
        }
    },

    saveDonor: async (donor) => {
        try {
            const response = await axios.post(ADMIN_API_BASE_URL + '/bloodDonor', donor);
            console.log('Donor saved successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error saving donor:', error);
            throw error;
        }
    },

    fetchAllBloodDonors: async () => {
        try {
            const response = await axios.get(ADMIN_API_BASE_URL + '/searchDonors');
            console.log('All blood donors fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching blood donors:', error);
            throw error;
        }
    }
};

export default AdminService;

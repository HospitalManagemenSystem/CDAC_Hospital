// services/bloodDonorService.js
import axios from 'axios';

const BLOOD_DONOR_BASE_URL = 'http://localhost:8080/blood_donation';

const BloodDonorService = {
    getAllBloodDonorsByCityAndBloodGroup: async (city, bloodGroup) => {
        try {
            const response = await axios.get(BLOOD_DONOR_BASE_URL + '/search/' + city + '/' + bloodGroup);
            console.log('Blood donors fetched:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error fetching blood donors:', error);
            throw error;
        }
    }
};

export default BloodDonorService;

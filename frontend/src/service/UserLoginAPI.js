// services/userLoginService.js
import axios from 'axios';

const USER_LOGIN_BASE_URL = 'http://localhost:8080/home';

const UserLoginService = {
    userLogin: async (user) => {
        try {
            const response = await axios.post(USER_LOGIN_BASE_URL + '/userLogin', user);
            console.log('User logged in successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error during login:', error);
            throw error;
        }
    },

    generateToken: async (userEmail) => {
        try {
            const response = await axios.get(USER_LOGIN_BASE_URL + '/generateToken/' + userEmail);
            console.log('Token generated successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error generating token:', error);
            throw error;
        }
    },

    resetPassword: async (userEmail, userNewPassword) => {
        try {
            const response = await axios.post(USER_LOGIN_BASE_URL + '/resetPassword/' + userEmail + '/' + userNewPassword);
            console.log('Password reset successfully:', response.data);
            return response.data;
        } catch (error) {
            console.error('Error resetting password:', error);
            throw error;
        }
    }
};

export default UserLoginService;

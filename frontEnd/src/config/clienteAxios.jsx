import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL || 'https://faster-envios-backend.up.railway.app/'

const clienteAxios = axios.create({
    baseURL: API_URL
});

export default clienteAxios;
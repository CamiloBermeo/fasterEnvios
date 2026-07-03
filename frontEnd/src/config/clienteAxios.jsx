import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL || 'http://faster-envio-alb-115006143.us-east-1.elb.amazonaws.com/api/v1'

const clienteAxios = axios.create({
    baseURL: API_URL
});

export default clienteAxios;
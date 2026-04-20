import axios from 'axios';

const clienteAxios = axios.create({
    baseURL: "http://localhost:8080/api/v1"
});

export default clienteAxios;
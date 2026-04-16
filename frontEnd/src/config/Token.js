import clienteAxios from "./ClienteAxios";

const tokenAuth = token => {
    if (token){
        //si hay token lo agrego a las cabeceras
        clienteAxios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

    }else{
        //si no hay token elimino la cabecera
        delete clienteAxios.defaults.headers.common["Authorization"];
    }
}
export default tokenAuth;
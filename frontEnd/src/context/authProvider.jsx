//Este archivo hará tres cosas: autenticar al usuario, verificar el token al cargar la app y cerrar sesión.

import { useState, useEffect, createContext } from "react";
import clienteAxios from "../config/clienteAxios.jsx";
import tokenAuth from "../config/token.jsx";

const AuthContext = createContext();

const AuthProvider = ({ children }) => {

    const [auth, guardarAuth] = useState({});
    const [cargando, guardarCargando] = useState(true);

    useEffect(() => {
        const autenticarUsuario = async () => {
            const token = localStorage.getItem("token");
            if (!token) {
                guardarCargando(false);
                return;
            }
            tokenAuth(token); //agrego el token a las cabeceras
            try {
                //llamada al back para obtener los datos del usuario
                const { data } = await clienteAxios.get("/auth/profile");
                guardarAuth(data);
            } catch (error) {
                console.log(error.response?.data?.msg || "Error al autenticar al usuario");
                guardarAuth({});//si el token no es válido o hay un error, limpio el token
            }
            guardarCargando(false);
        }
        autenticarUsuario();
    }, []);
    return (
        <AuthContext.Provider
            value={{
                auth,
                guardarAuth,
                cargando
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};

export { AuthProvider };
export default AuthContext;
import React, { useState } from "react";
import clienteAxios from "../../config/clienteAxios.jsx";
import IconoIr from "../../assets/home_icono.svg";
import useAuth from "../../hooks/useAuth";
import { Link, useNavigate } from "react-router-dom";


const LoginAliado = () => {

    const [usuario, guardarUsuario] = useState({
        email: "",
        password: ""
    });
    const { guardarAuth } = useAuth();

    const [alerta, guardarAlerta] = useState(null);//state para mostrar alerta
    const [cargando, guardarCargando] = useState(false);//state para mostrar el spinner
    const navigate = useNavigate();
    //extraer el usuario
    const { email, password } = usuario;

    const onChange = e => {
        guardarUsuario({
            ...usuario,
            [e.target.name]: e.target.value
        })
    }

    //cuando el usuario quiere iniciar sesion
    const onSubmit = async e => {
        e.preventDefault();

        //validar que no haya campos vacios
        if (email.trim() === "" || password.trim() === "") {
            guardarAlerta("Todos los campos son obligatorios");
            return;
        }
        if (password.length < 6) {
            guardarAlerta("La contraseña debe tener al menos 6 caracteres");
            return;
        }
        guardarAlerta(null);
        guardarCargando(true);

        //intento de inicio de sesion
        try {
           
                //limpio el token viejo antes de hacer login
                localStorage.removeItem("token");
                delete clienteAxios.defaults.headers.common["Authorization"];

                //llamada al back
                const respuesta = await clienteAxios.post("/auth/login", usuario);
                //guardar el token en localStorage
                localStorage.setItem("token", respuesta.data.token);
                guardarAuth(respuesta.data.usuario);
                //redirecciono al dashboard 
                navigate("/dashboard-aliado");
            


        } catch (error) {
            //utilizo los mensajes de error que me envia el back
            const mensaje = error.response?.data?.msg || "Hubo un error al iniciar sesión";
            guardarAlerta(mensaje);
        } finally {
            guardarCargando(false);
        }
    }

    return (

        <div className="flex items-center justify-center min-h-screen bg-gray-100 font-sans text-gray-800 leading-relaxed">

            <div className="flex flex-col max-w-[450px]   bg-white rounded-xl p-8 border border-gray-200 shadow-lg sm:p-6">

                <div className="flex flex-col pb-6 items-center justify-around login-header">
                    <h2 className="text-2xl font-bold text-slate-800 mb-2 sm:text-xl">Bienvenido de Nuevo</h2>
                    <p className="text-slate-500 text-sm">Ingresa tus credenciales para acceder a tu cuenta</p>
                </div>

                {alerta && (
                    <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                        {alerta}
                    </div>
                )}
                <form className="flex flex-col gap-4 login-form" id="loginForm" noValidate onSubmit={onSubmit}>
                    <div className="form-group mb-5">
                        <div className="input-wrapper relative">
                            <input type="email" id="email" placeholder="Email" value={email} name="email" required autoComplete="email" onChange={onChange} className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400" />
                        </div>
                        <span className="error-message" id="emailError"></span>
                    </div>

                    <div className="form-group mb-5">
                        <div className="input-wrapper relative password-wrapper">
                            <input type="password" id="password" placeholder="Contraseña" value={password} name="password" required autoComplete="current-password" onChange={onChange} className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 pr-12 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400" />
                            <button type="button" className="password-toggle" id="passwordToggle" aria-label="Toggle password visibility">
                                <span className="eye-icon"></span>
                            </button>
                        </div>
                        <span className="error-message" id="passwordError"></span>
                    </div>

                    <div className=" form-options flex justify-between items-center mb-6 flex-wrap gap-3 sm:flex-col sm:items-start sm:gap-4">
                        <label className="remember-wrapper flex items-center gap-2 cursor-pointer">
                            <input type="checkbox" id="remember" name="remember" className="peer hidden" />
                            <span className="checkbox-label text-slate-500 text-sm cursor-pointer select-none flex items-center gap-2">
                                <span className="checkmark w-4 h-4 border-2 border-gray-300 rounded flex items-center justify-center transition-all duration-200 flex-shrink-0 bg-white peer-checked:bg-indigo-500 peer-checked:border-indigo-500">
                                    <span className="hidden peer-checked:block text-white text-xs font-bold">✓</span>
                                </span>
                                Recordarme
                            </span>
                        </label>
                        <a href="#" className="forgot-password text-indigo-500 no-underline text-sm font-medium transition-colors duration-200 hover:text-indigo-600">¿Olvidaste tu contraseña?</a>
                    </div>

                    <button type="submit"
                        disabled={cargando}
                        className={`w-full bg-indigo-500 border-none rounded-lg px-6 py-3 text-white text-base font-semibold cursor-pointer transition-all duration-200 relative mb-6 hover:bg-indigo-600 active:translate-y-0.5
                    ${cargando ? 'opacity-50 cursor-not-allowed' : 'cursor-pointer hover:bg-indigo-600 active:translate-y-0.5'}`} >

                        <span className={`btn-text transition-opacity duration-200 ${cargando ? 'opacity-0' : 'opacity-100'}`}
                        >Iniciar Sesión</span>

                        <span className={`btn-loader absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-4 h-4 border-2 border-transparent border-t-white rounded-full opacity-0 animate-spin transition-opacity duration-200
                        ${cargando ? 'opacity-100' : 'opacity-0'}`}>
                        </span>
                    </button>
                </form>

                <Link className="flex flex-col items-center mx-auto mt-5 gap-1 w-28 bg-transparent border-none text-teal-600 text-xs cursor-pointer p-2 rounded-lg transition-colors duration-200 hover:text-orange-500" to="/home" type="button">
                    <img src={IconoIr} alt="Regresar" className="size-10 transition-all duration-300 text-current fill-current" />
                    Inicio
                </Link>

            </div>
        </div>

    );
}
export default LoginAliado;
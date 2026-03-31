import React, { useState } from "react";
import IconoIr from "../../assets/home_icono.svg";
import { Link } from "react-router-dom";


const Login = () => {

    const [usuario, guardarUsuario] = useState({
        email: "",
        password: ""
    });

    //extraer el usuario
    const {email,password} = usuario;

    const onChange = e =>{
        guardarUsuario({
            ...usuario,
            [e.target.name]: e.target.value
        })
    }

    //cuando el usuario quiere iniciar sesion
    const onSubmit = e =>{
        e.preventDefault();

        //validar que no haya campos vacios
        
        //pasarlo al action
    }

    return (

        <div className="flex items-center justify-center min-h-screen bg-gray-100 font-sans text-gray-800 leading-relaxed">

            <div className=" bg-white rounded-xl p-8 border border-gray-200 shadow-lg sm:p-6">

                <div className="pb-6 text-center">
                    <h2 className="text-2xl font-bold text-slate-800 mb-2 sm:text-xl">Iniciar Sesión</h2>
                    <p className="text-slate-500 text-sm">Ingresa tus credenciales para acceder a tu cuenta</p>
                </div>
                

                <form className="login-form" id="loginForm" noValidate onSubmit={onSubmit}>
                    <div className="form-group mb-5">
                        <div className="input-wrapper relative">
                            <input type="email" id="email" value={email} name="email" required autoComplete="email" onChange={onChange} className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"/>
                            <label htmlFor="email" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Correo</label>
                        </div>
                        <span className="error-message" id="emailError"></span>
                    </div>

                    <div className="form-group mb-5">
                        <div className="input-wrapper relative password-wrapper">
                            <input type="password" id="password" value={password} name="password" required autoComplete="current-password" onChange={onChange} className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 pr-12 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"/>
                            <label htmlFor="password" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Contraseña</label>
                            <button type="button" className="password-toggle" id="passwordToggle" aria-label="Toggle password visibility">
                                <span className="eye-icon"></span>
                            </button>
                        </div>
                        <span className="error-message" id="passwordError"></span>
                    </div>

                    <div className="form-options flex justify-between items-center mb-6 flex-wrap gap-3 sm:flex-col sm:items-start sm:gap-4">
                        <label className="remember-wrapper flex items-center gap-2 cursor-pointer">
                            <input type="checkbox" id="remember" name="remember" className="peer hidden"/>
                            <span className="checkbox-label text-slate-500 text-sm cursor-pointer select-none flex items-center gap-2">
                                <span className="checkmark w-4 h-4 border-2 border-gray-300 rounded flex items-center justify-center transition-all duration-200 flex-shrink-0 bg-white peer-checked:bg-indigo-500 peer-checked:border-indigo-500">
                                    <span className="hidden peer-checked:block text-white text-xs font-bold">✓</span>
                                </span>
                                Recordarme
                            </span>
                        </label>
                        <a href="#" className="forgot-password text-indigo-500 no-underline text-sm font-medium transition-colors duration-200 hover:text-indigo-600">¿Olvidaste tu contraseña?</a>
                    </div>

                    <button type="submit" className="w-full bg-indigo-500 border-none rounded-lg px-6 py-3 text-white text-base font-semibold cursor-pointer transition-all duration-200 relative mb-6 hover:bg-indigo-600 active:translate-y-0.5">
                        <span className="btn-text transition-opacity duration-200">Iniciar Sesión</span>
                        <span className="btn-loader absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-4 h-4 border-2 border-transparent border-t-white rounded-full opacity-0 animate-spin transition-opacity duration-200"></span>
                    </button>
                </form>

                <div className="signup-link">
                    <p className="text-slate-500 text-sm">¿No tienes una cuenta? <a href="/register" className="text-indigo-500 no-underline font-medium transition-colors duration-200 hover:text-indigo-600">Registrarse</a></p>
                </div>
                <Link className="btn boton-regresar flex flex-col items-center mx-auto mt-5 gap-1 w-[100px] bg-transparent border-none text-teal-600 text-xs cursor-pointer p-2 rounded-lg transition-colors duration-200 hover:text-orange-500" to="/home" type="button">
                    <img src={IconoIr} alt="Regresar" className="w-7.5 h-7.5 transition-all duration-300" />
                    Inicio
                </Link>

            </div>
        </div>

    );
}
export default Login;
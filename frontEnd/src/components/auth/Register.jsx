import React, { useState } from "react";
import IconoIr from "../../assets/home_icono.svg";
import { Link } from "react-router-dom";

const Register = () => {
  const [usuario, guardarUsuario] = useState({
    name: "",
    lastName: "",
    city: "",
    contact: "",
    cedula: "",
    cedulaConfirmation: "",
    email: "",
    emailConfirmation: "",
    password: "",
    passwordConfirmation: "",
  });

  //extraer el usuario
  const { name, lastName, city, contact, cedula, cedulaConfirmation, email, emailConfirmation,password, passwordConfirmation } = usuario;

  const onChange = (e) => {
    guardarUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  //cuando el usuario quiere iniciar sesion
  const onSubmit = (e) => {
    e.preventDefault();

    //validar que no haya campos vacios

    //Password minimo de 6 caracteres

    //revisar que los password sean iguales

    //revisar que los correos sean iguales

    //revisar que las cedulas sean iguales

    //pasarlo al action
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 font-sans text-gray-800 leading-relaxed pb-8 pt-8">

      <div className="max-w-[450px] w-full bg-white rounded-xl p-8 border border-gray-200 shadow-lg sm:p-6">
        <div className="login-header text-center mb-8">
          <h2 className="text-2xl font-bold text-slate-800 mb-2 sm:text-xl">Registrarse</h2>
          <p className="text-slate-500 text-sm">Crea tu cuenta para acceder a tu panel de control</p>
        </div>

        <form
          className="login-form"
          id="loginForm"
          noValidate
          onSubmit={onSubmit}
        >
          <div className="form-group mb-5 form-dual flex gap-4">
            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="name"
                value={name}
                name="name"
                required
                autoComplete="name"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
              />
              <label htmlFor="name" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Nombre</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="nameError"></span>
          
            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="lastName"
                value={lastName}
                name="lastName"
                required
                autoComplete="lastName"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="lastName" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Apellido</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="lastNameError"></span>
          </div>
          
          <div className="form-group mb-5 form-dual flex gap-4">
            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="city"
                value={city}
                name="city"
                required
                autoComplete="city"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="city" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Ciudad</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="cityError"></span>
          
            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="contact"
                value={contact}
                name="contact"
                required
                autoComplete="contact"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="contact" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">N° Celular</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="contactError"></span>
          </div>
          <div className="form-group mb-5 form-dual flex gap-4">
            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="cedula"
                value={cedula}
                name="cedula"
                required
                autoComplete="cedula"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="cedula" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Cédula</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="cedulaError"></span>
          
            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="cedulaConfirmation"
                value={cedulaConfirmation}
                name="cedulaConfirmation"
                required
                autoComplete="cedulaConfirmation"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="cedulaConfirmation" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Confirmar Cédula</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="cedulaConfirmationError"></span>
          </div>

          <div className="form-group mb-5">
            <div className="input-wrapper relative">
              <input
                type="email"
                id="email"
                value={email}
                name="email"
                required
                autoComplete="email"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="email" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Correo</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="emailError"></span>
          </div>
          <div className="form-group mb-5">
            <div className="input-wrapper relative">
              <input
                type="email"
                id="emailConfirmation"
                value={emailConfirmation}
                name="emailConfirmation"
                required
                autoComplete="emailConfirmation"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="emailConfirmation" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Confirmar Correo</label>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="emailConfirmationError"></span>
          </div>

          <div className="form-group mb-5">
            <div className="input-wrapper relative password-wrapper">
              <input
                type="password"
                id="password"
                value={password}
                name="password"
                required
                autoComplete="current-password"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 pr-12 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="password" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Contraseña</label>
              <button
                type="button"
                className="password-toggle"
                id="passwordToggle"
                aria-label="Toggle password visibility"
              >
                <span className="eye-icon"></span>
              </button>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="passwordError"></span>
          </div>
<div className="form-group mb-5">
            <div className="input-wrapper relative password-wrapper">
              <input
                type="password"
                id="passwordConfirmation"
                value={passwordConfirmation}
                name="passwordConfirmation"
                required
                autoComplete="current-password"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 pr-12 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
              <label htmlFor="passwordConfirmation" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Confirmar Contraseña</label>
              <button
                type="button"
                className="password-toggle"
                id="passwordToggle"
                aria-label="Toggle password visibility"
              >
                <span className="eye-icon"></span>
              </button>
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="passwordConfirmationError"></span>
          </div>

          <button type="submit" className="w-full bg-indigo-500 border-none rounded-lg px-6 py-3 text-white text-base font-semibold cursor-pointer transition-all duration-200 relative mb-6 hover:bg-indigo-600 active:translate-y-0.5">
            <span className="btn-text transition-opacity duration-200">Registrarme</span>
            <span className="btn-loader absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-4 h-4 border-2 border-transparent border-t-white rounded-full opacity-0 animate-spin transition-opacity duration-200"></span>
          </button>
        </form>

        <div className="signup-link">
          <p className="text-slate-500 text-sm">
            ¿Ya tienes una cuenta? <a href="/login" className="text-indigo-500 no-underline font-medium transition-colors duration-200 hover:text-indigo-600">Iniciar sesión</a>
          </p>
        </div>
        <Link className="btn boton-regresar flex flex-col items-center mx-auto mt-5 gap-1 w-[100px] bg-transparent border-none text-teal-600 text-xs cursor-pointer p-2 rounded-lg transition-colors duration-200 hover:text-orange-500" to="/home" type="button">
          <img src={IconoIr} alt="Regresar" className="w-7.5 h-7.5 transition-all duration-300" />
          Inicio
        </Link>

  
      </div>
    </div>
  );
};
export default Register;

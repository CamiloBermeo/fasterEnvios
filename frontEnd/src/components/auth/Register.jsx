import React, { useState } from "react";
import IconoIr from "../../assets/home_icono.svg";
import { Link, useNavigate} from "react-router-dom";
import clienteAxios from "../../config/clienteAxios";

const Register = () => {
  const navigate = useNavigate();
  const [usuario, guardarUsuario] = useState({
    name: "",
    lastName: "",
    city: "",
    phoneNumber: "",
    identityDocument: "",
    cedulaConfirmation: "",
    email: "",
    emailConfirmation: "",
    password: "",
    passwordConfirmation: "",
  });
  const [alerta, guardarAlerta] = useState(null);//state para mostrar alerta
  const [cargando, guardarCargando] = useState(false);//state para mostrar el spinner
  const [exitoso, guardarExitoso] = useState(false);//state cuando ha salido bien

  //extraer el usuario
  const { name, lastName, city, phoneNumber, identityDocument, email, password, passwordConfirmation, cedulaConfirmation, emailConfirmation } = usuario;

  const onChange = (e) => {
    guardarUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  //cuando el usuario quiere iniciar sesion
  const onSubmit = async e => {
    e.preventDefault();

    //validar que no haya campos vacios

    if (name.trim() === "" || lastName.trim() === "" || city.trim() === "" || phoneNumber.trim() === "" || identityDocument.trim() === "" || email.trim() === "" || password.trim() === "") {
      // Mostrar mensaje de error
      guardarAlerta("Todos los campos son obligatorios");
      return;
    }
    //Password minimo de 6 caracteres
    if (password.length < 6) {
      guardarAlerta("La contraseña debe tener al menos 6 caracteres");
      return;
    }
    //revisar que los password sean iguales
    if (password !== passwordConfirmation) {
      guardarAlerta("Las contraseñas no coinciden");
      return;
    }
    //revisar que los correos sean iguales
    if (email !== emailConfirmation) {
      guardarAlerta("Los correos no coinciden");
      return;
    }
    //revisar que las cedulas sean iguales
    if (identityDocument !== cedulaConfirmation) {
      guardarAlerta("Las cédulas no coinciden");
      return;
    }
    guardarAlerta(null);
    guardarCargando(true);

    //peticion al backend

    try {
      //limpio cualquier token que interfiera 
      localStorage.removeItem("token")
      delete clienteAxios.defaults.headers.common["Authorization"];
      //llamado al back
      const respuesta = await clienteAxios.post("/auth/register", usuario);
      console.log(respuesta.data);
      guardarExitoso("Registro exitoso");
      localStorage.setItem("token", respuesta.data.token)
      guardarCargando(false);
      navigate("/home-cliente")
    } catch (error) {
      console.log(usuario)
      const mensaje = error.response?.data?.msg || "Hubo un error al iniciar sesión";
      guardarAlerta(mensaje);
    } finally {
      guardarCargando(false);
    }

  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 font-sans text-gray-800 leading-relaxed pb-8 pt-8">

      <div className="max-w-[450px] w-full bg-white rounded-xl p-8 border border-gray-200 shadow-lg sm:p-6">
        <div className="login-header text-center mb-8">
          <h2 className="text-2xl font-bold text-slate-800 mb-2 sm:text-xl">Registrarse</h2>
          <p className="text-slate-500 text-sm">Crea tu cuenta para acceder a tu panel de control</p>
        </div>
        {alerta && (
          <div className="bg-red-100 border-l-4 border-red-500 text-red-700 p-3 mb-4 text-sm">
            {alerta}
          </div>
        )}
        {exitoso && (
          <div
          className="bg-green-400 border-l-4 border-green-600 text-green-950 p-3 mb-4 text-sm"
          >
            {exitoso}
          </div>
        )
        }
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
                placeholder="Nombre"
                required
                autoComplete="name"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
              />
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="nameError"></span>

            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="lastName"
                value={lastName}
                name="lastName"
                placeholder="Apellidos"
                required
                autoComplete="lastName"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
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
                placeholder="Ciudad"
                required
                autoComplete="city"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="cityError"></span>

            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="phoneNumber"
                value={phoneNumber}
                name="phoneNumber"
                placeholder="Celular"
                required
                autoComplete="phoneNumber"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="contactError"></span>
          </div>
          <div className="form-group mb-5 form-dual flex gap-4">
            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="identityDocument"
                value={identityDocument}
                name="identityDocument"
                placeholder="Cédula"
                required
                autoComplete="identityDocument"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
            </div>
            <span className="block text-red-500 text-xs font-medium mt-1 ml-1 opacity-0 -translate-y-1 transition-all duration-200" id="cedulaError"></span>

            <div className="input-wrapper relative w-1/2">
              <input
                type="text"
                id="cedulaConfirmation"
                value={cedulaConfirmation}
                name="cedulaConfirmation"
                placeholder="Confirmar Cédula"
                required
                autoComplete="cedulaConfirmation"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
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
                placeholder="Correo"
                required
                autoComplete="email"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
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
                placeholder="Confirmar Correo"
                required
                autoComplete="emailConfirmation"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
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
                placeholder="Contraseña"
                required
                autoComplete="current-password"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 pr-12 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
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
                placeholder="Confirmar Contraseña"
                required
                autoComplete="current-password"
                onChange={onChange}
                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 pr-12 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer"
              />
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

          <button
            type="submit"
            //cargando inicia como false
            disable={cargando}
            className={`w-full bg-indigo-500 border-none rounded-lg px-6 py-3 text-white text-base font-semibold cursor-pointer transition-all duration-200 relative mb-6 hover:bg-indigo-600 active:translate-y-0.5 
              ${cargando ? 'opacity-50 cursor-not-allowed':'cursor-pointer hover:bg-indigo-600 active:translate-y-0.5' }`}
              >
          
          <span 
          //cuando cargando es true desaparece el texto y se vuelve opaco el btn */
          className={`transition-opacity duration-200 ${cargando ? 'opacity-0' : 'opacity-100'}`}>Registrarme</span>
          
          <span 
          /*este span es la ruedita que se activa cuando cargando es true */
          className={`btn-loader absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-4 h-4 border-2 border-transparent border-t-white rounded-full animate-spin transition-opacity duration-200 
                        ${cargando ? 'opacity-100' : 'opacity-0'}`}>
                        </span>
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
    </div >
  );
};
export default Register;

import React, { useState } from "react";
import IconoIr from "../../assets/home_icono.svg";
import "./Login.css";
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

    //pasarlo al action
  };

  return (
    <div className="login-container register-container">
      <div className="login-card">
        <div className="login-header">
          <h2>Registrarse</h2>
          <p>Crea tu cuenta para acceder a tu panel de control</p>
        </div>

        <form
          className="login-form"
          id="loginForm"
          noValidate
          onSubmit={onSubmit}
        >
          <div className="form-group form-dual">
            <div className="input-wrapper">
              <input
                type="name"
                id="name"
                value={name}
                name="name"
                required
                autoComplete="name"
                onChange={onChange}
              />
              <label htmlFor="name">Nombre</label>
            </div>
            <span className="error-message" id="nameError"></span>
          
            <div className="input-wrapper">
              <input
                type="text"
                id="lastName"
                value={lastName}
                name="lastName"
                required
                autoComplete="lastName"
                onChange={onChange}
              />
              <label htmlFor="lastName">Apellido</label>
            </div>
            <span className="error-message" id="lastNameError"></span>
          </div>
          <div className="form-group form-dual">
            <div className="input-wrapper">
              <input
                type="text"
                id="city"
                value={city}
                name="city"
                required
                autoComplete="city"
                onChange={onChange}
              />
              <label htmlFor="city">Ciudad</label>
            </div>
            <span className="error-message" id="cityError"></span>
          
            <div className="input-wrapper">
              <input
                type="text"
                id="contact"
                value={contact}
                name="contact"
                required
                autoComplete="contact"
                onChange={onChange}
              />
              <label htmlFor="contact">N° Celular</label>
            </div>
            <span className="error-message" id="contactError"></span>
          </div>
          <div className="form-group form-dual">
            <div className="input-wrapper">
              <input
                type="text"
                id="cedula"
                value={cedula}
                name="cedula"
                required
                autoComplete="cedula"
                onChange={onChange}
              />
              <label htmlFor="cedula">Cédula</label>
            </div>
            <span className="error-message" id="cedulaError"></span>
          
            <div className="input-wrapper">
              <input
                type="text"
                id="cedulaConfirmation"
                value={cedulaConfirmation}
                name="cedulaConfirmation"
                required
                autoComplete="cedulaConfirmation"
                onChange={onChange}
              />
              <label htmlFor="cedulaConfirmation">Confirmar Cédula</label>
            </div>
            <span className="error-message" id="cedulaConfirmationError"></span>
          </div>

          <div className="form-group">
            <div className="input-wrapper">
              <input
                type="email"
                id="email"
                value={email}
                name="email"
                required
                autoComplete="email"
                onChange={onChange}
              />
              <label htmlFor="email">Correo</label>
            </div>
            <span className="error-message" id="emailError"></span>
          </div>
          <div className="form-group">
            <div className="input-wrapper">
              <input
                type="email"
                id="emailConfirmation"
                value={emailConfirmation}
                name="emailConfirmation"
                required
                autoComplete="emailConfirmation"
                onChange={onChange}
              />
              <label htmlFor="emailConfirmation">Confirmar Correo</label>
            </div>
            <span className="error-message" id="emailConfirmationError"></span>
          </div>

          <div className="form-group">
            <div className="input-wrapper password-wrapper">
              <input
                type="password"
                id="password"
                value={password}
                name="password"
                required
                autoComplete="current-password"
                onChange={onChange}
              />
              <label htmlFor="password">Contraseña</label>
              <button
                type="button"
                className="password-toggle"
                id="passwordToggle"
                aria-label="Toggle password visibility"
              >
                <span className="eye-icon"></span>
              </button>
            </div>
            <span className="error-message" id="passwordError"></span>
          </div>
<div className="form-group">
            <div className="input-wrapper password-wrapper">
              <input
                type="password"
                id="passwordConfirmation"
                value={passwordConfirmation}
                name="passwordConfirmation"
                required
                autoComplete="current-password"
                onChange={onChange}
              />
              <label htmlFor="passwordConfirmation">Confirmar Contraseña</label>
              <button
                type="button"
                className="password-toggle"
                id="passwordToggle"
                aria-label="Toggle password visibility"
              >
                <span className="eye-icon"></span>
              </button>
            </div>
            <span className="error-message" id="passwordConfirmationError"></span>
          </div>

          <button type="submit" className="login-btn">
            <span className="btn-text">Registrarme</span>
            <span className="btn-loader"></span>
          </button>
        </form>

        <div className="signup-link">
          <p>
            ¿Ya tienes una cuenta? <a href="/login">Iniciar sesión</a>
          </p>
        </div>
        <Link className="btn boton-regresar" to="/home" type="button">
          <img src={IconoIr} alt="Regresar" />
          Inicio
        </Link>

        <div className="success-message" id="successMessage">
          <div className="success-icon">✓</div>
          <h3>¡Registro exitoso!</h3>
          <p>Redirigiendo a tu panel de control...</p>
        </div>
      </div>
    </div>
  );
};
export default Register;

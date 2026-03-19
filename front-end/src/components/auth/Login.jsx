import React, { useState } from "react";
import "./Login.css";
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

        <div className="login-container">

            <div className="login-card">

                <div className="login-header">
                    <h2>Iniciar Sesión</h2>
                    <p>Ingresa tus credenciales para acceder a tu cuenta</p>
                </div>
                

                <form className="login-form" id="loginForm" noValidate onSubmit={onSubmit}>
                    <div className="form-group">
                        <div className="input-wrapper">
                            <input type="email" id="email" value={email} name="email" required autoComplete="email" onChange={onChange}/>
                            <label htmlFor="email">Correo</label>
                        </div>
                        <span className="error-message" id="emailError"></span>
                    </div>

                    <div className="form-group">
                        <div className="input-wrapper password-wrapper">
                            <input type="password" id="password" value={password} name="password" required autoComplete="current-password" onChange={onChange} />
                            <label htmlFor="password">Contraseña</label>
                            <button type="button" className="password-toggle" id="passwordToggle" aria-label="Toggle password visibility">
                                <span className="eye-icon"></span>
                            </button>
                        </div>
                        <span className="error-message" id="passwordError"></span>
                    </div>

                    <div className="form-options">
                        <label className="remember-wrapper">
                            <input type="checkbox" id="remember" name="remember" />
                            <span className="checkbox-label">
                                <span className="checkmark"></span>
                                Recordarme
                            </span>
                        </label>
                        <a href="#" className="forgot-password">¿Olvidaste tu contraseña?</a>
                    </div>

                    <button type="submit" className="login-btn">
                        <span className="btn-text">Iniciar Sesión</span>
                        <span className="btn-loader"></span>
                    </button>
                </form>

                <div className="signup-link">
                    <p>¿No tienes una cuenta? <a href="/register">Registrarse</a></p>
                </div>
                <Link className="btn boton-regresar" to="/home" type="button">
                    <img src={IconoIr} alt="Regresar" />
                    Regresar
                </Link>

                <div className="success-message" id="successMessage">
                    <div className="success-icon">✓</div>
                    <h3>¡Inicio de sesión exitoso!</h3>
                    <p>Redirigiendo a tu panel de control...</p>
                </div>
            </div>
        </div>

    );
}
export default Login;
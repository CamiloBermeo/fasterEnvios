import React from "react";
import "./Login.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import IconoIr from "../../assets/home_icono.svg";


const Login = () => {
    return (

        <div className="login-container">
            
            <div className="login-card">
                
                <div className="login-header">
                    <h2>Iniciar Sesión</h2>
                    <p>Ingresa tus credenciales para acceder a tu cuenta</p>
                </div>

                <form className="login-form" id="loginForm" noValidate>
                    <div className="form-group">
                        <div className="input-wrapper">
                            <input type="email" id="email" name="email" required autoComplete="email" />
                            <label htmlFor="email">Correo</label>
                        </div>
                        <span className="error-message" id="emailError"></span>
                    </div>

                    <div className="form-group">
                        <div className="input-wrapper password-wrapper">
                            <input type="password" id="password" name="password" required autoComplete="current-password" />
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
                <button className="btn boton-regresar" type="button">
                    <img src={IconoIr} alt="Regresar" />
                Regresar
            </button>

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
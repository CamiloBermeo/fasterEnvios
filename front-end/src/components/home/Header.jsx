import React from "react";
import "./Header.css";
import Logo from "../../assets/Logo.png";
import Search from "../../assets/search.svg";

const Header = ({ titulo }) => {

    return (
        <header className="header">
            <nav className="nav d-flex justify-content-between align-items-center">
                <ul className="nav-group d-none d-md-flex">
                    <li><a href="#nosotros">Nosotros</a></li>
                    <li><a href="#servicios">Servicios</a></li>
                    <li><a href="#consulta">Consulta</a></li>
                </ul>
                <img className="img-logo img-fluid" src={Logo} alt={titulo} />
                <ul className="nav-group d-none d-md-flex">
                    <li><a href="#únete">Únete</a></li>
                    <li><a href="#contactanos">Contactanos</a></li>
                    <button class="btn btn-success dropdown-toggle " type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Iniciar Sesion
                    </button>
                    <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Aliado</a></li>
                    <li><a class="dropdown-item" href="/login">Cliente</a></li>
                    
                    
                </ul>
                </ul>

            </nav>
            <div className="conteiner-consulta-guia">
                <div className="consulta-guia">
                    <input className="input-consultar-guia" type="text" placeholder="Ingresa Numero de Guia" />
                    <button className="button-consultar-guia"><img className="img-button-buscar" src={Search} alt="Consultar" /></button>
                </div>
            </div>

        </header>

    );
}
export default Header;
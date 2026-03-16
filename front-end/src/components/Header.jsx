import React from "react";
import "./Header.css";
import Logo from "../assets/Logo.png";
import Search from "../assets/search.svg";

const Header = ({ titulo }) => {

    return (
        <header className="header">
            <nav className="nav">
                <ul>
                    <li><a href="#nosotros">Nosotros</a></li>
                    <li><a href="#servicios">Servicios</a></li>
                    <li><a href="#consulta">Consulta</a></li>
                </ul>
                <img className="img-logo" src={Logo} alt={titulo} />
                <ul>
                    <li><a href="#productos">AutoEnvios</a></li>
                    <li><a href="#contactanos">Contactanos</a></li>
                    <li><a href="#login">Login</a></li>

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
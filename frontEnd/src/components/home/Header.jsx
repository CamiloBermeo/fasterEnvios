import React from "react";
import "./Header.css";
import Logo from "../../assets/Logo.png";
import Search from "../../assets/search.svg";

const Header = ({ titulo }) => {

    return (
        <header className="header ">
            <nav className="nav justify-around">
                <ul className="nav-group flex flex-row justify-start">
                    <li><a href="#nosotros">Nosotros</a></li>
                    <li><a href="#servicios">Servicios</a></li>
                    <li><a href="#consulta">Consulta</a></li>
                </ul>
                <div className="flex justify-center" >
                    <img className="img-logo " src={Logo} alt={titulo} />
                </div>

                <ul className="nav-group flex flex-row justify-end">
                    <li><a href="#únete">Únete</a></li>
                    <li><a href="#contactanos">Contactanos</a></li>
                    <button class=" w-30 h-8 btn rounded-full py-3 px-6 bg-green-500 cursor-pointer hover:bg-green-800 " type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Iniciar Sesion
                    </button>
                
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
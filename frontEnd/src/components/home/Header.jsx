import React, { useState } from "react";
import { Link } from 'react-router-dom';
import "./Header.css";
import Logo from "../../assets/Logo.png";
import Search from "../../assets/search.svg";

const Header = ({ titulo }) => {
    const [menuAbierto, setMenuAbierto] = useState(false);

    return (
        <header className="header ">
            <nav className="nav justify-around">
                <ul className=" nav-group flex flex-row justify-start">
                    <li>
                        <a href="#nosotros">Nosotros</a>
                    </li>
                    <li>
                        <a href="#servicios">Servicios</a>
                    </li>
                    <li>
                        <a href="#consulta">Consulta</a>
                    </li>
                </ul>
                <div className="flex justify-center">
                    <img className="img-logo " src={Logo} alt={titulo} />
                </div>

                <ul className=" relative nav-group flex flex-row justify-end">
                    <li>
                        <a href="#únete">Únete</a>
                    </li>
                    <li>
                        <a href="#contactanos">Contactanos</a>
                    </li>
                    <button
                        onClick={() => setMenuAbierto(!menuAbierto)}
                        class="flex items-center w-36 text-sm text-white h-8 rounded-full hover:text-white py-3 px-6 bg-green-600 cursor-pointer hover:bg-green-800 "
                        type="button"
                        data-bs-toggle="dropdown"
                        aria-expanded="false"
                    >
                        Iniciar Sesion
                    </button>
                    {menuAbierto && (
                        <div className="absolute top-full flex flex-col  right-14 items-center mt-2 w-36 bg-gray-600 rounded-2xl shadow-lg z-50">
                            <Link to="/login-aliado" className="block px-4 py-1 text-xs text-white ">
                                Aliado
                            </Link>
                            <Link to="/login" className="block px-4 py-1 text-sm text-white ">
                                Cliente
                            </Link>
                        </div>
                    )}
                </ul>
            </nav>
            <div className="conteiner-consulta-guia">
                <div className="consulta-guia">
                    <input
                        className="input-consultar-guia"
                        type="text"
                        placeholder="Ingresa Numero de Guia"
                    />
                    <button className="button-consultar-guia">
                        <img className="img-button-buscar" src={Search} alt="Consultar" />
                    </button>
                </div>
            </div>
        </header>
    );
};
export default Header;

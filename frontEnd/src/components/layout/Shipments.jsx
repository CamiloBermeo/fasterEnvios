import React from "react";
import NuevoEnvio from "../../assets/nuevo-envio.svg";
import BuscarEnvio from "../../assets/buscar-envio.svg";
import BuscarRepartidores from "../../assets/buscar-persona.svg";

const HederDashboardAliado = () => {
    return (
        <div className="flex justify-around items-center w-full h-24 mt-6 ">
            <div className="flex justify-center items-center w-44 h-20">
                <button
                    className="flex flex-col justify-center items-center  w-40 h-16 transition-all duration-500 ease-in-out hover:bg-emerald-600 
            rounded-2xl hover:shadow-lg shadow-emerald-800 hover:scale-110 active:scale-95
            bg-linear-to-r from-gray-300 to-gray-500 cursor-pointer text-white"
                >
                    <img className="size-10" src={NuevoEnvio} alt="Nuevo Envío" />
                    Nuevo Envío
                </button>
            </div>
            <div className="flex justify-center items-center  w-44 h-20">
                <button
                    className="flex flex-col justify-center items-center  w-40 h-16 transition-all duration-500 ease-in-out hover:bg-emerald-600 
            rounded-2xl hover:shadow-lg shadow-emerald-800 hover:scale-110 active:scale-95
            bg-linear-to-r from-gray-300 to-gray-500 cursor-pointer text-white"
                >
                    <img className="size-10" src={BuscarEnvio} alt="Buscar Envío" />
                    Buscar Envío
                </button>
            </div>
            <div className="flex justify-center items-center w-44 h-20">
                <button
                    className="flex flex-col justify-center items-center  w-40 h-16 transition-all duration-500 ease-in-out hover:bg-emerald-600 
            rounded-2xl hover:shadow-lg shadow-emerald-800 hover:scale-110 active:scale-95
            bg-linear-to-r from-gray-300 to-gray-500 cursor-pointer text-white"
                >
                    <img
                        className="size-10"
                        src={BuscarRepartidores}
                        alt="Buscar Repartidores"
                    />
                    Buscar Repartidores
                </button>
            </div>
        </div>
    );
};
export default HederDashboardAliado;

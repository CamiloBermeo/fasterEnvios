import React from "react";
import BuscarDashboard from "../../assets/search.svg";
import Campana from "../../assets/campana.svg";
import FotoPerfil from "../../assets/foto-perfil.png";

const HederDashboardAliado = () => {
    const [buscar, setBuscar] = React.useState({ buscar: "" });

    const onChange = e => {
        setBuscar({
            ...buscar,
            [e.target.name]: e.target.value
        });
    };

    return (
        <div className="flex justify-between rounded-2xl items-center gap-8   px-6 ">
            <div className="flex ">
                <h1 className="text-2xl font-sans">Dashboard</h1>
            </div>


            <div className="flex flex-1 mx-10 gap-1 max-w-xs bg-white rounded-3xl px-1 border border-gray-200">
                <button className="cursor-pointer"><img src={BuscarDashboard} alt="buscar" className="w-5 h-5" /></button>
                <input
                    className="flex-1 outline-none  bg-transparent"
                    type="text"
                    name="buscar"
                    value={buscar.buscar} // Corregido: .buscar
                    placeholder="Buscar..."
                    onChange={onChange}
                />
            </div>

            <div className="flex gap-2 justify-content items-center">
                <button className="flex justify-center items-center bg-white rounded-full cursor-pointer size-8
                hover:bg-green-600 transition-colors  duration-500 ease-in-out hover:scale-110 active:scale-95">
                    <img src={Campana} alt="campana" className="size-5" />
                </button>
                <button className="overflow-hidden rounded-full border-2 cursor-pointer border-white shadow-sm">
                    <img className="size-8 object-cover" src={FotoPerfil} alt="perfil" />
                </button>
            </div>
        </div>
    );
}
export default HederDashboardAliado;
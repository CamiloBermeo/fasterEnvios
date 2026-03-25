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
        <div className="flex justify-around rounded-2xl items-center gap-8   px-6 ">
            <div className="flex ">
                <h1 className="text-2xl font-bold">Dashboard</h1>
            </div>


            <div className="flex flex-1 mx-10 gap-2 max-w-md bg-white rounded-3xl px-4 py-1 border border-gray-200">
                <button className="cursor-pointer"><img src={BuscarDashboard} alt="buscar" className="w-5 h-5" /></button>
                <input
                    className="flex-1 w-3 outline-none px-2 bg-transparent"
                    type="text"
                    name="buscar"
                    value={buscar.buscar} // Corregido: .buscar
                    placeholder="Buscar..."
                    onChange={onChange}
                />
            </div>

            <div className="flex gap-2 justify-content items-center">
                <button className="flex justify-center items-center bg-white rounded-full cursor-pointer size-10
                hover:bg-gray-100 transition-colors">
                    <img src={Campana} alt="campana" className="w-6 h-6" />
                </button>
                <button className="overflow-hidden rounded-full border-2 cursor-pointer border-white shadow-sm">
                    <img className="w-10 h-10 object-cover" src={FotoPerfil} alt="perfil" />
                </button>
            </div>
        </div>
    );
}
export default HederDashboardAliado;
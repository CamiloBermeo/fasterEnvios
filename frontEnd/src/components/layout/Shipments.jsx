import React, { useState } from "react";
import FormNewShipment from "./FormNewShipment";
import NuevoEnvio from "../../assets/nuevo-envio.svg";
import BuscarEnvio from "../../assets/buscar-envio.svg";
import BuscarRepartidores from "../../assets/buscar-persona.svg";

const Shipments = () => {

    const [cargando, guardarCargando] = useState(false) 
    const [pagar, guardarPagar] = useState(false);
    const [envio, guardarEnvio] = useState({
        nombreRemitente: "",
        direccionRemitente: "",
        apellidosRemitente: "",
        telefonoRemitente: "",
        ciudadRemitente: "",
        nombreDestinatario: "",
        apellidosDestinatario: "",
        direccionDestinatario: "",
        telefonoDestinatario: "",
        ciudadDestinatario: "",
        descripcionPaquete: "",
        pesoPaquete: "",
        valorDeclarado: "",
        tipoPaquete: "",
        metodoPago: ""
    });
    const [formNuevoEnvio, setFormNuevoEnvio] = useState(false);
 const onChange = (e) => {
        guardarEnvio({
            ...envio,
            [e.target.name]: e.target.value
        });
    }
    const onSubmit = async e=>{

        //conectarme con el back
    }


    return (
        <div className="flex justify-around items-center w-full h-20  ">
            <div className="flex justify-center items-center w-44 h-20 ">
                <button
                    onClick={() => setFormNuevoEnvio(!formNuevoEnvio)}
                    className="flex flex-col justify-center items-center  w-40 h-16 transition-all duration-500 ease-in-out hover:bg-emerald-600 
            rounded-2xl hover:shadow-lg shadow-emerald-800 hover:scale-110 active:scale-95
            bg-linear-to-r from-gray-300 to-gray-500 cursor-pointer text-white"
                >
                    <img className="size-10" src={NuevoEnvio} alt="Nuevo Envío" />
                    Nuevo Envío
                </button>
                {formNuevoEnvio && (
                    <div className="flex flex-col absolute top-9 left-1/2 -translate-x-1/2 gap-4 bg-white rounded-lg shadow-lg p-6 z-50">
                        <div className="text-center text-2xl ">
                            <h2>Formulario de Nuevo Envío</h2>
                        </div>
                        <FormNewShipment envio={envio} onChange={onChange}/>
                        <div className="flex items-center justify-center gap-4">
                            <button
                            type="submit"
                            disabled={cargando}
                            className="cursor-pointer w-full h-full bg-blue-800 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-blue-600 hover:shadow-lg shadow-blue-800 hover:scale-100 active:scale-90">
                                Pagar
                            </button>
                            <button
                            className="cursor-pointer w-72 h-full bg-red-800 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-red-600 hover:shadow-lg shadow-red-800 hover:scale-100 active:scale-90"
                            onClick={()=> setFormNuevoEnvio(!formNuevoEnvio)}
                            >
                                Cancelar
                            </button>
                        </div>
                    </div>

                )}

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
export default Shipments;

import React, { useState } from "react";
import FormNewShipment from "./FormNewShipment";
import Payment from "./Payment"
import Factura from "./Factura"
import NuevoEnvio from "../../assets/nuevo-envio.svg";
import BuscarEnvio from "../../assets/buscar-envio.svg";
import BuscarRepartidores from "../../assets/buscar-persona.svg";
import clienteAxios from "../../config/clienteAxios";


const Shipments = () => {

    let respuestaEnvio ;
    const [cargando, guardarCargando] = useState(false)
    const [alerta, guardarAlerta] = useState(null)
    const [irPagar, guardarIrPagar] = useState(false);
    const [factura, guardarFactura] = useState(null)
    const [irFactura, guardarIrFactura] = useState(false)
    const [envio, guardarEnvio] = useState({
        nombreRemitente: "",
        direccionRemitente: "",
        cedulaRemitente: "",
        apellidosRemitente: "",
        telefonoRemitente: "",
        ciudadRemitente: "",
        nombreDestinatario: "",
        apellidosDestinatario: "",
        cedulaDestinatario: "",
        direccionDestinatario: "",
        telefonoDestinatario: "",
        ciudadDestinatario: "",
        descripcionPaquete: "",
        pesoPaquete: "",
        valorDeclarado: "",
        dimensionesPaquete: "",
    });
    const buildShipmentPayload = (envio) => ({
        sender: {
            name: envio.nombreRemitente,
            lastName: envio.apellidosRemitente,
            identityDocument: envio.cedulaRemitente,
            phoneNumber: envio.telefonoRemitente,
            address: envio.direccionRemitente,
            city: {
                name: envio.ciudadRemitente
            }
        },
        addressee: {
            name: envio.nombreDestinatario,
            lastName: envio.apellidosDestinatario,
            identityDocument: envio.cedulaDestinatario,
            phoneNumber: envio.telefonoDestinatario,
            address: envio.direccionDestinatario,
            city: {
                name: envio.ciudadDestinatario
            }
        },
        packages: {
            weightKg: envio.pesoPaquete,
            dimensions: envio.dimensionesPaquete,
            declaredValue: envio.valorDeclarado,
            description: envio.descripcionPaquete
        }
    });
    
    const [pago, guardarPago] = useState({

        orderId: "",
        payingPerson: "",
        amount: "",
        methodPaymentName: "",
        observation: ''

    })
    const [ventanaEnvio, setVentanaEnvio] = useState(false);
    const onChangeEnvio = (e) => {
        guardarEnvio({
            ...envio,
            [e.target.name]: e.target.value
        });
    }
    const onChangePago = (e) => {
        guardarPago({
            ...pago,
            [e.target.name]: e.target.value
        });
    }
    const onSubmit = async e => {
        const payload = buildShipmentPayload(envio);
        //evita, que el navegador recargue y se pierdan los datos si no que empieza a manejarlo react
        e.preventDefault();
        //conectarme con el back
        try {
            const respuesta = await clienteAxios.post("/shipments/newShipment", payload)
            guardarIrPagar(true)
            respuestaEnvio = respuesta.data;
            const trackingNumber = respuesta.data.trackingNumber;
            const amount = respuesta.data.amount;
            guardarPago(prev => ({
                ...prev,
                orderId: trackingNumber,
                amount: amount
            }));

        } catch (error) {
            const mensaje = error.response?.data?.msg || "Hubo un error al guardar el envio";
            guardarAlerta({ mensaje })
        } finally {
            guardarCargando(true)
        }

        //guardar pago
        try {
            const respuesta = await clienteAxios.post("/payments/payment", pago)
            guardarFactura(respuesta.data)
            guardarIrFactura(true)
        } catch (error) {
            const mensaje = error.response?.data?.msg || "Hubo un error al guardar el envío"
            guardarAlerta({ mensaje })
        } finally {
            guardarCargando(true)
        }
    }


    return (
        <div className="flex justify-around items-center w-full h-20  ">
            <div className="flex justify-center items-center w-44 h-20 ">
                <button
                    onClick={() => setVentanaEnvio(!ventanaEnvio)}
                    className="flex flex-col justify-center items-center  w-40 h-16 transition-all duration-500 ease-in-out hover:bg-emerald-600 
            rounded-2xl hover:shadow-lg shadow-emerald-800 hover:scale-110 active:scale-95
            bg-linear-to-r from-gray-300 to-gray-500 cursor-pointer text-white"
                >
                    <img className="size-10" src={NuevoEnvio} alt="Nuevo Envío" />
                    Nuevo Envío
                </button>
                {ventanaEnvio && (
                    <div className="flex flex-col absolute top-9 left-1/2 -translate-x-1/2 gap-4 bg-white rounded-lg shadow-lg p-6 z-50">
                        <div className="text-center text-2xl ">
                            <h2>Formulario de Nuevo Envío</h2>
                        </div>
                        {alerta && (
                            <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                                {alerta}
                            </div>
                        )}
                        <form onSubmit={onSubmit}>
                            <FormNewShipment
                                envio={envio}
                                onChangeEnvio={onChangeEnvio}
                            />
                            <div className="flex items-center justify-center gap-4">
                                <button
                                    type="submit"
                                    disabled={cargando}
                                    className="cursor-pointer w-full h-full bg-blue-800 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-blue-600 hover:shadow-lg shadow-blue-800 hover:scale-100 active:scale-90">
                                    Pagar
                                </button>
                                <button
                                    className="cursor-pointer w-72 h-full bg-red-800 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-red-600 hover:shadow-lg shadow-red-800 hover:scale-100 active:scale-90"
                                    type="button"
                                    onClick={() => setVentanaEnvio(!ventanaEnvio)}
                                >
                                    Cancelar
                                </button>
                            </div>
                        </form>
                    </div>
                )}
                {irPagar && (
                    <Payment
                        pago={pago}
                        respuestaEnvio={respuestaEnvio}
                        onChangePago={onChangePago}
                    />
                )}
                {irFactura && (
                    <Factura
                    factura={factura}
                    />
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
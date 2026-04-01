import React from "react";
import { useState } from "react";

const FormNewShipment = () => {

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
    //extraigo la informacion del remitente y la guardo en una variable para usarla
    const { nombreRemitente, apellidosRemitente, direccionRemitente, telefonoRemitente, ciudadRemitente
        , nombreDestinatario, apellidosDestinatario, direccionDestinatario, telefonoDestinatario, ciudadDestinatario,
        descripcionPaquete, pesoPaquete, valorDeclarado, tipoPaquete, metodoPago
    } = envio;
    //funcion para actualizar el estado del remitente cada vez que se cambia un campo del formulario
    const onChange = (e) => {
        guardarEnvio({
            ...envio,
            [e.target.name]: e.target.value
        });
    }

    return (
        <div className=" flex flex-col gap-4 items-center  overflow-y-auto max-h-96">

            <div
                className="flex flex-col gap-2 items-start  ">
                <h1 className="text-blue-600"> Información Remitente</h1>
                <form >
                    <div className="flex gap-1 form-group mb-2">
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="nombreRemitente"
                                value={nombreRemitente}
                                name="nombreRemitente"
                                required
                                autoComplete="nombreRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="nombreRemitente" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Nombre</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="apellidosRemitente"
                                value={apellidosRemitente}
                                name="apellidosRemitente"
                                required
                                autoComplete="apellidosRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="apellidosRemitente" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Apellidos</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="telefonoRemitente"
                                value={telefonoRemitente}
                                name="telefonoRemitente"
                                required
                                autoComplete="telefonoRemitente"
                                onChange={onChange}

                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="telefonoRemitente" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Teléfono</label>
                        </div>
                    </div>

                    <div className=" flex gap-3 items-center form-group mb-2">

                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="direccionRemitente"
                                value={direccionRemitente}
                                name="direccionRemitente"
                                required
                                autoComplete="direccionRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="direccionRemitente" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Dirección</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="ciudadRemitente"
                                value={ciudadRemitente}
                                name="ciudadRemitente"
                                required
                                autoComplete="ciudadRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="ciudadRemitente" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Ciudad</label>
                        </div>
                    </div>



                </form>
            </div>

            <div
                className="flex flex-col gap-4  ">
                <h1 className="text-emerald-600"> Información Destinatario</h1>
                <form >
                    <div className="flex gap-3 form-group mb-2">
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="nombreDestinatario"
                                value={nombreDestinatario}
                                name="nombreDestinatario"
                                required
                                autoComplete="nombreDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="nombreDestinatario" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Nombre</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="apellidosDestinatario"
                                value={apellidosDestinatario}
                                name="apellidosDestinatario"
                                required
                                autoComplete="apellidosDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="apellidosDestinatario" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Apellidos</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="telefonoDestinatario"
                                value={telefonoDestinatario}
                                name="telefonoDestinatario"
                                required
                                autoComplete="telefonoDestinatario"
                                onChange={onChange}

                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="telefonoDestinatario" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Teléfono</label>
                        </div>
                    </div>

                    <div className=" flex gap-3 items-center  form-group mb-2">

                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="direccionDestinatario"
                                value={direccionDestinatario}
                                name="direccionDestinatario"
                                required
                                autoComplete="direccionDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="direccionDestinatario" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Dirección</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="ciudadDestinatario"
                                value={ciudadDestinatario}
                                name="ciudadDestinatario"
                                required
                                autoComplete="ciudadDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="ciudad" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Ciudad</label>
                        </div>
                    </div>



                </form>
            </div>
            <div
                className="flex flex-col gap-4 w-full ">
                <h1 className="text-amber-600"> Información Paquete</h1>
                <form >
                    <div className="flex gap-3 form-group mb-2">

                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="pesoPaquete"
                                value={pesoPaquete}
                                name="pesoPaquete"
                                required
                                autoComplete="pesoPaquete"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="pesoPaquete" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Peso</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="valorDeclarado"
                                value={valorDeclarado}
                                name="valorDeclarado"
                                required
                                autoComplete="valorDeclarado"
                                onChange={onChange}

                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="valorDeclarado" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Valor Declarado</label>
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="tipoPaquete"
                                value={tipoPaquete}
                                name="tipoPaquete"
                                required
                                autoComplete="tipoPaquete"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="tipoPaquete" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Tipo de Paquete</label>
                        </div>
                    </div>

                    <div className=" flex gap-3 items-center form-group mb-5">


                        <div className="input-wrapper relative">
                            <textarea
                                type="text"
                                id="descripcionPaquete"
                                value={descripcionPaquete}
                                name="descripcionPaquete"
                                required
                                autoComplete="descripcionPaquete"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                            <label htmlFor="descripcionPaquete" className="absolute left-4 top-3 text-slate-500 text-base transition-all duration-200 pointer-events-none origin-top-left peer-focus:-translate-y-2 peer-focus:scale-75 peer-focus:text-indigo-500 peer-focus:font-normal">Descripción</label>
                        </div>

                    </div>


                    <div className="input-wrapper relative">
                        <label
                            htmlFor="metodoPago"
                            className="block text-slate-500 text-sm font-medium mb-2 pointer-events-none"
                        >
                            Método de Pago
                        </label>
                        <select
                            id="metodoPago"
                            name="metodoPago"
                            value={metodoPago}
                            onChange={onChange}
                            required
                            className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer appearance-none"
                        >
                            <option value="" disabled hidden>Selecciona un método</option>
                            <option value="CONTRAENTREGA">Contraentrega</option>
                            <option value="NEQUI">Nequi</option>
                            <option value="BANCOLOMBIA">Bancolombia</option>
                            <option value="EFECTIVO">Efectivo</option>
                        </select>

                        {/* Icono de flecha opcional para indicar que es un desplegable */}
                        <div className="absolute right-4 top-12 pointer-events-none text-slate-400">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                                <path fillRule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clipRule="evenodd" />
                            </svg>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default FormNewShipment;
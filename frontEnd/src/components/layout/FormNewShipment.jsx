import React from "react";

const FormNewShipment = ({ envio, onChange }) => {

    
    
    //extraigo la informacion del remitente y la guardo en una variable para usarla
    const { nombreRemitente, apellidosRemitente, cedulaRemitente, direccionRemitente, telefonoRemitente, ciudadRemitente
        , nombreDestinatario, apellidosDestinatario, cedulaDestinatario, direccionDestinatario, telefonoDestinatario, ciudadDestinatario,
        descripcionPaquete, pesoPaquete, valorDeclarado, dimensionesPaquete
    } = envio;
    //funcion para actualizar el estado del remitente cada vez que se cambia un campo del formulario

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
                                placeholder="Nombre"
                                required
                                autoComplete="nombreRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="apellidosRemitente"
                                value={apellidosRemitente}
                                name="apellidosRemitente"
                                required
                                placeholder="Apellidos"
                                autoComplete="apellidosRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="telefonoRemitente"
                                value={telefonoRemitente}
                                name="telefonoRemitente"
                                placeholder="Telefono"
                                required
                                autoComplete="telefonoRemitente"
                                onChange={onChange}

                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                    </div>

                    <div className=" flex gap-3 items-center form-group mb-2">
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="CedulaRemitente"
                                value={cedulaRemitente}
                                name="cedulaRemitente"
                                placeholder="Cédula"
                                required
                                autoComplete="cedulaRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="direccionRemitente"
                                value={direccionRemitente}
                                name="direccionRemitente"
                                placeholder="Direccion"
                                required
                                autoComplete="direccionRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="ciudadRemitente"
                                value={ciudadRemitente}
                                name="ciudadRemitente"
                                required
                                placeholder="Ciudad"
                                autoComplete="ciudadRemitente"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
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
                                placeholder="Nombre"
                                autoComplete="nombreDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="apellidosDestinatario"
                                value={apellidosDestinatario}
                                name="apellidosDestinatario"
                                required
                                placeholder="Apellidos"
                                autoComplete="apellidosDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="telefonoDestinatario"
                                value={telefonoDestinatario}
                                name="telefonoDestinatario"
                                required
                                placeholder="Telefono"
                                autoComplete="telefonoDestinatario"
                                onChange={onChange}

                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                    </div>

                    <div className=" flex gap-3 items-center  form-group mb-2">
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="CedulaDestinatario"
                                value={cedulaDestinatario}
                                name="cedulaDestinatario"
                                placeholder="Cédula"
                                required
                                autoComplete="cedulaDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="direccionDestinatario"
                                value={direccionDestinatario}
                                name="direccionDestinatario"
                                required
                                placeholder="Direccion"
                                autoComplete="direccionDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="ciudadDestinatario"
                                value={ciudadDestinatario}
                                name="ciudadDestinatario"
                                required
                                placeholder="Ciudad"
                                autoComplete="ciudadDestinatario"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
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
                                type="number"
                                id="pesoPaquete"
                                value={pesoPaquete}
                                name="pesoPaquete"
                                required
                                placeholder="Peso en KG"
                                autoComplete="pesoPaquete"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="text"
                                id="valorDeclarado"
                                value={valorDeclarado}
                                name="valorDeclarado"
                                required
                                placeholder="Valor Declarado"
                                autoComplete="valorDeclarado"
                                onChange={onChange}

                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                        <div className="input-wrapper relative">
                            <input
                                type="number"
                                id="DimensionesPaquete"
                                value={dimensionesPaquete}
                                name="dimensionesPaquete"
                                required
                                placeholder="Dimensiones en CM^3(largo x ancho x alto)"
                                autoComplete="dimensionesPaquete"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
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
                                placeholder="Descripcion del Paquete"
                                autoComplete="descripcionPaquete"
                                onChange={onChange}
                                className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400"
                            />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default FormNewShipment;
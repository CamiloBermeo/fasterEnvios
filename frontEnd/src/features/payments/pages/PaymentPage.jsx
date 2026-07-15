import React, { useState } from "react";

const Payment = ({ pago, respuestaEnvio, onChangePago, onSubmitPago }) => {
    const [errores, setErrores] = useState({});
    const [mostrarOpciones, setMostrarOpciones] = useState(false);

    const metodosDePago = [
        { id: 1, nombre: "Tarjeta de Crédito", icono: "💳" },
        { id: 2, nombre: "Tarjeta de Débito", icono: "🏦" },
        { id: 3, nombre: "Transferencia Bancaria", icono: "🔄" },
        { id: 4, nombre: "Billetera Digital", icono: "📱" },
        { id: 5, nombre: "CONTRAENTREGA", icono: "💵" },
    ];

    const validarFormulario = () => {
        const nuevosErrores = {};

        if (!pago.payingPerson) {
            nuevosErrores.payingPerson = "Selecciona quién realiza el pago";
        }

        if (!pago.methodPaymentName) {
            nuevosErrores.methodPaymentName = "Selecciona un método de pago";
        }

        setErrores(nuevosErrores);
        return Object.keys(nuevosErrores).length === 0;
    };

    const handleConfirmarPago = (e) => {
        e.preventDefault();
        if (validarFormulario()) {
            onSubmitPago();
        }
    };

    return (
        <div className="flex flex-col gap-4 items-center overflow-y-auto max-h-96 w-full">
            
            {/* Sección de Número de Referencia */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-blue-600">Número de Referencia</h1>
                <div className="w-full bg-blue-50 border-2 border-blue-200 rounded-lg px-4 py-3 text-blue-700 font-semibold">
                    
                    {respuestaEnvio.trackingNumber || "Pendiente..."}
                </div>
            </div>

            {/* Sección de Información de Pago */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-blue-600">Información de Pago</h1>
                
                <div className="flex gap-3 form-group mb-2">
                    <div className="input-wrapper relative w-full">
                        <div className="relative">
                            <button
                                type="button"
                                onClick={() => setMostrarOpciones(!mostrarOpciones)}
                                className={`w-full rounded-lg px-4 py-3 text-base transition-all duration-200 outline-none text-left flex justify-between items-center border-2 ${
                                    pago.payingPerson
                                        ? "bg-white border-slate-200 text-slate-800 hover:border-indigo-300 focus:border-indigo-500"
                                        : "bg-slate-50 border-slate-200 text-slate-500 hover:border-indigo-300 focus:border-indigo-500"
                                }`}
                            >
                                <span className={pago.payingPerson ? "font-medium text-slate-800" : "text-slate-500"}>
                                    {pago.payingPerson === "remitente" && "Quien Envía"}
                                    {pago.payingPerson === "destinatario" && "Quien Recibe"}
                                    {!pago.payingPerson && "¿Quién Paga?"}
                                </span>
                                <span className={`text-lg transition-transform ${mostrarOpciones ? "rotate-180" : ""}`}>
                                    ▼
                                </span>
                            </button>

                            {mostrarOpciones && (
                                <div className="absolute top-full left-0 right-0 mt-2 bg-white border-2 border-indigo-300 rounded-lg shadow-lg z-10">
                                    <button
                                        type="button"
                                        onClick={() => {
                                            onChangePago({
                                                target: { name: "payingPerson", value: "remitente" },
                                            });
                                            setMostrarOpciones(false);
                                        }}
                                        className={`w-full text-left px-4 py-3 transition-all duration-200 ${
                                            pago.payingPerson === "remitente"
                                                ? "bg-indigo-100 text-indigo-700 font-medium"
                                                : "text-slate-800 hover:bg-slate-50"
                                        }`}
                                    >
                                        Quien Envía
                                    </button>
                                    <button
                                        type="button"
                                        onClick={() => {
                                            onChangePago({
                                                target: { name: "payingPerson", value: "destinatario" },
                                            });
                                            setMostrarOpciones(false);
                                        }}
                                        className={`w-full text-left px-4 py-3 transition-all duration-200 ${
                                            pago.payingPerson === "destinatario"
                                                ? "bg-indigo-100 text-indigo-700 font-medium"
                                                : "text-slate-800 hover:bg-slate-50"
                                        }`}
                                    >
                                        Quien Recibe
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-sm text-slate-600 mb-1">Monto a Pagar</p>
                            <p className="text-lg font-semibold text-emerald-600">
                                {respuestaEnvio?.totalAmount || "Pendiente..."}
                            </p>
                        </div>
                    </div>
                </div>

                {/* Mensajes de error */}
                {errores.payingPerson && (
                    <div className="w-full text-red-600 text-sm">
                        {errores.payingPerson && <p>⚠️ {errores.payingPerson}</p>}
                    </div>
                )}
            </div>

            {/* Sección de Método de Pago */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-emerald-600">Método de Pago</h1>
                
                <div className="grid grid-cols-2 gap-3 w-full">
                    {metodosDePago.map((metodo) => (
                        <button
                            key={metodo.id}
                            type="button"
                            onClick={() => {
                                onChangePago({
                                    target: { name: "methodPaymentName", value: metodo.nombre },
                                });
                            }}
                            className={`p-3 rounded-lg border-2 transition-all duration-200 font-medium text-sm flex flex-col items-center gap-2 ${
                                pago.methodPaymentName === metodo.nombre
                                    ? "border-indigo-500 bg-indigo-100 text-indigo-700 shadow-md"
                                    : "border-slate-200 bg-white text-slate-800 hover:border-indigo-300"
                            }`}
                        >
                            <div className="text-2xl">{metodo.icono}</div>
                            <span className="text-xs text-center">{metodo.nombre}</span>
                        </button>
                    ))}
                </div>

                {errores.methodPaymentName && (
                    <p className="text-red-600 text-sm">⚠️ {errores.methodPaymentName}</p>
                )}
            </div>

            {/* Sección de Observaciones */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-amber-600">Observaciones (Opcional)</h1>
                
                <div className="form-group mb-2 w-full">
                    <div className="input-wrapper relative w-full">
                        <textarea
                            type="text"
                            id="observation"
                            value={pago.observation}
                            name="observation"
                            placeholder="Agrega notas adicionales del pago"
                            autoComplete="observation"
                            onChange={onChangePago}
                            className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer placeholder:text-slate-400 resize-none h-24"
                        />
                    </div>
                </div>
            </div>

            {/* Resumen de Pago */}
            <div className="w-full bg-green-50 border-2 border-green-200 rounded-lg p-4 flex flex-col gap-4">
                <div className="flex flex-col items-center justify-center gap-1 pb-2 border-b border-green-200">
                    <p className="text-sm text-slate-600">Total a Pagar</p>
                    <p className="text-3xl font-bold text-green-600">
                        {respuestaEnvio.totalAmount}
                    </p>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="flex flex-col items-center justify-center gap-1">
                        <p className="text-xs text-slate-600">Paga</p>
                        <p className="text-sm font-semibold text-slate-800">
                            {pago.payingPerson === "remitente" && "Quien Envía"}
                            {pago.payingPerson === "destinatario" && "Quien Recibe"}
                            {!pago.payingPerson && "No seleccionado"}
                        </p>
                    </div>
                    <div className="flex flex-col items-center justify-center gap-1">
                        <p className="text-xs text-slate-600">Método</p>
                        <p className="text-sm font-semibold text-slate-800">
                            {pago.methodPaymentName || "No seleccionado"}
                        </p>
                    </div>
                </div>
            </div>

            {/* Botones de Acción */}
            <div className="flex items-center justify-center gap-4 w-full">
                <button
                    type="button"
                    onClick={handleConfirmarPago}
                    disabled={false}
                    className="cursor-pointer w-full h-full bg-green-600 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-green-500 hover:shadow-lg shadow-green-800 hover:scale-100 active:scale-90">
                    Confirmar Pago
                </button>
            </div>
        </div>
    );
};

export default Payment;
import React, { useState } from "react";

// Componente Payment: Modal para recopilar y validar información de pago del usuario
const Payment = ({ pago, envio,onChangePago }) => {
    // Estado que almacena los errores de validación de cada campo
    const [errores, setErrores] = useState({});

    // Array con los métodos de pago disponibles: cada uno tiene id, nombre e icono emoji
    const metodosDePago = [
        { id: 1, nombre: "Tarjeta de Crédito", icono: "💳" },
        { id: 2, nombre: "Tarjeta de Débito", icono: "🏦" },
        { id: 3, nombre: "Transferencia Bancaria", icono: "🔄" },
        { id: 4, nombre: "Billetera Digital", icono: "📱" },
        { id: 5, nombre: "Efectivo", icono: "💵" },
    ];

    // Función que valida todos los campos del formulario de pago y retorna true si son válidos
    const validarFormulario = () => {
        // Objeto para almacenar temporalmente los errores encontrados
        const nuevosErrores = {};

        // Valida que el nombre del pagador no esté vacío
        if (!pago.payingPerson || pago.payingPerson.trim() === "") {
            nuevosErrores.payingPerson = "El nombre es requerido";
        }

        // Valida que el monto sea un número válido y mayor a 0
        if (!pago.amount || parseFloat(pago.amount) <= 0) {
            nuevosErrores.amount = "El monto debe ser mayor a 0";
        }

        // Valida que se haya seleccionado un método de pago
        if (!pago.methodPaymentName) {
            nuevosErrores.methodPaymentName = "Selecciona un método de pago";
        }

        // Actualiza el estado con los errores encontrados
        setErrores(nuevosErrores);
        // Retorna true si no hay errores, false si los hay
        return Object.keys(nuevosErrores).length === 0;
    };

    // Manejador del botón confirmar: valida el formulario y si es válido ejecuta el callback onSubmit
    const handleConfirmarPago = (e) => {
        e.preventDefault();
        if (validarFormulario()) {
            onSubmit();
        }
    };

    return (
        // Contenedor principal del modal con gradiente de colores y bordes redondeados
        <div className="w-full space-y-6 mt-6 p-6 bg-gradient-to-br from-blue-50 to-indigo-50 rounded-xl border border-blue-100">
            {/* Encabezado del modal con icono y título */}
            <div className="flex items-center gap-3 border-b border-blue-200 pb-4">
                <div className="w-10 h-10 bg-blue-600 rounded-full flex items-center justify-center">
                    <span className="text-white text-lg">💳</span>
                </div>
                <h3 className="text-2xl font-bold text-gray-800">Información de Pago del envio: </h3>
            </div>

            {/* Tarjeta que muestra el número de referencia (ID del envío) */}
            <div className="bg-white rounded-lg p-4 border-l-4 border-blue-600">
                <p className="text-sm text-gray-600">Número de Referencia</p>
                <p className="text-xl font-bold text-blue-600">{pago.orderId || "Pendiente..."}</p>
            </div>

            {/* Campo de entrada para el nombre de quien realiza el pago */}
            <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                    Nombre de Quien Paga *
                </label>
                <input
                    type="text"
                    name="payingPerson"
                    value={pago.payingPerson}
                    onChange={onChangePago}
                    placeholder="Ej: Juan Pérez"
                    className={`w-full px-4 py-3 rounded-lg border-2 transition-colors duration-200 focus:outline-none ${errores.payingPerson
                            ? "border-red-500 bg-red-50"
                            : "border-gray-300 bg-white hover:border-blue-400 focus:border-blue-600"
                        }`}
                />
                {/* Muestra el mensaje de error si el campo no es válido */}
                {errores.payingPerson && (
                    <p className="text-red-600 text-sm mt-1 flex items-center gap-1">
                    {errores.payingPerson}
                    </p>
                )}
            </div>

            {/* Campo de entrada para el monto a pagar con símbolo de dólar */}
            <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                    Monto a Pagar *
                </label>
                <div className="relative">
                    {/* Símbolo de dólar posicionado dentro del input */}
                    <span className="absolute left-4 top-3 text-xl font-bold text-gray-600">$</span>
                    <input
                        type="number"
                        name="amount"
                        value={pago.amount}
                        onChange={onChangePago}
                        placeholder="0.00"
                        step="0.01"
                        min="0"
                        className={`w-full pl-8 pr-4 py-3 rounded-lg border-2 transition-colors duration-200 focus:outline-none ${errores.amount
                                ? "border-red-500 bg-red-50"
                                : "border-gray-300 bg-white hover:border-blue-400 focus:border-blue-600"
                            }`}
                    />
                </div>
                {/* Muestra el mensaje de error si el monto no es válido */}
                {errores.amount && (
                    <p className="text-red-600 text-sm mt-1 flex items-center gap-1">
                        ⚠️ {errores.amount}
                    </p>
                )}
            </div>

            {/* Selector de métodos de pago con botones interactivos */}
            <div>
                <label className="block text-sm font-semibold text-gray-700 mb-3">
                    Método de Pago *
                </label>
                <div className="grid grid-cols-2 md:grid-cols-3 gap-3">
                    {/* Mapea los métodos disponibles para crear un botón por cada uno */}
                    {metodosDePago.map((metodo) => (
                        <button
                            key={metodo.id}
                            type="button"
                            onClick={() => {
                                // Actualiza el estado con el método seleccionado
                                onChangePago({
                                    target: { name: "methodPaymentName", value: metodo.nombre },
                                });
                            }}
                            className={`p-3 rounded-lg border-2 transition-all duration-200 font-medium text-sm ${pago.methodPaymentName === metodo.nombre
                                    ? "border-blue-600 bg-blue-100 text-blue-700 shadow-md"
                                    : "border-gray-300 bg-white text-gray-700 hover:border-blue-400"
                                }`}
                        >
                            <div className="text-2xl mb-1">{metodo.icono}</div>
                            {metodo.nombre}
                        </button>
                    ))}
                </div>
                {/* Muestra el mensaje de error si no se selecciona un método */}
                {errores.methodPaymentName && (
                    <p className="text-red-600 text-sm mt-2 flex items-center gap-1">
                        ⚠️ {errores.methodPaymentName}
                    </p>
                )}
            </div>

            {/* Campo de textarea para notas o observaciones adicionales (opcional) */}
            <div>
                <label className="block text-sm font-semibold text-gray-700 mb-2">
                    Observaciones (Opcional)
                </label>
                <textarea
                    name="observation"
                    value={pago.observation}
                    onChange={onChangePago}
                    placeholder="Agrega notas relevantes sobre el pago..."
                    rows="3"
                    className="w-full px-4 py-3 rounded-lg border-2 border-gray-300 bg-white hover:border-blue-400 focus:border-blue-600 focus:outline-none transition-colors duration-200 resize-none"
                />
            </div>

            {/* Tarjeta resumen que muestra el total a pagar y el método seleccionado */}
            <div className="bg-white rounded-lg p-4 border-2 border-green-200">
                <p className="text-sm text-gray-600 mb-1">Total a Pagar</p>
                {/* Formatea el monto a 2 decimales y lo muestra en color verde */}
                <p className="text-3xl font-bold text-green-600">
                    ${parseFloat(pago.amount || 0).toFixed(2)}
                </p>
                <p className="text-xs text-gray-500 mt-2">
                    Método: <span className="font-semibold">{pago.methodPaymentName || "No seleccionado"}</span>
                </p>
            </div>

            {/* Aviso de seguridad para generar confianza en el usuario */}
            <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-3 flex gap-2">
                <span className="text-lg">🔒</span>
                <p className="text-sm text-yellow-800">
                    Tu información de pago es segura y encriptada. No compartimos tus datos con terceros.
                </p>
            </div>

            {/* Botones de acción: Confirmar Pago y Cancelar */}
            <div className="flex gap-3 pt-4 border-t border-blue-200">
                {/* Botón para confirmar el pago: valida antes de ejecutar onSubmit */}
                <button
                    onClick={handleConfirmarPago}
                    className="flex-1 bg-green-600 hover:bg-green-700 text-white font-bold py-3 rounded-lg transition-all duration-200 shadow-md hover:shadow-lg active:scale-95 flex items-center justify-center gap-2"
                >
                    <span>✓</span> Confirmar Pago
                </button>
                {/* Botón para cancelar la operación de pago */}
                <button
                    type="button"
                    className="flex-1 bg-gray-500 hover:bg-gray-600 text-white font-bold py-3 rounded-lg transition-all duration-200 shadow-md hover:shadow-lg active:scale-95 flex items-center justify-center gap-2"
                >
                    <span>✕</span> Cancelar
                </button>
            </div>
        </div>
    );
};

// Exporta el componente Payment para usarlo en otros archivos
export default Payment;
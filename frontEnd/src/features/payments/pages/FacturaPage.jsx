import React from "react";

const Factura = ({ factura }) => {
    const handlePrint = () => {
        window.print();
    };

    const handleSendEmail = () => {
        alert("Se envió la factura por correo electrónico");
    };

    const handleFinish = () => {
        window.location.reload();
    };

    const formatDate = (dateString) => {
        if (!dateString) return "N/A";
        const date = new Date(dateString);
        return date.toLocaleDateString("es-CO", {
            year: "numeric",
            month: "long",
            day: "numeric"
        });
    };

    const formatDateTime = (dateString) => {
        if (!dateString) return "N/A";
        const date = new Date(dateString);
        return date.toLocaleDateString("es-CO", {
            year: "numeric",
            month: "long",
            day: "numeric",
            hour: "2-digit",
            minute: "2-digit"
        });
    };

    const getPaymentStatusColor = (status) => {
        switch (status?.toLowerCase()) {
            case "pagado":
                return "text-green-600";
            case "pendiente":
                return "text-yellow-600";
            case "fallido":
                return "text-red-600";
            default:
                return "text-slate-600";
        }
    };

    return (
        <div className="flex flex-col gap-4 items-center overflow-y-auto max-h-96 w-full">
            
            {/* Sección de Número de Factura */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-blue-600">Número de Factura / Transacción</h1>
                <div className="w-full bg-blue-50 border-2 border-blue-200 rounded-lg px-4 py-3 text-blue-700 font-semibold text-center">
                    {factura?.idTransaction || "Pendiente..."}
                </div>
            </div>

            {/* Sección de Remitente y Destinatario */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-blue-600">Información de Envío</h1>
                
                <div className="flex gap-3 form-group mb-2">
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-xs text-slate-600 mb-2 font-semibold">De (Remitente)</p>
                            <p className="text-sm font-medium text-slate-800 mb-1">{factura?.senderName || "N/A"}</p>
                            <p className="text-sm font-medium text-slate-800 mb-1">{factura?.senderAddress || "N/A"}</p>
                            <p className="text-xs text-slate-600">{factura?.senderCity || "N/A"}</p>
                        </div>
                    </div>
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-xs text-slate-600 mb-2 font-semibold">Para (Destinatario)</p>
                            <p className="text-sm font-medium text-slate-800 mb-1">{factura?.addresseeName || "N/A"}</p>
                            <p className="text-xs text-slate-600 mb-1">{factura?.addresseeAddress || "N/A"}</p>
                            <p className="text-xs text-slate-600">{factura?.addresseeCity || "N/A"}</p>
                        </div>
                    </div>
                </div>
            </div>

            {/* Sección de Información del Paquete */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-emerald-600">Información del Paquete</h1>
                
                <div className="flex gap-3 form-group mb-2">
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-xs text-slate-600 mb-1 font-semibold">Descripción</p>
                            <p className="text-sm text-slate-800">{factura?.packageDescription || "N/A"}</p>
                        </div>
                    </div>
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-xs text-slate-600 mb-1 font-semibold">Peso</p>
                            <p className="text-sm text-slate-800 font-medium">{factura?.packageWeight || "0"} kg</p>
                        </div>
                    </div>
                </div>
            </div>

            {/* Sección de Entrega */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-amber-600">Fecha Estimada de Entrega</h1>
                
                <div className="w-full bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base">
                    <p className="text-lg font-bold text-amber-600">
                        {formatDate(factura?.estimateDeliveryDate)}
                    </p>
                </div>
            </div>

            {/* Sección de Detalles de Pago */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-blue-600">Detalles de Pago</h1>
                
                <div className="flex gap-3 form-group mb-2">
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-xs text-slate-600 mb-1 font-semibold">Método de Pago</p>
                            <p className="text-sm text-slate-800">{factura?.paymentMethod || "N/A"}</p>
                        </div>
                    </div>
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-xs text-slate-600 mb-1 font-semibold">Fecha de Pago</p>
                            <p className="text-sm text-slate-800">{formatDateTime(factura?.paymentDate)}</p>
                        </div>
                    </div>
                </div>
            </div>

            {/* Sección de Estado */}
            <div className="w-full flex flex-col gap-4">
                <h1 className="text-emerald-600">Estado de Pago</h1>
                
                <div className="flex gap-3 form-group mb-2">
                    <div className="input-wrapper relative w-full">
                        <div className={`bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full text-center font-bold text-lg ${getPaymentStatusColor(factura?.paymentStatus)}`}>
                            {factura?.paymentStatus || "N/A"}
                        </div>
                    </div>
                    <div className="input-wrapper relative w-full">
                        <div className="bg-white border-2 border-slate-200 rounded-lg px-4 py-3 text-slate-800 text-base w-full">
                            <p className="text-xs text-slate-600 mb-1 font-semibold">Fecha de Emisión</p>
                            <p className="text-sm text-slate-800">{formatDate(factura?.InvoiceIssueDate)}</p>
                        </div>
                    </div>
                </div>
            </div>

            {/* Resumen */}
            <div className="w-full bg-green-50 border-2 border-green-200 rounded-lg p-4 flex flex-col gap-3">
                <p className="text-xs text-slate-600 text-center font-semibold">
                    🔒 Esta es una factura electrónica oficial. Conserva este documento para tu registro.
                </p>
            </div>

            {/* Botones de Acción */}
            <div className="flex items-center justify-center gap-3 w-full">
                <button
                    type="button"
                    onClick={handlePrint}
                    className="cursor-pointer flex-1 bg-blue-600 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-blue-500 hover:shadow-lg shadow-blue-800 hover:scale-100 active:scale-90">
                    🖨️ Imprimir
                </button>
                <button
                    type="button"
                    onClick={handleSendEmail}
                    className="cursor-pointer flex-1 bg-indigo-600 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-indigo-500 hover:shadow-lg shadow-indigo-800 hover:scale-100 active:scale-90">
                    📧 Correo
                </button>
                <button
                    type="button"
                    onClick={handleFinish}
                    className="cursor-pointer flex-1 bg-green-600 text-white rounded-2xl p-3 transition-all duration-500 ease-in-out hover:bg-green-500 hover:shadow-lg shadow-green-800 hover:scale-100 active:scale-90">
                    ✓ Terminar
                </button>
            </div>
        </div>
    );
};

export default Factura;
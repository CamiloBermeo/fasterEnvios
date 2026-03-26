import React from "react";
const Metrics = () => {
  return (
    <div className="flex justify-around items-center bg-amber-600 w-full ">

      <div className="flex flex-col items-start m-6 w-60 gap-3 bg-white rounded-2xl shadow-lg border border-gray-100">
        <h3 className="text-sm font-medium text-gray-500">Envíos Activos</h3>

        <div className="flex items-end mt-2 bg-blue-100">
          <span className="text-4xl font-bold text-slate-900">120</span>
        </div>

        <div className="flex items-center mt-3 bg-green-200">
          {/* Un span contenedor para toda la métrica secundaria */}
          <span className="flex items-center text-sm font-medium text-emerald-600 bg-emerald-50 px-2 py-0.5 rounded-full">
           12
            {/* Icono de Flecha (Estático pero condicional) */}
            <svg
              className="w-4 h-4 mr-1"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M5 10l7-7m0 0l7 7m-7-7v18"
              />
            </svg>

            {/* El Porcentaje (Dinámico) */}
            <span className="font-semibold"></span>
          </span>

          {/* Texto de Comparación (Estático) */}
          <span className="ml-2 text-xs text-gray-400">vs. ayer</span>
        </div>

      </div>
      <div className="flex items-center justify-center w-52 bg-white rounded-2xl">
        <h3 className="text-l ">Paquetes Pendientes</h3>
      </div>
      <div className="flex items-center justify-center w-52 bg-white rounded-2xl">
        <h3 className="text-l ">Entregas exitosas</h3>
      </div>
      <div className=" flex items-center justify-center w-52 bg-white rounded-2xl">
        <h3 className="text-l ">Repartidores Disponibles</h3>
      </div>
    </div>
  );
};
export default Metrics;

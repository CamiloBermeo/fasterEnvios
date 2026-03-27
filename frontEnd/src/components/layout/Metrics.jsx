import React from "react";
const Metrics = () => {
  return (
    <div className="flex justify-around items-center w-full ">

      <div className="flex flex-col items-start p-3 w-60 bg-white rounded-2xl shadow-lg border border-gray-100">
        <h3 className="text-sm font-medium  text-gray-500">Envíos Activos</h3>

        <div className="flex items-end mt-2">
          <span className="text-3xl font-sans text-slate-900">120</span>
        </div>

        <div className="flex items-center ">
          {/* Un span contenedor para toda la métrica secundaria */}
          <span className="flex items-center text-sm font-medium text-emerald-600 bg-emerald-50 px-2 py-0.5 rounded-full">
           12
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
            <span className="font-semibold"></span>
          </span>

          {/* Texto de Comparación (Estático) */}
          <span className="ml-2 text-xs text-gray-400">vs. ayer</span>
        </div>
      </div>

<div className="flex flex-col items-start p-3 w-60 bg-white rounded-2xl shadow-lg border border-gray-100">
        <h3 className="text-sm font-medium  text-gray-500">Paquetes Pendientes</h3>

        <div className="flex items-end mt-2">
          <span className="text-3xl font-sans text-slate-900">34</span>
        </div>

        <div className="flex items-center ">
          {/* Un span contenedor para toda la métrica secundaria */}  
          <span className="flex items-center text-sm font-medium text-emerald-600 bg-emerald-50 px-2 py-0.5 rounded-full">
           5
            <span className="font-semibold"></span>
          </span>

          {/* Texto de Comparación (Estático) */}
          <span className="ml-2 text-xs text-gray-400"> En cola de despacho</span>
        </div>
      </div>


<div className="flex flex-col items-start p-3 w-60 bg-white rounded-2xl shadow-lg border border-gray-100">
        <h3 className="text-sm font-medium  text-gray-500">Entregas exitosas</h3>

        <div className="flex items-end mt-2">
          <span className="text-3xl font-sans text-slate-900">1150</span>
        </div>

        <div className="flex items-center ">
          {/* Un span contenedor para toda la métrica secundaria */}
          <span className="flex items-center text-sm font-medium text-emerald-600 bg-emerald-50 px-2 py-0.5 rounded-full">
           22.5
            
            <svg 
  className="w-4 h-4 mr-1 stroke-[2.5px]" 
  viewBox="0 0 24 24" 
  fill="none" 
  stroke="currentColor" 
  strokeLinecap="round" 
  strokeLinejoin="round"
>
  <line x1="19" x2="5" y1="5" y2="19" />
  <circle cx="6.5" cy="6.5" r="2.5" />
  <circle cx="17.5" cy="17.5" r="2.5" />
</svg>
            <span className="font-semibold"></span>
          </span>

          {/* Texto de Comparación (Estático) */}
          <span className="ml-2 text-xs text-gray-400"> vs. mes anterior</span>
        </div>
      </div>


<div className="flex flex-col items-start p-3 w-60 bg-white rounded-2xl shadow-lg border border-gray-100">
        <h3 className="text-sm font-medium  text-gray-500">Repartidores Disponibles</h3>

        <div className="flex items-end mt-2">
          <span className="text-3xl font-sans text-slate-900">18</span>
        </div>

        <div className="flex items-center ">
          <span className="ml-2 text-xs text-gray-400">De un total de:  </span>
          {/* Un span contenedor para toda la métrica secundaria */}
          <span className="flex items-center text-sm font-medium text-emerald-600 bg-emerald-50 px-2 py-0.5 rounded-full">
           30
            <span className="font-semibold"></span>
          </span>

          {/* Texto de Comparación (Estático) */}
          
        </div>
      </div>
    </div>
  );
};
export default Metrics;

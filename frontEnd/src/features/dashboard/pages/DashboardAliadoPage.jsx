import React from "react";
import Metrics from "../components/Metrics.jsx";
import Statistics from "../components/Statistics.jsx";
import Shipments from "../../shipments/pages/ShipmentsPage.jsx";

const DashboardAliadoPage = () => {
  return (
    <div className="space-y-6">
      <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-100 flex flex-col md:flex-row justify-between items-start md:items-center gap-4">
        <div>
          <h1 className="text-2xl font-black text-slate-800">Panel de Control Aliado</h1>
          <p className="text-sm text-slate-500">Gestión en tiempo real de despachos, repartidores y métricas de rendimiento.</p>
        </div>
        <div className="flex items-center gap-2">
          <span className="px-3 py-1 bg-emerald-100 text-emerald-700 text-xs font-bold rounded-full flex items-center gap-1.5">
            <span className="w-2 h-2 rounded-full bg-emerald-500 animate-pulse"></span>
            Sistema en Línea
          </span>
        </div>
      </div>

      <Metrics />
      
      <div className="bg-white p-6 rounded-2xl shadow-sm border border-slate-100">
        <h2 className="text-lg font-bold text-slate-800 mb-4">Acciones y Gestión de Envíos</h2>
        <Shipments />
      </div>

      <Statistics />
    </div>
  );
};

export default DashboardAliadoPage;
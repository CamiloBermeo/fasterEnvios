import React from 'react';

const FleetStatusCard = () => {
  const fleetData = [
    { label: "Motos Urbanas", total: 18, active: 15, color: "bg-emerald-500" },
    { label: "Furgones Ligeros", total: 8, active: 7, color: "bg-indigo-500" },
    { label: "Camiones Nacionales", total: 4, active: 4, color: "bg-amber-500" },
  ];

  return (
    <div className="bg-white p-5 rounded-2xl shadow-sm border border-slate-100 flex flex-col justify-between">
      <div>
        <h3 className="text-sm font-bold text-slate-700 mb-3">Estado de la Flota Logística</h3>
        <div className="space-y-4">
          {fleetData.map((item, idx) => (
            <div key={idx} className="space-y-1">
              <div className="flex justify-between text-xs font-semibold text-slate-600">
                <span>{item.label}</span>
                <span>{item.active} / {item.total} en ruta</span>
              </div>
              <div className="w-full h-2.5 bg-slate-100 rounded-full overflow-hidden">
                <div 
                  className={`h-full ${item.color} rounded-full transition-all duration-500`}
                  style={{ width: `${(item.active / item.total) * 100}%` }}
                ></div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className="mt-5 pt-3 border-t border-slate-100 flex items-center justify-between text-xs text-slate-500">
        <span className="flex items-center gap-1.5 font-medium text-emerald-600">
          <span className="w-2 h-2 rounded-full bg-emerald-500 animate-ping"></span>
          91% Operatividad Activa
        </span>
        <button className="text-indigo-600 hover:underline font-semibold">Ver Mapa GPS →</button>
      </div>
    </div>
  );
};

export default FleetStatusCard;
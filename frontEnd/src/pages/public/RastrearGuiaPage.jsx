import React, { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import { trackShipment } from "../../services/shipmentService.js";
import SearchIcon from "../../assets/search.svg";

const RastrearGuiaPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const guiaParam = searchParams.get("guia") || "";

  const [guiaInput, setGuiaInput] = useState(guiaParam);
  const [envioData, setEnvioData] = useState(null);
  const [cargando, setCargando] = useState(false);
  const [error, setError] = useState(null);

  const consultarGuia = async (codigoGuia) => {
    if (!codigoGuia.trim()) return;
    setCargando(true);
    setError(null);
    try {
      const data = await trackShipment(codigoGuia);
      setEnvioData(data);
    } catch (err) {
      setError(err.message || "Error al consultar la guía.");
      setEnvioData(null);
    } finally {
      setCargando(false);
    }
  };

  useEffect(() => {
    if (guiaParam) {
      setGuiaInput(guiaParam);
      consultarGuia(guiaParam);
    } else {
      // Cargar guía de demostración por defecto
      consultarGuia("FE-892341");
    }
  }, [guiaParam]);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (guiaInput.trim()) {
      setSearchParams({ guia: guiaInput.trim() });
      consultarGuia(guiaInput.trim());
    }
  };

  return (
    <div className="space-y-12 pb-16">
      
      {/* Header */}
      <section className="bg-slate-900 text-white py-14 text-center">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 space-y-3">
          <span className="bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 px-3.5 py-1 rounded-full text-xs font-bold uppercase">
            Sistema de Trazabilidad Satelital
          </span>
          <h1 className="text-3xl sm:text-4xl font-black">Consulta el Estado de tu Guía</h1>
          <p className="text-xs sm:text-sm text-slate-300 max-w-lg mx-auto">
            Seguimiento en tiempo real de tu envío desde la recolección hasta la firma en destino.
          </p>

          {/* Search Form */}
          <form onSubmit={handleSubmit} className="max-w-xl mx-auto pt-4 flex gap-2">
            <div className="relative flex-1">
              <input
                type="text"
                value={guiaInput}
                onChange={(e) => setGuiaInput(e.target.value)}
                placeholder="Número de Guía (ej: FE-892341)..."
                className="w-full pl-4 pr-10 py-3.5 bg-white text-slate-900 text-sm font-semibold rounded-xl outline-none border border-slate-300 focus:border-emerald-500"
              />
              <img src={SearchIcon} alt="Buscar" className="absolute right-3.5 top-3.5 w-5 h-5 text-slate-400" />
            </div>
            <button
              type="submit"
              disabled={cargando}
              className="px-6 py-3.5 bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs rounded-xl shadow transition cursor-pointer"
            >
              {cargando ? "Buscando..." : "Buscar"}
            </button>
          </form>
        </div>
      </section>

      {/* Main Tracking Details */}
      <section className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {error && (
          <div className="bg-red-50 border border-red-300 text-red-700 p-4 rounded-2xl text-xs font-bold text-center">
            {error}
          </div>
        )}

        {cargando && (
          <div className="bg-white p-12 rounded-3xl border border-slate-200 text-center space-y-3">
            <div className="w-8 h-8 border-4 border-emerald-500 border-t-transparent rounded-full animate-spin mx-auto"></div>
            <p className="text-xs font-bold text-slate-600">Consultando satélites y centros de acopio...</p>
          </div>
        )}

        {!cargando && envioData && (
          <div className="space-y-8">
            
            {/* Header Status Card */}
            <div className="bg-white p-6 sm:p-8 rounded-3xl border border-slate-200 shadow-sm space-y-6">
              <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4 pb-6 border-b border-slate-100">
                <div>
                  <span className="text-xs text-slate-400 font-bold uppercase block">Guía de Envío</span>
                  <span className="text-2xl sm:text-3xl font-black text-slate-900">{envioData.trackingNumber}</span>
                </div>
                <div className="flex items-center gap-2">
                  <span className="px-4 py-1.5 bg-emerald-100 text-emerald-800 text-xs font-bold rounded-full border border-emerald-300 uppercase tracking-wide">
                    {envioData.statusText || envioData.status}
                  </span>
                </div>
              </div>

              {/* TIMELINE PROGRESS BAR */}
              <div className="py-4">
                <div className="relative flex justify-between items-center max-w-3xl mx-auto">
                  
                  {/* Progress background line */}
                  <div className="absolute top-1/2 left-0 right-0 h-1 bg-slate-200 -translate-y-1/2 z-0"></div>
                  
                  {envioData.history.map((step, idx) => (
                    <div key={idx} className="relative z-10 flex flex-col items-center group">
                      <div className={`w-8 h-8 rounded-full flex items-center justify-center font-bold text-xs transition-all ${
                        step.completed
                          ? 'bg-emerald-600 text-white ring-4 ring-emerald-100'
                          : 'bg-slate-200 text-slate-500'
                      }`}>
                        {step.completed ? '✓' : idx + 1}
                      </div>
                      <span className={`text-[11px] font-bold mt-2 text-center max-w-[80px] leading-tight ${
                        step.completed ? 'text-slate-800' : 'text-slate-400'
                      }`}>
                        {step.title}
                      </span>
                    </div>
                  ))}

                </div>
              </div>

              {/* Package Meta Info Grid */}
              <div className="grid grid-cols-2 sm:grid-cols-4 gap-4 bg-slate-50 p-4 rounded-2xl text-xs">
                <div>
                  <span className="text-slate-400 font-medium block">Origen</span>
                  <span className="font-bold text-slate-800">{envioData.origin}</span>
                </div>
                <div>
                  <span className="text-slate-400 font-medium block">Destino</span>
                  <span className="font-bold text-slate-800">{envioData.destination}</span>
                </div>
                <div>
                  <span className="text-slate-400 font-medium block">Peso</span>
                  <span className="font-bold text-slate-800">{envioData.weightKg} kg</span>
                </div>
                <div>
                  <span className="text-slate-400 font-medium block">Entrega Estimada</span>
                  <span className="font-bold text-emerald-700">{envioData.estimatedDelivery}</span>
                </div>
              </div>
            </div>

            {/* Detailed Timeline Events */}
            <div className="bg-white p-6 sm:p-8 rounded-3xl border border-slate-200 shadow-sm space-y-4">
              <h3 className="text-base font-bold text-slate-800">Historial de Movimientos y Eventos</h3>
              
              <div className="space-y-4 pt-2">
                {envioData.history.map((item, idx) => (
                  <div key={idx} className="flex gap-4 items-start pb-4 border-b border-slate-100 last:border-0 last:pb-0">
                    <div className={`w-3 h-3 rounded-full mt-1.5 shrink-0 ${item.completed ? 'bg-emerald-500 ring-4 ring-emerald-100' : 'bg-slate-300'}`}></div>
                    <div className="flex-1 flex flex-col sm:flex-row sm:justify-between sm:items-center gap-1">
                      <div>
                        <h4 className="text-xs font-bold text-slate-800">{item.title}</h4>
                        <span className="text-xs text-slate-500 block">Ubicación: {item.location}</span>
                      </div>
                      <span className="text-[11px] font-semibold text-slate-400">{item.date}</span>
                    </div>
                  </div>
                ))}
              </div>
            </div>

          </div>
        )}

      </section>

    </div>
  );
};

export default RastrearGuiaPage;

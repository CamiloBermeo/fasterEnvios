import React, { useState } from "react";
import { Link } from "react-router-dom";
import { calculateRate } from "../../services/shipmentService.js";

const CotizadorPage = () => {
  const [params, setParams] = useState({
    origen: "Bogotá, D.C.",
    destino: "Medellín, Antioquia",
    peso: 2,
    largo: 20,
    ancho: 15,
    alto: 10,
    valorDeclarado: 100000
  });

  const [resultado, setResultado] = useState(
    calculateRate("Bogotá, D.C.", "Medellín, Antioquia", 2, 20, 15, 10)
  );

  const handleChange = (e) => {
    const updated = {
      ...params,
      [e.target.name]: e.target.type === "number" ? parseFloat(e.target.value) || 0 : e.target.value
    };
    setParams(updated);
    setResultado(
      calculateRate(updated.origen, updated.destino, updated.peso, updated.largo, updated.ancho, updated.alto)
    );
  };

  return (
    <div className="space-y-12 pb-16">
      
      {/* Header */}
      <section className="bg-slate-900 text-white py-14 text-center">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 space-y-2">
          <span className="bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 px-3.5 py-1 rounded-full text-xs font-bold uppercase">
            Cotizador Oficial
          </span>
          <h1 className="text-3xl sm:text-4xl font-black">Calculadora Transparente de Tarifas</h1>
          <p className="text-xs sm:text-sm text-slate-300 max-w-lg mx-auto">
            Calcula el valor exacto flete e impuestos de tu paquete en segundos.
          </p>
        </div>
      </section>

      {/* Calculator Grid */}
      <section className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-start">
          
          {/* Inputs */}
          <div className="lg:col-span-7 bg-white p-6 sm:p-8 rounded-3xl border border-slate-200 shadow-sm space-y-6">
            <h2 className="text-lg font-bold text-slate-800 pb-3 border-b border-slate-100">
              Datos del Envío
            </h2>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">Ciudad Origen</label>
                <select
                  name="origen"
                  value={params.origen}
                  onChange={handleChange}
                  className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                >
                  <option value="Bogotá, D.C.">Bogotá, D.C.</option>
                  <option value="Medellín, Antioquia">Medellín, Antioquia</option>
                  <option value="Cali, Valle">Cali, Valle</option>
                  <option value="Barranquilla, Atlántico">Barranquilla, Atlántico</option>
                  <option value="Bucaramanga, Santander">Bucaramanga, Santander</option>
                  <option value="Pereira, Risaralda">Pereira, Risaralda</option>
                  <option value="Cartagena, Bolívar">Cartagena, Bolívar</option>
                </select>
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">Ciudad Destino</label>
                <select
                  name="destino"
                  value={params.destino}
                  onChange={handleChange}
                  className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                >
                  <option value="Medellín, Antioquia">Medellín, Antioquia</option>
                  <option value="Bogotá, D.C.">Bogotá, D.C.</option>
                  <option value="Cali, Valle">Cali, Valle</option>
                  <option value="Barranquilla, Atlántico">Barranquilla, Atlántico</option>
                  <option value="Bucaramanga, Santander">Bucaramanga, Santander</option>
                  <option value="Pereira, Risaralda">Pereira, Risaralda</option>
                  <option value="Cartagena, Bolívar">Cartagena, Bolívar</option>
                </select>
              </div>
            </div>

            <div className="grid grid-cols-2 sm:grid-cols-4 gap-3">
              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">Peso (kg)</label>
                <input
                  type="number"
                  min="1"
                  max="500"
                  name="peso"
                  value={params.peso}
                  onChange={handleChange}
                  className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">Largo (cm)</label>
                <input
                  type="number"
                  min="1"
                  name="largo"
                  value={params.largo}
                  onChange={handleChange}
                  className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">Ancho (cm)</label>
                <input
                  type="number"
                  min="1"
                  name="ancho"
                  value={params.ancho}
                  onChange={handleChange}
                  className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">Alto (cm)</label>
                <input
                  type="number"
                  min="1"
                  name="alto"
                  value={params.alto}
                  onChange={handleChange}
                  className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                />
              </div>
            </div>

            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">Valor Declarado de la Mercancía ($ COP)</label>
              <input
                type="number"
                min="10000"
                step="10000"
                name="valorDeclarado"
                value={params.valorDeclarado}
                onChange={handleChange}
                className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
              />
            </div>
          </div>

          {/* Breakdown Result */}
          <div className="lg:col-span-5 bg-gradient-to-b from-slate-900 to-slate-950 text-white p-8 rounded-3xl space-y-6 shadow-xl border border-slate-800">
            <h3 className="text-sm font-extrabold uppercase tracking-wider text-emerald-400">Resumen de Cotización</h3>
            
            <div className="space-y-3 border-b border-slate-800 pb-4">
              <div className="flex justify-between text-xs text-slate-400">
                <span>Ruta:</span>
                <span className="font-semibold text-white">{params.origen} → {params.destino}</span>
              </div>
              <div className="flex justify-between text-xs text-slate-400">
                <span>Peso Facturable:</span>
                <span className="font-semibold text-white">{resultado.chargeableWeight} kg</span>
              </div>
              <div className="flex justify-between text-xs text-slate-400">
                <span>Tiempo de Entrega:</span>
                <span className="font-semibold text-emerald-400">{resultado.deliveryDays}</span>
              </div>
              <div className="flex justify-between text-xs text-slate-400">
                <span>Seguro Incluido (aprox):</span>
                <span className="font-semibold text-white">${resultado.insuranceIncluded.toLocaleString('es-CO')} COP</span>
              </div>
            </div>

            <div className="space-y-1">
              <span className="text-xs text-slate-400 block font-semibold">Costo Total Estimado</span>
              <span className="text-4xl font-black text-emerald-400 block">{resultado.formattedPrice}</span>
              <span className="text-[10px] text-slate-500 block">IVA incluido. Sujeto a verificación física de peso y volumen.</span>
            </div>

            <div className="pt-2">
              <Link
                to="/login"
                className="block text-center w-full py-3.5 bg-emerald-500 hover:bg-emerald-600 text-slate-950 font-extrabold text-xs rounded-xl shadow-lg transition"
              >
                Crear y Despachar Envío Ahora
              </Link>
            </div>
          </div>

        </div>
      </section>

    </div>
  );
};

export default CotizadorPage;

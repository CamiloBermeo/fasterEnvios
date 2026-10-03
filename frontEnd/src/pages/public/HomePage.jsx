import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import BannerImage1 from "../../assets/banner_homee_faster_envios.png";
import SearchIcon from "../../assets/search.svg";
import PaqueteIcon from "../../assets/paquete.svg";
import { calculateRate } from "../../services/shipmentService.js";

const HomePage = () => {
  const navigate = useNavigate();
  const [guiaTracking, setGuiaTracking] = useState("");
  
  // State del cotizador rápido
  const [cotizacion, setCotizacion] = useState({
    origen: "Bogotá, D.C.",
    destino: "Medellín, Antioquia",
    peso: "2",
  });
  const [resultadoCotizacion, setResultadoCotizacion] = useState(
    calculateRate("Bogotá, D.C.", "Medellín, Antioquia", 2)
  );

  const handleRastrearSubmit = (e) => {
    e.preventDefault();
    if (guiaTracking.trim()) {
      navigate(`/rastrear?guia=${encodeURIComponent(guiaTracking.trim())}`);
    }
  };

  const handleCotizarChange = (e) => {
    const updated = { ...cotizacion, [e.target.name]: e.target.value };
    setCotizacion(updated);
    setResultadoCotizacion(calculateRate(updated.origen, updated.destino, updated.peso));
  };

  return (
    <div className="space-y-16 pb-12">
      
      {/* HERO SECTION */}
      <section className="relative bg-slate-900 text-white overflow-hidden">
        {/* Background gradient overlays */}
        <div className="absolute inset-0 bg-gradient-to-r from-slate-950 via-slate-900/90 to-emerald-950/70 z-10" />
        <img
          src={BannerImage1}
          alt="Faster Envíos Logística"
          className="absolute inset-0 w-full h-full object-cover object-center opacity-35"
        />

        <div className="relative z-20 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20 lg:py-28 flex flex-col items-center text-center">
          
          <span className="inline-flex items-center gap-2 px-4 py-1.5 rounded-full bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 text-xs font-bold uppercase tracking-wider mb-6 animate-pulse">
            🚀 Envíos Exprés & Logística Inteligente
          </span>

          <h1 className="text-3xl sm:text-5xl lg:text-6xl font-black tracking-tight leading-tight max-w-4xl">
            Tus Paquetes en el Lugar Indicado,{" "}
            <span className="text-transparent bg-clip-text bg-gradient-to-r from-emerald-400 via-teal-300 to-cyan-400">
              A la Velocidad que Necesitas.
            </span>
          </h1>

          <p className="mt-6 text-base sm:text-lg text-slate-300 max-w-2xl font-normal leading-relaxed">
            Cobertura nacional en más de 50 ciudades. Trazabilidad satelital en tiempo real, recolección a domicilio y entregas garantizadas en 24 horas.
          </p>

          {/* FLOATING QUICK TRACKING CARD */}
          <div className="mt-10 w-full max-w-2xl bg-white/95 backdrop-blur border border-slate-200 rounded-2xl p-4 sm:p-6 shadow-2xl text-slate-900">
            <h2 className="text-base font-bold text-slate-800 flex items-center justify-center gap-2 mb-3">
              <span className="w-2.5 h-2.5 rounded-full bg-emerald-500 animate-ping"></span>
              Rastrea tu Envío al Instante
            </h2>

            <form onSubmit={handleRastrearSubmit} className="flex flex-col sm:flex-row gap-3">
              <div className="relative flex-1">
                <input
                  type="text"
                  value={guiaTracking}
                  onChange={(e) => setGuiaTracking(e.target.value)}
                  placeholder="Ingresa tu número de guía (Ej: FE-892341)"
                  className="w-full pl-4 pr-10 py-3.5 bg-slate-100 text-slate-900 text-sm rounded-xl border border-slate-300 outline-none focus:border-emerald-500 focus:bg-white focus:ring-2 focus:ring-emerald-200 transition font-medium"
                />
                <img
                  src={SearchIcon}
                  alt="Buscar"
                  className="absolute right-3.5 top-3.5 w-5 h-5 text-slate-400"
                />
              </div>
              <button
                type="submit"
                className="px-8 py-3.5 bg-emerald-600 hover:bg-emerald-700 active:scale-95 text-white text-sm font-bold rounded-xl shadow-lg shadow-emerald-600/30 transition-all cursor-pointer flex items-center justify-center gap-2"
              >
                <span>Consultar Guía</span>
                <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2.5" d="M14 5l7 7m0 0l-7 7m7-7H3" />
                </svg>
              </button>
            </form>

            <div className="mt-3 flex items-center justify-center gap-4 text-xs text-slate-500">
              <span>💡 Ejemplo de guía de prueba: <button onClick={() => setGuiaTracking("FE-892341")} className="text-emerald-700 font-bold underline">FE-892341</button> o <button onClick={() => setGuiaTracking("FE-102938")} className="text-emerald-700 font-bold underline">FE-102938</button></span>
            </div>
          </div>

          {/* Quick Action buttons */}
          <div className="mt-8 flex flex-wrap justify-center gap-4">
            <Link
              to="/cotizador"
              className="px-6 py-3 bg-white/10 hover:bg-white/20 text-white rounded-xl text-xs sm:text-sm font-semibold border border-white/20 transition"
            >
              Calculadora de Tarifas →
            </Link>
            <Link
              to="/contacto"
              className="px-6 py-3 bg-emerald-600/30 hover:bg-emerald-600/40 text-emerald-200 rounded-xl text-xs sm:text-sm font-semibold border border-emerald-500/30 transition"
            >
              Solicitar Recolección
            </Link>
          </div>
        </div>
      </section>

      {/* COTIZADOR RÁPIDO SIMULADOR */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="bg-gradient-to-br from-emerald-800 to-teal-900 rounded-3xl p-6 sm:p-10 text-white shadow-xl relative overflow-hidden">
          <div className="relative z-10 grid grid-cols-1 lg:grid-cols-12 gap-8 items-center">
            
            <div className="lg:col-span-5 space-y-4">
              <span className="bg-emerald-700/80 px-3 py-1 rounded-full text-xs font-bold text-emerald-200 uppercase tracking-wider inline-block">
                Simulador Tarifario en Vivo
              </span>
              <h2 className="text-2xl sm:text-4xl font-extrabold tracking-tight">
                Calcula el Costo de tu Envío al Instante
              </h2>
              <p className="text-sm text-emerald-100 leading-relaxed">
                Sin sorpresas ni cobros ocultos. Ingresa origen, destino y peso estimado para obtener una cotización transparente con seguro incluido.
              </p>
            </div>

            <div className="lg:col-span-7 bg-white text-slate-800 rounded-2xl p-6 shadow-lg space-y-4">
              <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
                
                <div>
                  <label className="block text-xs font-bold text-slate-600 mb-1.5 uppercase">Ciudad Origen</label>
                  <select
                    name="origen"
                    value={cotizacion.origen}
                    onChange={handleCotizarChange}
                    className="w-full p-2.5 bg-slate-100 border border-slate-300 rounded-lg text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                  >
                    <option value="Bogotá, D.C.">Bogotá, D.C.</option>
                    <option value="Medellín, Antioquia">Medellín, Antioquia</option>
                    <option value="Cali, Valle">Cali, Valle</option>
                    <option value="Barranquilla, Atlántico">Barranquilla, Atlántico</option>
                    <option value="Bucaramanga, Santander">Bucaramanga, Santander</option>
                  </select>
                </div>

                <div>
                  <label className="block text-xs font-bold text-slate-600 mb-1.5 uppercase">Ciudad Destino</label>
                  <select
                    name="destino"
                    value={cotizacion.destino}
                    onChange={handleCotizarChange}
                    className="w-full p-2.5 bg-slate-100 border border-slate-300 rounded-lg text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                  >
                    <option value="Medellín, Antioquia">Medellín, Antioquia</option>
                    <option value="Bogotá, D.C.">Bogotá, D.C.</option>
                    <option value="Cali, Valle">Cali, Valle</option>
                    <option value="Barranquilla, Atlántico">Barranquilla, Atlántico</option>
                    <option value="Cartagena, Bolívar">Cartagena, Bolívar</option>
                  </select>
                </div>

                <div>
                  <label className="block text-xs font-bold text-slate-600 mb-1.5 uppercase">Peso (kg)</label>
                  <input
                    type="number"
                    min="1"
                    max="100"
                    name="peso"
                    value={cotizacion.peso}
                    onChange={handleCotizarChange}
                    className="w-full p-2.5 bg-slate-100 border border-slate-300 rounded-lg text-xs font-semibold text-slate-800 outline-none focus:border-emerald-500"
                  />
                </div>
              </div>

              {/* Resultado Cotización */}
              <div className="bg-emerald-50 border border-emerald-200 rounded-xl p-4 flex flex-col sm:flex-row items-center justify-between gap-4">
                <div>
                  <span className="text-xs text-slate-500 font-semibold block">Valor Estimado del Envío</span>
                  <span className="text-2xl font-black text-emerald-700">{resultadoCotizacion.formattedPrice}</span>
                  <span className="text-[11px] text-slate-500 block mt-0.5">Tiempo estimado: <strong className="text-slate-800">{resultadoCotizacion.deliveryDays}</strong></span>
                </div>
                <Link
                  to="/cotizador"
                  className="w-full sm:w-auto px-5 py-2.5 bg-emerald-600 hover:bg-emerald-700 text-white text-xs font-bold rounded-lg shadow transition text-center"
                >
                  Ver Cotización Detallada →
                </Link>
              </div>
            </div>

          </div>
        </div>
      </section>

      {/* NUESTROS SERVICIOS DE ENVÍO */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center max-w-3xl mx-auto mb-12">
          <h2 className="text-xs font-bold text-emerald-600 uppercase tracking-widest">Servicios Especializados</h2>
          <p className="mt-2 text-3xl font-extrabold text-slate-900 tracking-tight sm:text-4xl">
            Soluciones Logísticas a la Medida de tus Necesidades
          </p>
          <p className="mt-3 text-sm text-slate-500">
            Ya sea un sobre urgente o una carga industrial, tenemos el vehículo y la ruta idónea.
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          
          <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm hover:shadow-md transition-all group">
            <div className="w-12 h-12 bg-emerald-100 text-emerald-600 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
              <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
              </svg>
            </div>
            <h3 className="text-lg font-bold text-slate-900 mb-2">Envíos Exprés 24H</h3>
            <p className="text-xs text-slate-500 leading-relaxed mb-4">
              Entregas prioritarias en las principales ciudades del país antes de 24 horas laborables.
            </p>
            <Link to="/servicios" className="text-xs font-bold text-emerald-600 hover:underline">Saber más →</Link>
          </div>

          <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm hover:shadow-md transition-all group">
            <div className="w-12 h-12 bg-indigo-100 text-indigo-600 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
              <img src={PaqueteIcon} alt="Paquetería" className="w-6 h-6" />
            </div>
            <h3 className="text-lg font-bold text-slate-900 mb-2">Paquetería Nacional</h3>
            <p className="text-xs text-slate-500 leading-relaxed mb-4">
              Servicio estándar seguro y económico con recolección directa en la puerta de tu hogar u oficina.
            </p>
            <Link to="/servicios" className="text-xs font-bold text-indigo-600 hover:underline">Saber más →</Link>
          </div>

          <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm hover:shadow-md transition-all group">
            <div className="w-12 h-12 bg-amber-100 text-amber-600 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
              <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10" />
              </svg>
            </div>
            <h3 className="text-lg font-bold text-slate-900 mb-2">Carga Pesada & Empresarial</h3>
            <p className="text-xs text-slate-500 leading-relaxed mb-4">
              Transporte masivo de mercancías y pallets con furgones y camiones dedicados.
            </p>
            <Link to="/servicios" className="text-xs font-bold text-amber-600 hover:underline">Saber más →</Link>
          </div>

          <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm hover:shadow-md transition-all group">
            <div className="w-12 h-12 bg-teal-100 text-teal-600 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
              <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z" />
              </svg>
            </div>
            <h3 className="text-lg font-bold text-slate-900 mb-2">Soluciones e-Commerce</h3>
            <p className="text-xs text-slate-500 leading-relaxed mb-4">
              Integración API para tiendas virtuales, pago contra entrega (COD) y gestión de devoluciones.
            </p>
            <Link to="/servicios" className="text-xs font-bold text-teal-600 hover:underline">Saber más →</Link>
          </div>

        </div>
      </section>

      {/* METRICAS DE CONFIANZA */}
      <section className="bg-slate-900 text-white py-14">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-8 text-center">
            <div>
              <span className="text-4xl sm:text-5xl font-black text-emerald-400 block">+500K</span>
              <span className="text-xs sm:text-sm text-slate-400 font-medium mt-1 block">Envíos Entregados</span>
            </div>
            <div>
              <span className="text-4xl sm:text-5xl font-black text-teal-300 block">99.4%</span>
              <span className="text-xs sm:text-sm text-slate-400 font-medium mt-1 block">Entregas a Tiempo</span>
            </div>
            <div>
              <span className="text-4xl sm:text-5xl font-black text-cyan-400 block">+120</span>
              <span className="text-xs sm:text-sm text-slate-400 font-medium mt-1 block">Aliados Logísticos</span>
            </div>
            <div>
              <span className="text-4xl sm:text-5xl font-black text-emerald-400 block">50+</span>
              <span className="text-xs sm:text-sm text-slate-400 font-medium mt-1 block">Ciudades Conectadas</span>
            </div>
          </div>
        </div>
      </section>

      {/* POR QUÉ ELEGIR FASTER ENVÍOS */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          
          <div className="space-y-6">
            <span className="text-xs font-bold text-emerald-600 uppercase tracking-widest">Ventajas Competitivas</span>
            <h2 className="text-3xl sm:text-4xl font-extrabold text-slate-900 tracking-tight">
              ¿Por qué las Empresas y Familias Confían en Faster Envíos?
            </h2>
            <p className="text-sm text-slate-600 leading-relaxed">
              Combinamos infraestructura física de vanguardia con herramientas tecnológicas que te brindan tranquilidad absoluta desde el despacho hasta la firma en destino.
            </p>

            <div className="space-y-4 pt-2">
              <div className="flex gap-4">
                <div className="w-10 h-10 bg-emerald-100 text-emerald-600 rounded-lg flex items-center justify-center shrink-0">
                  <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2.5" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                </div>
                <div>
                  <h4 className="text-base font-bold text-slate-800">Seguridad & Seguro 100% Declarado</h4>
                  <p className="text-xs text-slate-500">Cada paquete cuenta con póliza de protección contra pérdidas o averías.</p>
                </div>
              </div>

              <div className="flex gap-4">
                <div className="w-10 h-10 bg-indigo-100 text-indigo-600 rounded-lg flex items-center justify-center shrink-0">
                  <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2.5" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                  </svg>
                </div>
                <div>
                  <h4 className="text-base font-bold text-slate-800">Trazabilidad Satelital GPS</h4>
                  <p className="text-xs text-slate-500">Notificaciones automáticas por mensaje y correo en cada estado del envío.</p>
                </div>
              </div>

              <div className="flex gap-4">
                <div className="w-10 h-10 bg-teal-100 text-teal-600 rounded-lg flex items-center justify-center shrink-0">
                  <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2.5" d="M18.364 5.636l-3.536 3.536m0 5.656l3.536 3.536M9.172 9.172L5.636 5.636m3.536 9.192l-3.536 3.536M21 12a9 9 0 11-18 0 9 9 0 0118 0zm-5 0a4 4 0 11-8 0 4 4 0 018 0z" />
                  </svg>
                </div>
                <div>
                  <h4 className="text-base font-bold text-slate-800">Soporte Humano 24/7</h4>
                  <p className="text-xs text-slate-500">Atención personalizada para coordinar recolecciones o cambios de dirección.</p>
                </div>
              </div>
            </div>
          </div>

          <div className="bg-gradient-to-br from-slate-100 to-slate-200 rounded-3xl p-8 border border-slate-300 relative shadow-inner">
            <div className="bg-white rounded-2xl p-6 shadow-md space-y-4">
              <div className="flex items-center gap-3">
                <div className="w-3 h-3 rounded-full bg-emerald-500"></div>
                <span className="text-xs font-bold text-slate-800 uppercase">Centro Logístico Inteligente</span>
              </div>
              <p className="text-xs text-slate-600">
                Nuestra plataforma automatiza la asignación de rutas óptimas para evitar congestiones y reducir tiempos de entrega hasta en un 35%.
              </p>
              <div className="pt-2 border-t border-slate-100 flex items-center justify-between text-xs">
                <span className="text-slate-500">Velocidad promedio:</span>
                <span className="font-bold text-emerald-600">98.5% Dentro del tiempo estimado</span>
              </div>
            </div>
          </div>

        </div>
      </section>

      {/* CTA REGISTRO / CONTACTO */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="bg-slate-900 text-white rounded-3xl p-8 sm:p-12 text-center relative overflow-hidden shadow-2xl">
          <div className="relative z-10 max-w-2xl mx-auto space-y-6">
            <h2 className="text-3xl sm:text-4xl font-extrabold tracking-tight">
              ¿Listo para enviar tus paquetes con la máxima velocidad?
            </h2>
            <p className="text-sm text-slate-300">
              Crea tu cuenta gratuita en menos de 2 minutos para programar recolecciones, guardar tus direcciones frecuentes y acceder a tarifas preferenciales.
            </p>
            <div className="flex flex-wrap justify-center gap-4 pt-2">
              <Link
                to="/register"
                className="px-8 py-3.5 bg-emerald-500 hover:bg-emerald-600 text-slate-950 font-extrabold rounded-xl shadow-lg transition text-sm"
              >
                Crear Cuenta Gratis
              </Link>
              <Link
                to="/contacto"
                className="px-8 py-3.5 bg-slate-800 hover:bg-slate-700 text-white font-bold rounded-xl border border-slate-700 transition text-sm"
              >
                Contactar a Asesor
              </Link>
            </div>
          </div>
        </div>
      </section>

    </div>
  );
};

export default HomePage;

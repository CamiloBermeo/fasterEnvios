import React from "react";
import { Link } from "react-router-dom";

const ServiciosPage = () => {
  const servicios = [
    {
      id: "express",
      icon: "⚡",
      title: "Envíos Exprés 24H",
      subtitle: "Entrega urgente de sobres y paquetes prioritarios",
      description: "Garantizamos la entrega en ciudades principales en menos de 24 horas laborables. Incluye recolección urgente en menos de 60 minutos en Bogotá y Medellín.",
      badge: "Prioridad Máxima",
      badgeColor: "bg-emerald-100 text-emerald-800"
    },
    {
      id: "nacional",
      icon: "📦",
      title: "Paquetería Nacional Estándar",
      subtitle: "La solución económica y confiable para todo el país",
      description: "Servicio de transporte terrestre para documentos y mercancías con cobertura en más de 50 municipios de Colombia. Trazabilidad completa por código de barras.",
      badge: "Más Popular",
      badgeColor: "bg-indigo-100 text-indigo-800"
    },
    {
      id: "carga",
      icon: "🚛",
      title: "Carga Pesada & Mercancías",
      subtitle: "Furgones y camiones dedicados para empresas",
      description: "Manejo de mercancía sobre-dimensionada, pallets y consolidado industrial. Seguro de valor declarado extendido y acompañamiento vehicular opcional.",
      badge: "Empresarial",
      badgeColor: "bg-amber-100 text-amber-800"
    },
    {
      id: "ecommerce",
      icon: "🛒",
      title: "Soluciones e-Commerce & COD",
      subtitle: "Impulsa tu tienda virtual con pago contra entrega",
      description: "Integración vía API para Shopify, WooCommerce o desarrollos a medida. Recaudamos el valor del producto al momento de entregar y lo transferimos a tu cuenta.",
      badge: "Negocios Digitales",
      badgeColor: "bg-teal-100 text-teal-800"
    }
  ];

  return (
    <div className="space-y-16 pb-16">
      
      {/* Hero Header */}
      <section className="bg-slate-900 text-white py-16 text-center relative overflow-hidden">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 space-y-3 relative z-10">
          <span className="bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 px-3.5 py-1 rounded-full text-xs font-bold uppercase tracking-wider">
            Portafolio de Soluciones
          </span>
          <h1 className="text-3xl sm:text-5xl font-black">Nuestros Servicios Logísticos</h1>
          <p className="text-xs sm:text-sm text-slate-300 max-w-xl mx-auto">
            Diseñados para brindar velocidad, trazabilidad y máxima seguridad en cada trayecto.
          </p>
        </div>
      </section>

      {/* Grid de Servicios */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          {servicios.map((s) => (
            <div key={s.id} className="bg-white p-8 rounded-3xl border border-slate-200 shadow-sm hover:shadow-md transition space-y-4">
              <div className="flex justify-between items-start">
                <span className="text-3xl p-3 bg-slate-100 rounded-2xl">{s.icon}</span>
                <span className={`text-[10px] font-extrabold px-3 py-1 rounded-full uppercase ${s.badgeColor}`}>
                  {s.badge}
                </span>
              </div>
              <h2 className="text-xl font-bold text-slate-900">{s.title}</h2>
              <h3 className="text-xs font-semibold text-emerald-600">{s.subtitle}</h3>
              <p className="text-xs text-slate-500 leading-relaxed">{s.description}</p>
              
              <div className="pt-3 border-t border-slate-100 flex items-center justify-between">
                <Link to="/cotizador" className="text-xs font-bold text-slate-800 hover:text-emerald-600 transition">
                  Cotizar este servicio →
                </Link>
                <Link to="/contacto" className="text-xs font-semibold text-slate-400 hover:text-slate-600">
                  Asesoría dedicada
                </Link>
              </div>
            </div>
          ))}
        </div>
      </section>

    </div>
  );
};

export default ServiciosPage;

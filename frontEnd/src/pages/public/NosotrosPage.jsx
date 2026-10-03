import React from "react";
import { Link } from "react-router-dom";
import LogoAguila from "../../assets/Logo-aguila.png";

const NosotrosPage = () => {
  return (
    <div className="space-y-16 pb-16">
      {/* Hero Header */}
      <section className="bg-slate-900 text-white py-16 lg:py-24 relative overflow-hidden">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative z-10 space-y-4">
          <span className="bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 px-3.5 py-1 rounded-full text-xs font-bold uppercase tracking-wider">
            Nuestra Historia & Compromiso
          </span>
          <h1 className="text-3xl sm:text-5xl font-black tracking-tight">
            Acerca de <span className="text-emerald-400">Faster Envíos</span>
          </h1>
          <p className="max-w-2xl mx-auto text-sm sm:text-base text-slate-300 leading-relaxed">
            Somos el aliado estratégico en transporte, paquetería y logística integral para miles de empresas, comercios y familias en Colombia.
          </p>
        </div>
      </section>

      {/* Story & Mission Section */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-12 items-center">
          <div className="space-y-6">
            <h2 className="text-2xl sm:text-3xl font-black text-slate-900 tracking-tight">
              Transformando la Distribución de Paquetería Nacional
            </h2>
            <p className="text-sm text-slate-600 leading-relaxed">
              Nacimos con la firme convicción de revolucionar los tiempos de entrega en el país. Entendemos que detrás de cada paquete hay un sueño, una venta urgente o una sorpresa para un ser querido.
            </p>
            <p className="text-sm text-slate-600 leading-relaxed">
              Por eso, hemos construido una infraestructura sólida respaldada por conductores aliados certificados, tecnología de monitoreo GPS y centros de acopio estratégicamente ubicados en los principales corredores viales.
            </p>
            <div className="grid grid-cols-2 gap-4 pt-2">
              <div className="p-4 bg-emerald-50 border border-emerald-200 rounded-xl">
                <span className="text-2xl font-black text-emerald-700 block">100%</span>
                <span className="text-xs text-slate-600 font-semibold">Empresa Colombiana</span>
              </div>
              <div className="p-4 bg-indigo-50 border border-indigo-200 rounded-xl">
                <span className="text-2xl font-black text-indigo-700 block">+120</span>
                <span className="text-xs text-slate-600 font-semibold">Aliados Certificados</span>
              </div>
            </div>
          </div>

          <div className="bg-white p-8 rounded-3xl border border-slate-200 shadow-lg flex flex-col items-center text-center space-y-4">
            <img src={LogoAguila} alt="Faster Envíos Águila" className="h-32 w-auto object-contain" />
            <h3 className="text-xl font-bold text-slate-800">El Águila Faster</h3>
            <p className="text-xs text-slate-500 leading-relaxed max-w-sm">
              Nuestro emblema del águila representa la agilidad en el aire, la visión panorámica de nuestras rutas y la velocidad de despacho que nos caracteriza.
            </p>
          </div>
        </div>
      </section>

      {/* Misión, Visión, Valores */}
      <section className="bg-slate-100 py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            
            <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-3">
              <div className="w-10 h-10 bg-emerald-100 text-emerald-600 rounded-lg flex items-center justify-center font-bold">
                🎯
              </div>
              <h3 className="text-lg font-bold text-slate-900">Nuestra Misión</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Brindar soluciones de transporte y logística eficiente, segura y oportuna, acortando distancias y maximizando la satisfacción de nuestros clientes.
              </p>
            </div>

            <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-3">
              <div className="w-10 h-10 bg-indigo-100 text-indigo-600 rounded-lg flex items-center justify-center font-bold">
                👁️
              </div>
              <h3 className="text-lg font-bold text-slate-900">Nuestra Visión</h3>
              <p className="text-xs text-slate-600 leading-relaxed">
                Ser consolidados para el 2030 como la red de mensajería y envíos express más confiable e innovadora de la región andina.
              </p>
            </div>

            <div className="bg-white p-6 rounded-2xl border border-slate-200 shadow-sm space-y-3">
              <div className="w-10 h-10 bg-amber-100 text-amber-600 rounded-lg flex items-center justify-center font-bold">
                ⭐
              </div>
              <h3 className="text-lg font-bold text-slate-900">Nuestros Valores</h3>
              <ul className="text-xs text-slate-600 space-y-1.5 list-disc list-inside">
                <li>Puntualidad Absoluta</li>
                <li>Transparencia Tarifaria</li>
                <li>Integridad en la Custodia</li>
                <li>Innovación Continua</li>
              </ul>
            </div>

          </div>
        </div>
      </section>

      {/* CTA */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
        <div className="bg-gradient-to-r from-emerald-700 to-teal-800 text-white rounded-3xl p-10 space-y-4">
          <h2 className="text-2xl sm:text-3xl font-bold">¿Deseas Trabajar o Ser Aliado con Nosotros?</h2>
          <p className="text-xs sm:text-sm text-emerald-100 max-w-xl mx-auto">
            Únete a nuestra red de transporte o integra tu comercio electrónico con nuestras soluciones logísticas.
          </p>
          <div className="pt-2 flex justify-center gap-4">
            <Link to="/login-aliado" className="px-6 py-3 bg-white text-emerald-800 font-extrabold text-xs rounded-xl hover:bg-emerald-50 transition">
              Portal de Aliados
            </Link>
            <Link to="/contacto" className="px-6 py-3 bg-emerald-900 text-white font-semibold text-xs rounded-xl hover:bg-emerald-950 transition border border-emerald-600">
              Contáctanos
            </Link>
          </div>
        </div>
      </section>

    </div>
  );
};

export default NosotrosPage;

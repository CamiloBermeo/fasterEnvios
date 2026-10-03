import React from "react";
import { Link } from "react-router-dom";
import Logo from "../../assets/Logo.png";

const Footer = () => {
  return (
    <footer className="bg-slate-900 text-slate-300 pt-16 pb-8 border-t border-slate-800">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-10 pb-12 border-b border-slate-800">
          
          {/* Brand Col */}
          <div className="lg:col-span-2 space-y-4">
            <div className="flex items-center gap-3">
              <img src={Logo} alt="Faster Envíos" className="h-12 w-auto bg-white/10 p-1.5 rounded-lg" />
              <div>
                <span className="text-xl font-black text-white tracking-wider block">
                  FASTER<span className="text-emerald-500">ENVÍOS</span>
                </span>
                <span className="text-xs text-slate-400 font-medium">
                  Operador Logístico Autorizado
                </span>
              </div>
            </div>
            <p className="text-sm text-slate-400 leading-relaxed max-w-sm">
              Conectamos a todo el país con envíos exprés, logística empresarial segura y la más avanzada tecnología de trazabilidad en tiempo real.
            </p>
            <div className="pt-2 flex items-center gap-3">
              <span className="inline-flex items-center gap-2 px-3 py-1 bg-emerald-950 text-emerald-400 text-xs font-semibold rounded-full border border-emerald-800">
                <span className="w-2 h-2 rounded-full bg-emerald-400 animate-pulse"></span>
                Red Nacional Activa
              </span>
              <span className="text-xs text-slate-400">+500k Envíos Realizados</span>
            </div>
          </div>

          {/* Quick Links */}
          <div className="space-y-3">
            <h3 className="text-sm font-bold text-white uppercase tracking-wider">Navegación</h3>
            <ul className="space-y-2 text-sm">
              <li>
                <Link to="/" className="hover:text-emerald-400 transition-colors">
                  Inicio
                </Link>
              </li>
              <li>
                <Link to="/servicios" className="hover:text-emerald-400 transition-colors">
                  Servicios de Envío
                </Link>
              </li>
              <li>
                <Link to="/cotizador" className="hover:text-emerald-400 transition-colors">
                  Cotizador de Tarifas
                </Link>
              </li>
              <li>
                <Link to="/rastrear" className="hover:text-emerald-400 transition-colors">
                  Rastrear Guía
                </Link>
              </li>
              <li>
                <Link to="/nosotros" className="hover:text-emerald-400 transition-colors">
                  Acerca de Nosotros
                </Link>
              </li>
              <li>
                <Link to="/contacto" className="hover:text-emerald-400 transition-colors">
                  Sedes y Contacto
                </Link>
              </li>
            </ul>
          </div>

          {/* Logistics Services */}
          <div className="space-y-3">
            <h3 className="text-sm font-bold text-white uppercase tracking-wider">Servicios</h3>
            <ul className="space-y-2 text-sm text-slate-400">
              <li className="hover:text-white transition cursor-pointer">Envíos Exprés 24h</li>
              <li className="hover:text-white transition cursor-pointer">Paquetería Nacional</li>
              <li className="hover:text-white transition cursor-pointer">Carga Pesada & Mercancías</li>
              <li className="hover:text-white transition cursor-pointer">Mensajería Urbana e-Commerce</li>
              <li className="hover:text-white transition cursor-pointer">Almacenamiento & Bodega</li>
              <li className="hover:text-white transition cursor-pointer">Envíos Internacionales</li>
            </ul>
          </div>

          {/* Contact Info */}
          <div className="space-y-3">
            <h3 className="text-sm font-bold text-white uppercase tracking-wider">Contacto</h3>
            <ul className="space-y-3 text-sm text-slate-400">
              <li className="flex items-start gap-2.5">
                <svg className="w-5 h-5 text-emerald-500 shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
                <span>Av. Principal de Logística #45-12, Bogotá, Colombia</span>
              </li>
              <li className="flex items-center gap-2.5">
                <svg className="w-5 h-5 text-emerald-500 shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                </svg>
                <span>PBX: (+57) 601 555-0199</span>
              </li>
              <li className="flex items-center gap-2.5">
                <svg className="w-5 h-5 text-emerald-500 shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                </svg>
                <span>contacto@fasterenvios.com</span>
              </li>
            </ul>
          </div>
        </div>

        {/* Bottom bar */}
        <div className="pt-8 flex flex-col md:flex-row justify-between items-center gap-4 text-xs text-slate-500">
          <p>© {new Date().getFullYear()} Faster Envíos S.A.S. Todos los derechos reservados.</p>
          <div className="flex items-center gap-6">
            <span className="hover:text-slate-400 cursor-pointer transition">Términos y Condiciones</span>
            <span className="hover:text-slate-400 cursor-pointer transition">Política de Tratamiento de Datos</span>
            <span className="hover:text-slate-400 cursor-pointer transition">Supertransporte</span>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;

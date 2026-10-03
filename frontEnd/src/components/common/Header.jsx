import React, { useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import Logo from "../../assets/Logo.png";
import SearchIcon from "../../assets/search.svg";
import useAuth from "../../hooks/useAuth.jsx";

const Header = () => {
  const [menuAbierto, setMenuAbierto] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const [trackingInput, setTrackingInput] = useState("");
  const location = useLocation();
  const navigate = useNavigate();
  const { auth, guardarAuth } = useAuth();

  const handleQuickTrack = (e) => {
    e.preventDefault();
    if (trackingInput.trim()) {
      navigate(`/rastrear?guia=${encodeURIComponent(trackingInput.trim())}`);
      setTrackingInput("");
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    guardarAuth({});
    navigate("/home");
  };

  const isActive = (path) => location.pathname === path;

  return (
    <header className="sticky top-0 z-50 bg-white/95 backdrop-blur-md border-b border-slate-200 shadow-sm transition-all">
      {/* Top Notification Bar */}
      <div className="bg-gradient-to-r from-emerald-700 via-emerald-600 to-teal-700 text-white text-xs py-1.5 px-4 text-center font-medium flex justify-between items-center px-6 sm:px-12">
        <div className="hidden sm:flex items-center gap-4">
          <span className="flex items-center gap-1">
            <svg className="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
            </svg>
            Línea Gratuita Nacional: 018000-910-300
          </span>
          <span className="hidden md:inline">|</span>
          <span className="hidden md:flex items-center gap-1">
            <svg className="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            Atención Lunes a Sábado 7:00 AM - 8:00 PM
          </span>
        </div>
        <div className="mx-auto sm:mx-0 flex items-center gap-3">
          <span className="bg-emerald-800/60 px-2 py-0.5 rounded text-[11px] font-semibold tracking-wide">
            ⚡ ENTREGAS 24H GARANTIZADAS
          </span>
          <Link to="/cotizador" className="underline hover:text-emerald-200 transition">
            Calcula tu tarifa aquí →
          </Link>
        </div>
      </div>

      {/* Main Navbar */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-20 gap-4">
          
          {/* Logo & Brand */}
          <Link to="/" className="flex items-center gap-3 group shrink-0">
            <img 
              src={Logo} 
              alt="Faster Envíos" 
              className="h-12 w-auto object-contain transition-transform duration-300 group-hover:scale-105" 
            />
            <div className="flex flex-col">
              <span className="text-xl font-extrabold text-slate-900 tracking-tight leading-none group-hover:text-emerald-600 transition-colors">
                FASTER<span className="text-emerald-600 font-black">ENVÍOS</span>
              </span>
              <span className="text-[10px] text-slate-500 font-semibold tracking-wider uppercase">
                Soluciones Logísticas Integrales
              </span>
            </div>
          </Link>

          {/* Desktop Navigation Links */}
          <nav className="hidden lg:flex items-center space-x-1 xl:space-x-2">
            <Link
              to="/"
              className={`px-3 py-2 rounded-lg text-sm font-semibold transition-all ${
                isActive('/') || isActive('/home')
                  ? 'text-emerald-600 bg-emerald-50'
                  : 'text-slate-700 hover:text-emerald-600 hover:bg-slate-50'
              }`}
            >
              Inicio
            </Link>
            <Link
              to="/servicios"
              className={`px-3 py-2 rounded-lg text-sm font-semibold transition-all ${
                isActive('/servicios')
                  ? 'text-emerald-600 bg-emerald-50'
                  : 'text-slate-700 hover:text-emerald-600 hover:bg-slate-50'
              }`}
            >
              Servicios
            </Link>
            <Link
              to="/cotizador"
              className={`px-3 py-2 rounded-lg text-sm font-semibold transition-all ${
                isActive('/cotizador')
                  ? 'text-emerald-600 bg-emerald-50'
                  : 'text-slate-700 hover:text-emerald-600 hover:bg-slate-50'
              }`}
            >
              Cotizador
            </Link>
            <Link
              to="/rastrear"
              className={`px-3 py-2 rounded-lg text-sm font-semibold transition-all ${
                isActive('/rastrear')
                  ? 'text-emerald-600 bg-emerald-50'
                  : 'text-slate-700 hover:text-emerald-600 hover:bg-slate-50'
              }`}
            >
              Rastrear Guía
            </Link>
            <Link
              to="/nosotros"
              className={`px-3 py-2 rounded-lg text-sm font-semibold transition-all ${
                isActive('/nosotros')
                  ? 'text-emerald-600 bg-emerald-50'
                  : 'text-slate-700 hover:text-emerald-600 hover:bg-slate-50'
              }`}
            >
              Nosotros
            </Link>
            <Link
              to="/contacto"
              className={`px-3 py-2 rounded-lg text-sm font-semibold transition-all ${
                isActive('/contacto')
                  ? 'text-emerald-600 bg-emerald-50'
                  : 'text-slate-700 hover:text-emerald-600 hover:bg-slate-50'
              }`}
            >
              Contacto
            </Link>
          </nav>

          {/* Quick Tracking & Auth Actions */}
          <div className="hidden md:flex items-center gap-3">
            {/* Quick Track Input Bar */}
            <form onSubmit={handleQuickTrack} className="relative flex items-center">
              <input
                type="text"
                value={trackingInput}
                onChange={(e) => setTrackingInput(e.target.value)}
                placeholder="Rastrear Guía (ej: FE-892341)..."
                className="w-48 xl:w-56 pl-3 pr-9 py-1.5 text-xs bg-slate-100 border border-slate-300 rounded-full outline-none focus:w-64 focus:bg-white focus:border-emerald-500 focus:ring-2 focus:ring-emerald-200 transition-all duration-300"
              />
              <button 
                type="submit" 
                aria-label="Rastrear guía"
                className="absolute right-1 p-1 text-slate-500 hover:text-emerald-600 hover:bg-emerald-50 rounded-full transition"
              >
                <img src={SearchIcon} alt="Buscar" className="w-4 h-4" />
              </button>
            </form>

            {/* User Session Buttons */}
            {auth?.id ? (
              <div className="flex items-center gap-2">
                <Link
                  to="/dashboard-aliado"
                  className="px-4 py-2 bg-emerald-600 hover:bg-emerald-700 text-white rounded-lg text-xs font-bold shadow-sm transition"
                >
                  Mi Panel ({auth.name || 'Usuario'})
                </Link>
                <button
                  onClick={handleLogout}
                  className="px-3 py-2 bg-slate-100 hover:bg-slate-200 text-slate-700 rounded-lg text-xs font-semibold transition"
                >
                  Salir
                </button>
              </div>
            ) : (
              <div className="relative">
                <button
                  onClick={() => setMenuAbierto(!menuAbierto)}
                  className="flex items-center gap-2 px-4 py-2 text-xs font-bold text-white bg-emerald-600 hover:bg-emerald-700 rounded-lg shadow-sm hover:shadow transition-all duration-200 cursor-pointer"
                >
                  <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                  </svg>
                  Iniciar Sesión
                  <svg className={`w-3.5 h-3.5 transition-transform ${menuAbierto ? 'rotate-180' : ''}`} fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 9l-7 7-7-7" />
                  </svg>
                </button>

                {/* Dropdown menu */}
                {menuAbierto && (
                  <div 
                    className="absolute right-0 mt-2 w-48 bg-white rounded-xl shadow-xl border border-slate-100 py-2 z-50 animate-in fade-in slide-in-from-top-2 duration-200"
                    onMouseLeave={() => setMenuAbierto(false)}
                  >
                    <Link
                      to="/login"
                      onClick={() => setMenuAbierto(false)}
                      className="flex items-center gap-2 px-4 py-2.5 text-xs font-semibold text-slate-700 hover:bg-emerald-50 hover:text-emerald-700 transition"
                    >
                      <span className="w-2 h-2 rounded-full bg-emerald-500"></span>
                      Portal Cliente
                    </Link>
                    <Link
                      to="/login-aliado"
                      onClick={() => setMenuAbierto(false)}
                      className="flex items-center gap-2 px-4 py-2.5 text-xs font-semibold text-slate-700 hover:bg-emerald-50 hover:text-emerald-700 transition"
                    >
                      <span className="w-2 h-2 rounded-full bg-indigo-500"></span>
                      Portal Aliado / Conductor
                    </Link>
                    <div className="border-t border-slate-100 my-1"></div>
                    <Link
                      to="/register"
                      onClick={() => setMenuAbierto(false)}
                      className="flex items-center gap-2 px-4 py-2.5 text-xs font-semibold text-emerald-600 hover:bg-emerald-100/50 transition"
                    >
                      ✨ Crear Cuenta Nueva
                    </Link>
                  </div>
                )}
              </div>
            )}
          </div>

          {/* Mobile Menu Toggle Button */}
          <div className="lg:hidden flex items-center gap-2">
            <button
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
              className="p-2 text-slate-700 hover:text-emerald-600 hover:bg-slate-100 rounded-lg transition"
              aria-label="Abrir menú"
            >
              <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                {mobileMenuOpen ? (
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" />
                ) : (
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16" />
                )}
              </svg>
            </button>
          </div>
        </div>

        {/* Mobile Navigation Drawer */}
        {mobileMenuOpen && (
          <div className="lg:hidden bg-white border-t border-slate-200 py-4 px-2 space-y-2 animate-in slide-in-from-top duration-200">
            <form onSubmit={handleQuickTrack} className="mb-3 px-2">
              <div className="relative">
                <input
                  type="text"
                  value={trackingInput}
                  onChange={(e) => setTrackingInput(e.target.value)}
                  placeholder="Número de guía..."
                  className="w-full pl-3 pr-9 py-2 text-sm bg-slate-100 border border-slate-300 rounded-lg"
                />
                <button type="submit" className="absolute right-2 top-2.5 text-slate-500">
                  <img src={SearchIcon} alt="Buscar" className="w-4 h-4" />
                </button>
              </div>
            </form>

            <Link
              to="/"
              onClick={() => setMobileMenuOpen(false)}
              className="block px-3 py-2 rounded-md text-base font-semibold text-slate-800 hover:bg-emerald-50 hover:text-emerald-600"
            >
              Inicio
            </Link>
            <Link
              to="/servicios"
              onClick={() => setMobileMenuOpen(false)}
              className="block px-3 py-2 rounded-md text-base font-semibold text-slate-800 hover:bg-emerald-50 hover:text-emerald-600"
            >
              Servicios
            </Link>
            <Link
              to="/cotizador"
              onClick={() => setMobileMenuOpen(false)}
              className="block px-3 py-2 rounded-md text-base font-semibold text-slate-800 hover:bg-emerald-50 hover:text-emerald-600"
            >
              Cotizador de Envíos
            </Link>
            <Link
              to="/rastrear"
              onClick={() => setMobileMenuOpen(false)}
              className="block px-3 py-2 rounded-md text-base font-semibold text-slate-800 hover:bg-emerald-50 hover:text-emerald-600"
            >
              Rastrear Guía
            </Link>
            <Link
              to="/nosotros"
              onClick={() => setMobileMenuOpen(false)}
              className="block px-3 py-2 rounded-md text-base font-semibold text-slate-800 hover:bg-emerald-50 hover:text-emerald-600"
            >
              Nosotros
            </Link>
            <Link
              to="/contacto"
              onClick={() => setMobileMenuOpen(false)}
              className="block px-3 py-2 rounded-md text-base font-semibold text-slate-800 hover:bg-emerald-50 hover:text-emerald-600"
            >
              Contacto
            </Link>

            <div className="border-t border-slate-100 pt-3 space-y-2">
              <Link
                to="/login"
                onClick={() => setMobileMenuOpen(false)}
                className="block text-center w-full py-2.5 bg-emerald-600 text-white font-bold rounded-lg"
              >
                Ingreso Cliente
              </Link>
              <Link
                to="/login-aliado"
                onClick={() => setMobileMenuOpen(false)}
                className="block text-center w-full py-2.5 bg-slate-800 text-white font-bold rounded-lg"
              >
                Ingreso Aliado
              </Link>
              <Link
                to="/register"
                onClick={() => setMobileMenuOpen(false)}
                className="block text-center w-full py-2.5 bg-emerald-50 text-emerald-700 font-semibold rounded-lg border border-emerald-200"
              >
                Crear Cuenta
              </Link>
            </div>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;

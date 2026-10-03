import React, { useState } from "react";

const ContactoPage = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    email: "",
    telefono: "",
    asunto: "consulta_general",
    mensaje: ""
  });
  const [enviado, setEnviado] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    if (formData.nombre && formData.email && formData.mensaje) {
      setEnviado(true);
      setFormData({ nombre: "", email: "", telefono: "", asunto: "consulta_general", mensaje: "" });
      setTimeout(() => setEnviado(false), 5000);
    }
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <div className="space-y-16 pb-16">
      
      {/* Hero Header */}
      <section className="bg-slate-900 text-white py-16 relative overflow-hidden">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center space-y-3 relative z-10">
          <span className="bg-emerald-500/20 text-emerald-300 border border-emerald-500/30 px-3.5 py-1 rounded-full text-xs font-bold uppercase tracking-wider">
            Estamos para Atenderte
          </span>
          <h1 className="text-3xl sm:text-5xl font-black">Centro de Atención & Contacto</h1>
          <p className="text-xs sm:text-sm text-slate-300 max-w-xl mx-auto">
            ¿Tienes dudas sobre un envío, deseas una cotización empresarial o quieres integrarte como aliado? Completa el formulario o visítanos.
          </p>
        </div>
      </section>

      {/* Main Content: Form + Direct Contact Info */}
      <section className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-10 items-start">
          
          {/* Contact Form */}
          <div className="lg:col-span-7 bg-white p-8 rounded-3xl border border-slate-200 shadow-md">
            <h2 className="text-xl font-bold text-slate-800 mb-2">Envíanos un Mensaje Directo</h2>
            <p className="text-xs text-slate-500 mb-6">Responderemos tu solicitud en menos de 2 horas en horario laboral.</p>

            {enviado && (
              <div className="mb-6 p-4 bg-emerald-50 border border-emerald-300 text-emerald-800 rounded-xl text-xs font-bold flex items-center gap-2">
                <span>✅ ¡Mensaje enviado con éxito! Un asesor se pondrá en contacto contigo muy pronto.</span>
              </div>
            )}

            <form onSubmit={handleSubmit} className="space-y-4">
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div>
                  <label className="block text-xs font-bold text-slate-700 mb-1">Nombre Completo *</label>
                  <input
                    type="text"
                    required
                    name="nombre"
                    value={formData.nombre}
                    onChange={handleChange}
                    placeholder="Ej: Carlos Mendoza"
                    className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs outline-none focus:border-emerald-500 focus:bg-white"
                  />
                </div>
                <div>
                  <label className="block text-xs font-bold text-slate-700 mb-1">Correo Electrónico *</label>
                  <input
                    type="email"
                    required
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="ejemplo@correo.com"
                    className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs outline-none focus:border-emerald-500 focus:bg-white"
                  />
                </div>
              </div>

              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div>
                  <label className="block text-xs font-bold text-slate-700 mb-1">Teléfono / Celular</label>
                  <input
                    type="tel"
                    name="telefono"
                    value={formData.telefono}
                    onChange={handleChange}
                    placeholder="+57 300 000 0000"
                    className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs outline-none focus:border-emerald-500 focus:bg-white"
                  />
                </div>
                <div>
                  <label className="block text-xs font-bold text-slate-700 mb-1">Tipo de Solicitud</label>
                  <select
                    name="asunto"
                    value={formData.asunto}
                    onChange={handleChange}
                    className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs outline-none focus:border-emerald-500"
                  >
                    <option value="consulta_general">Consulta de Guía / Estado</option>
                    <option value="cotizacion_empresarial">Cotización Empresarial</option>
                    <option value="reclamacion">Novedades o Reclamaciones</option>
                    <option value="aliado_transporte">Quiero ser Conductor / Aliado</option>
                  </select>
                </div>
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">Mensaje o Detalle *</label>
                <textarea
                  required
                  rows="4"
                  name="mensaje"
                  value={formData.mensaje}
                  onChange={handleChange}
                  placeholder="Escribe aquí tu duda, número de guía o detalle de solicitud..."
                  className="w-full p-3 bg-slate-50 border border-slate-300 rounded-xl text-xs outline-none focus:border-emerald-500 focus:bg-white"
                ></textarea>
              </div>

              <button
                type="submit"
                className="w-full py-3.5 bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-xs rounded-xl shadow-md transition cursor-pointer"
              >
                Enviar Solicitud
              </button>
            </form>
          </div>

          {/* Contact Direct Cards */}
          <div className="lg:col-span-5 space-y-6">
            
            <div className="bg-white p-6 rounded-3xl border border-slate-200 shadow-sm space-y-4">
              <h3 className="text-lg font-bold text-slate-800">Canales Directos</h3>
              
              <div className="flex items-start gap-3">
                <div className="w-9 h-9 bg-emerald-100 text-emerald-600 rounded-lg flex items-center justify-center shrink-0">
                  📞
                </div>
                <div>
                  <span className="text-xs font-bold text-slate-700 block">Línea Nacional Gratuita</span>
                  <span className="text-xs text-slate-500 block">018000-910-300 / (+57) 601 555-0199</span>
                </div>
              </div>

              <div className="flex items-start gap-3">
                <div className="w-9 h-9 bg-indigo-100 text-indigo-600 rounded-lg flex items-center justify-center shrink-0">
                  ✉️
                </div>
                <div>
                  <span className="text-xs font-bold text-slate-700 block">Correos Electrónicos</span>
                  <span className="text-xs text-slate-500 block">soporte@fasterenvios.com</span>
                  <span className="text-xs text-slate-500 block">ventas@fasterenvios.com</span>
                </div>
              </div>

              <div className="flex items-start gap-3">
                <div className="w-9 h-9 bg-amber-100 text-amber-600 rounded-lg flex items-center justify-center shrink-0">
                  ⏰
                </div>
                <div>
                  <span className="text-xs font-bold text-slate-700 block">Horario de Atención</span>
                  <span className="text-xs text-slate-500 block">Lunes a Viernes: 7:00 AM - 8:00 PM</span>
                  <span className="text-xs text-slate-500 block">Sábados: 8:00 AM - 4:00 PM</span>
                </div>
              </div>
            </div>

            {/* Principal Hub Cities */}
            <div className="bg-slate-900 text-white p-6 rounded-3xl space-y-3">
              <h3 className="text-sm font-bold uppercase tracking-wider text-emerald-400">Sedes Principales</h3>
              <div className="space-y-2 text-xs text-slate-300">
                <div className="flex justify-between border-b border-slate-800 pb-1.5">
                  <span className="font-semibold text-white">Bogotá, D.C.:</span>
                  <span>Av. Logística #45-12 Hub Norte</span>
                </div>
                <div className="flex justify-between border-b border-slate-800 pb-1.5">
                  <span className="font-semibold text-white">Medellín:</span>
                  <span>Calle 10 #32-50 El Poblado</span>
                </div>
                <div className="flex justify-between border-b border-slate-800 pb-1.5">
                  <span className="font-semibold text-white">Cali:</span>
                  <span>Carrera 1 #22-10 Zona Industrial</span>
                </div>
                <div className="flex justify-between">
                  <span className="font-semibold text-white">Barranquilla:</span>
                  <span>Vía 40 #76-180 Puerto</span>
                </div>
              </div>
            </div>

          </div>

        </div>
      </section>

    </div>
  );
};

export default ContactoPage;

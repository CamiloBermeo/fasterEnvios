import React from 'react'

import LogoAguila from "../../assets/Logo-aguila.png";
import PaqueteIcon from "../../assets/paquete.svg";
import AlertaIcon from "../../assets/icono-alerta.svg";
import EstadisticasIcon from "../../assets/estadisticas.svg";
import RepartidoresIcon from "../../assets/persona.svg";
import MensajesIcon from "../../assets/mensaje.svg";
import BodegaIcon from "../../assets/bodega.svg";
import AyudaIAIcon from "../../assets/robot-ia.svg";
import ConfiguracionesIcon from "../../assets/configuraciones.svg";
import SalirIcon from "../../assets/salir.svg";
import DashboardIcon from "../../assets/dashboard.svg";
const Sidebar = () => {
    return (
        <aside className='fixed inset-y-0 left-0 w-64 bg-white flex flex-col h-screen border-r border-gray-200 z-50 overflow-y-auto'>
            
            {/* Sección 1: Logo y Nombre */}
            <div className='flex justify-center h-25 py-6 px-4 pb-8'>
                <img className="align-center size-20" src={LogoAguila} alt="Logo" />
                <div className='flex items-center ml-2'>
                    <div className='flex items-end'>
                        <h3 className='text-2xl font-bold'>Faster</h3>
                    <span className="text-sm text-gray-600">Envios</span>
                    </div>
                    
                </div>
            </div>

            {/* Sección 2: Opciones de Menú */}
            <div className='flex flex-col  py-6 px-2'>
                <div className="flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg  cursor-pointer hover:bg-gray-200">
                    <img src={DashboardIcon} alt="Dashboard" className='w-5 h-5' />
                    <h2 className='text-base '>Dashboard</h2>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={PaqueteIcon} alt="Paquetes" className='w-5 h-5' />
                    <h3 className='text-base'>Paquetes</h3>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={AlertaIcon} alt="Alertas" className='w-5 h-5' />
                    <h3 className='text-base'>Alertas</h3>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={EstadisticasIcon} alt="Estadísticas" className='w-5 h-5' />
                    <h3 className='text-base'>Estadisticas</h3>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={RepartidoresIcon} alt="Repartidores" className='w-5 h-5' />
                    <h3 className='text-base'>Repartidores</h3>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={MensajesIcon} alt="Mensajes" className='w-5 h-5' />
                    <h3 className='text-base'>Mensajes</h3>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={BodegaIcon} alt="Bodega" className='w-5 h-5' />
                    <h3 className='text-base'>Bodega</h3>
                </div>
            </div>

            {/* Sección 3: Espacio Vacío */}
            <div className='flex-1'></div>

            {/* Sección 4: Opciones Inferiores */}
            <div className='flex flex-col  py-4 px-2'>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={AyudaIAIcon} alt="Ayuda IA" className='w-5 h-5' />
                    <h3 className='text-base'>Ayuda IA</h3>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={ConfiguracionesIcon} alt="Configuraciones" className='w-5 h-5' />
                    <h3 className='text-base'>Configuraciones</h3>
                </div>
                <div className='flex items-center w-auto h-10 gap-4 pl-8 pr-2 py-2 rounded-lg cursor-pointer hover:bg-gray-200'>
                    <img src={SalirIcon} alt="Salir" className='w-5 h-5' />
                    <h3 className='text-base'>Salir</h3>
                </div>
            </div>

        </aside>
    );
}
export default Sidebar;
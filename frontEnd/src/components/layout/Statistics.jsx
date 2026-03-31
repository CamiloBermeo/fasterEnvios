import React, { useState } from "react";
import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
    PieChart,
    Pie,
    Cell,
} from "recharts";



const Statistics = () => {
    const data = [
        { month: "Ene", envios: 400 },
        { month: "Feb", envios: 300 },
        { month: "Mar", envios: 500 },
        { month: "Abr", envios: 400 },
        { month: "May", envios: 600 },
        { month: "Jun", envios: 700 },
    ];

    const [fechaSeleccionada, setFechaSeleccionada] = useState(new Date());

    const datos = {
        "2026-04-1": { envios: 15, pendientes: 3 },
        "2026-04-2": { envios: 8, pendientes: 1 },
        "2026-04-3": { envios: 12, pendientes: 2 },
    };

    // Formateo de fecha para buscar en el objeto
    const fechaKey = fechaSeleccionada.toISOString().split("T")[0];
    const infoActual = datos[fechaKey] || { envios: 0, pendientes: 0 };
    const porcentajeSaturacion = infoActual.envios > 10 ? 85 : 40;
    const dataPie = [
        { value: porcentajeSaturacion },
        { value: 100 - porcentajeSaturacion },
    ];

    const obtenerSemana = (fecha) => {
        const diaSemana = fecha.getDay();//obtengo el día de la semana (0-6, donde 0 es domingo)
        const diasARestar = diaSemana === 0 ? 6 : diaSemana - 1;

        const lunes = new Date(fecha);
        lunes.setDate(fecha.getDate() - diasARestar); //resto los días necesarios para llegar al lunes

        return Array.from({ length: 7 }, (_, i) => {
            const dia = new Date(lunes);
            dia.setDate(lunes.getDate() + i);
            return dia;
        });
    }

    const esDiaSeleccionado = (dia) => 
    dia.getDate() === fechaSeleccionada.getDate() &&
    dia.getMonth() === fechaSeleccionada.getMonth() &&
    dia.getFullYear() === fechaSeleccionada.getFullYear();

    return (
        <div className="flex flex-col lg:flex-row gap-6 mt-2 w-full  items-start">
            {/* TARJETA 1: GRÁFICA */}
            <div className="flex flex-col w-full lg:w-2/3 h-72 bg-white p-6 rounded-3xl shadow-lg border border-gray-100">
                <h2 className="text-xl font-bold text-gray-800 mb-6">
                    Estadísticas de Envíos
                </h2>
                <div className="flex-1 w-full">
                    <ResponsiveContainer width="100%" height="100%">
                        <BarChart data={data}>
                            <XAxis
                                dataKey="month"
                                axisLine={false}
                                tickLine={false}
                                tick={{ fill: "#94a3b8", fontSize: 12 }}
                            />
                            <YAxis
                                axisLine={false}
                                tickLine={false}
                                tick={{ fill: "#94a3b8", fontSize: 12 }}
                            />
                            <Tooltip cursor={{ fill: "transparent" }} />
                            {/* Corregí el fill: NO usar clases de tailwind dentro de componentes Recharts */}
                            <Bar
                                dataKey="envios"
                                fill="#334155"
                                radius={[10, 10, 0, 0]}
                                barSize={40}
                            />
                        </BarChart>
                    </ResponsiveContainer>
                </div>
            </div>

            {/* TARJETA 2: CALENDARIO Y FLOTA */}
            <div className="flex flex-col w-full h-72 lg:w-1/3 bg-white p-6 rounded-3xl shadow-lg border border-gray-100">
                <h3 className="text-lg font-bold text-slate-800 mb-5">
                    Estado de la Flota
                </h3>

                <div className=" flex  mb-6">
                    {obtenerSemana(fechaSeleccionada).map((dia, index) => (
                        <div onClick={() => setFechaSeleccionada(dia)} className={esDiaSeleccionado(dia) ? "bg-gray-500 text-white flex flex-col items-center gap-4 justify-center p-2 cursor-pointer rounded-2xl shadow-lg shadow-gray-200" : 
                        "cursor-pointer hover:bg-gray-200 rounded-2xl text-gray-700 flex flex-col items-center gap-4 justify-center p-2"} key={index}>
                            <span>{dia.toLocaleDateString('es-ES', { weekday: 'short'})}</span>
                            <span>{dia.getDate()}</span>
                        </div>
                    ))}
                </div>
                {/* PANEL DE ESTADO DE REPARTIDORES (El que pediste) */}
                <div className="bg-slate-50 p-3 rounded-3xl border border-slate-100 flex items-center justify-between">
                    <div>
                        <h4 className="text-sm font-bold text-slate-800">
                            Estado de repartidores
                        </h4>
                        <div className="flex items-center gap-1 mt-1">
                            <span className="text-emerald-500 text-xs font-bold">↑ 0.9%</span>
                            <span className="text-slate-400 text-[10px]">vs. mes pasado</span>
                        </div>
                    </div>

                    {/* Gráfico Circular de Saturación */}
                    <div className="relative w-16 h-16">
                        <ResponsiveContainer width="100%" height="100%">
                            <PieChart>
                                <Pie
                                    data={dataPie}
                                    innerRadius={20}
                                    outerRadius={28}
                                    paddingAngle={0}
                                    dataKey="value"
                                    startAngle={90}
                                    endAngle={-270}
                                >
                                    <Cell
                                        fill={porcentajeSaturacion > 80 ? "#ef4444" : "#334155"}
                                    />
                                    <Cell fill="#e2e8f0" />
                                </Pie>
                            </PieChart>
                        </ResponsiveContainer>
                        <div className="absolute inset-0 flex items-center justify-center">
                            <span className="text-[10px] font-bold text-slate-700">
                                {porcentajeSaturacion}%
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Statistics;

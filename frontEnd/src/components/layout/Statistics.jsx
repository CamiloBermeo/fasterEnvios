import React, { useState } from "react";
import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    ResponsiveContainer,
} from "recharts";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
// IMPORTANTE: El CSS personalizado que te pasé antes debe estar en tu index.css
// o importado aquí para que el calendario no se vea feo.

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
        "2026-03-25": { envios: 15, pendientes: 3 },
        "2026-03-26": { envios: 8, pendientes: 1 },
        "2026-03-27": { envios: 12, pendientes: 2 },
    };

    // Formateo de fecha para buscar en el objeto
    const fechaKey = fechaSeleccionada.toISOString().split("T")[0];
    const infoActual = datos[fechaKey] || { envios: 0, pendientes: 0 };
const porcentajeSaturacion = infoActual.envios > 10 ? 85 : 40; 
const dataPie = [
    { value: porcentajeSaturacion },
    { value: 100 - porcentajeSaturacion }
];

    return (
        <div className="flex flex-col lg:flex-row gap-6 mt-6 w-full items-start">
            {/* TARJETA 1: GRÁFICA */}
            <div className="flex flex-col w-full lg:w-2/3 h-[450px] bg-white p-6 rounded-3xl shadow-lg border border-gray-100">
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
            <div className="flex flex-col w-full lg:w-1/3 bg-white p-6 rounded-3xl shadow-lg border border-gray-100">
                <h3 className="text-lg font-bold text-slate-800 mb-5">
                    Estado de la Flota
                </h3>

                <div className="custom-calendar-semana mb-6">
                    <Calendar
                        onChange={setFechaSeleccionada}
                        value={fechaSeleccionada}
                        view="month"
                        showNavigation={true}
                        showNeighboringMonth={false}
                        className="border-none w-full"
                    />
                </div>
                {/* PANEL DE ESTADO DE REPARTIDORES (El que pediste) */}
            <div className="bg-slate-50 p-5 rounded-3xl border border-slate-100 flex items-center justify-between">
                <div>
                    <h4 className="text-sm font-bold text-slate-800">Estado de repartidores</h4>
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
                                <Cell fill={porcentajeSaturacion > 80 ? "#ef4444" : "#334155"} />
                                <Cell fill="#e2e8f0" />
                            </Pie>
                        </PieChart>
                    </ResponsiveContainer>
                    <div className="absolute inset-0 flex items-center justify-center">
                        <span className="text-[10px] font-bold text-slate-700">{porcentajeSaturacion}%</span>
                    </div>
                </div>
            </div>
            </div>
        </div>
    );
};

export default Statistics;

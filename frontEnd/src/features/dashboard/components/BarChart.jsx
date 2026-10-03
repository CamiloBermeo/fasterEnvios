import React from 'react';
import { BarChart as ReBarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

const data = [
  { name: 'Lun', envios: 42, entregados: 38 },
  { name: 'Mar', envios: 58, entregados: 52 },
  { name: 'Mie', envios: 65, entregados: 60 },
  { name: 'Jue', envios: 78, entregados: 75 },
  { name: 'Vie', envios: 90, entregados: 84 },
  { name: 'Sab', envios: 45, entregados: 40 },
  { name: 'Dom', envios: 20, entregados: 18 },
];

const BarChart = () => {
  return (
    <div className="w-full h-64 bg-white p-4 rounded-2xl shadow-sm border border-slate-100">
      <h3 className="text-sm font-bold text-slate-700 mb-3">Envíos vs Entregas Semanales</h3>
      <ResponsiveContainer width="100%" height="85%">
        <ReBarChart data={data}>
          <CartesianGrid strokeDasharray="3 3" vertical={false} stroke="#e2e8f0" />
          <XAxis dataKey="name" stroke="#94a3b8" fontSize={12} tickLine={false} />
          <YAxis stroke="#94a3b8" fontSize={12} tickLine={false} />
          <Tooltip 
            contentStyle={{ backgroundColor: '#0f172a', borderRadius: '8px', border: 'none', color: '#fff' }} 
            itemStyle={{ color: '#fff' }}
          />
          <Bar dataKey="envios" fill="#10b981" radius={[4, 4, 0, 0]} name="Envíos Solicitados" />
          <Bar dataKey="entregados" fill="#6366f1" radius={[4, 4, 0, 0]} name="Entregados a Tiempo" />
        </ReBarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default BarChart;
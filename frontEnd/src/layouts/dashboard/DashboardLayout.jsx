import React from "react";
import { Outlet } from "react-router-dom";
import Sidebar from "./components/Sidebar.jsx";
import HeaderDashboardAliado from "./components/HeaderDashboardAliado.jsx";

const DashboardLayout = () => {
  return (
    <div className="flex min-h-screen bg-slate-100 font-sans text-slate-800">
      <Sidebar />
      <div className="flex-1 flex flex-col min-w-0 md:ml-64">
        <header className="sticky top-0 z-40 bg-slate-100/90 backdrop-blur border-b border-slate-200 py-3 px-6">
          <HeaderDashboardAliado />
        </header>
        <main className="flex-1 p-6">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default DashboardLayout;

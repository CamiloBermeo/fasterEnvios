import React from "react";
import Sidebar from "../layout/Sidebar";
import HederDashboardAliado from "../layout/HederDashboardAliado";

const DashboardAliado = () => {
    return (
        <div className="flex min-h-screen bg-gray-50 h-full w-full">
 
            <Sidebar />

            <main className="flex-1 flex flex-col min-w-0"style={{ marginLeft: '16rem' }}>
                <div className="p-8 ">
                    <HederDashboardAliado />
                    <div className="mt-10 p-6 bg-white rounded-xl shadow-sm border border-gray-200">
                        <p className="text-gray-500">Contenido del panel activo...</p>
                    </div>
                </div>
            </main>
        </div>
    );
}
export default DashboardAliado;
import React from "react";
import Sidebar from "../layout/Sidebar";
import HederDashboardAliado from "../layout/HederDashboardAliado";
import Shipments from "../layout/Shipments";
import Metrics from "../layout/Metrics";
import Statistics from "../layout/Statistics";

const DashboardAliado = () => {
    return (
        <div className="flex min-h-screen  h-full w-full">
            <Sidebar />

            <main className="flex-1 flex  flex-col min-w-0"style={{ marginLeft: '16rem' }}>
                <div className="p-4 ">
                    <HederDashboardAliado />
                    <Shipments />
                    <Metrics />
                    <Statistics />
                </div>
            </main>
        </div>
    );
}
export default DashboardAliado;
import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

// Layouts
import ClientLayout from "../layouts/ClientLayout.jsx";
import DashboardLayout from "../layouts/dashboard/DashboardLayout.jsx";

// Public Pages
import HomePage from "../pages/public/HomePage.jsx";
import NosotrosPage from "../pages/public/NosotrosPage.jsx";
import ContactoPage from "../pages/public/ContactoPage.jsx";
import ServiciosPage from "../pages/public/ServiciosPage.jsx";
import CotizadorPage from "../pages/public/CotizadorPage.jsx";
import RastrearGuiaPage from "../pages/public/RastrearGuiaPage.jsx";

// Auth Pages
import LoginPage from "../features/auth/LoginPage.jsx";
import LoginAliadoPage from "../features/auth/LoginAliadoPage.jsx";
import RegisterPage from "../features/auth/RegisterPage.jsx";

// Protected Dashboard
import DashboardAliadoPage from "../features/dashboard/pages/DashboardAliadoPage.jsx";
import ProtectedRoute from "./ProtectedRoute.jsx";

const AppRoutes = () => {
  return (
    <Routes>
      {/* Client Facing Public Layout */}
      <Route element={<ClientLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/nosotros" element={<NosotrosPage />} />
        <Route path="/contacto" element={<ContactoPage />} />
        <Route path="/servicios" element={<ServiciosPage />} />
        <Route path="/cotizador" element={<CotizadorPage />} />
        <Route path="/rastrear" element={<RastrearGuiaPage />} />
      </Route>

      {/* Auth Pages */}
      <Route path="/login" element={<LoginPage />} />
      <Route path="/login-aliado" element={<LoginAliadoPage />} />
      <Route path="/register" element={<RegisterPage />} />

      {/* Dashboard Routes (Protected) */}
      <Route element={<ProtectedRoute />}>
        <Route element={<DashboardLayout />}>
          <Route path="/dashboard-aliado" element={<DashboardAliadoPage />} />
        </Route>
      </Route>

      {/* Catch-all Fallback */}
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
};

export default AppRoutes;

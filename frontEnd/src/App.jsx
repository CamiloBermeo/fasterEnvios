import React from 'react'
import "./index.css";
import Home from "./components/home/Home";
import Login from "./components/auth/Login";
import LoginAliado from "./components/auth/LoginAliado";
import Register from "./components/auth/Register";
import DashboardAliado from "./components/dashboard/DashboardAliado";
import RutaProtegida from "./components/auth/ProtectedRoute";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/authProvider.jsx";

function App() {
  return (
  <Router>
    <AuthProvider>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/home" element={<Home />} />
        <Route exact path="/login" element={<Login />} />
        <Route exact path="/login-aliado" element={<LoginAliado />} />
        <Route exact path="/register" element={<Register />} />
        <Route element={<RutaProtegida />}>
          <Route  exact path="/dashboard-aliado" element={<DashboardAliado />} />
        </Route>
      </Routes>
    </AuthProvider>
    </Router>
  )
}

export default App

import React from 'react'
import "./index.css";
import Home from "./components/home/Home";
import Login from "./components/auth/Login";
import LoginAliado from "./components/auth/LoginAliado";
import Register from "./components/auth/Register";
import DashboardAliado from "./components/dashboard/DashboardAliado";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
  <Router>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/home" element={<Home />} />
        <Route exact path="/login" element={<Login />} />
        <Route exact path="/login-aliado" element={<LoginAliado />} />
        <Route exact path="/register" element={<Register />} />
        <Route exact path="/dashboard-aliado" element={<DashboardAliado />} />
      </Routes>
    </Router>
  )
}

export default App

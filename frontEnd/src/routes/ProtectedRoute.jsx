import {Navigate, Outlet} from "react-router-dom";
import useAuth from "../hooks/useAuth";

const RutaProtegida = ({rolesPermitidos}) => {

const{auth, cargando} = useAuth();

if (cargando) return <div>Cargando...</div>;
console.log(auth);
if(!auth?.id) return <Navigate to="/login-aliado" />;

if (rolesPermitidos && !rolesPermitidos.includes(auth.rol)) {
        return <Navigate to="/login-aliado" />;
    }
    return <Outlet />;

}
export default RutaProtegida;
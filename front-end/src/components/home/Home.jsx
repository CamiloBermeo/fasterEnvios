import React from "react";
import "./Home.css";
import Header from "./Header";
import Banner from "./Banner";

const Home = ({titulo}) => {
return(
<div className="contenedor-home">
    <Header titulo={titulo} />
    <Banner />
</div>
);
}
export default Home;
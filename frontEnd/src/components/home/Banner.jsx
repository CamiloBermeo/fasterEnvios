import React from "react";
import "./Banner.css";
import BannerImage1 from "../../assets/banner_homee_faster_envios.png";
import IconoIr from "../../assets/icono_ir.svg";

const Banner = () => {
    return (
        <div id="carouselExample" className="carousel slide contenedor-banner">
            <div className="carousel-inner">
                <div className="carousel-item active">
                    <img className="d-block w-100 img-banner img-fluid" src={BannerImage1} alt="banner 1" />
                </div>
            </div>
            <button type="button " className="boton-banner">Vamos allá... <img className="icono-ir" src={IconoIr} alt="Icono Ir" /></button>
        </div>
    )
}
export default Banner;
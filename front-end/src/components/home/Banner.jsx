import React from "react";
import "./Banner.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import BannerImage1 from "../../assets/banner_homee_faster_envios.png";
import IconoIr from "../../assets/icono_ir.svg";

const Banner = () => {
    return (
        <div id="carouselExample" className="carousel slide contenedor-banner">
            <div className="carousel-inner">
                <div claclasNames="carousel-item active">
                    <img className="d-block w-100 img-banner img-fluid" src={BannerImage1} alt="banner 1" />
                </div>
                <div className="carousel-item">
                    <img className="d-block w-100 img-banner img-fluid" src={BannerImage1} alt="banner 2" />
                </div>
                <div className="carousel-item">
                    <img className="d-block w-100 img-banner img-fluid" src={BannerImage1} alt="banner 3" />
                </div>
            </div>
            <button type="button " className="boton-banner">Vamos allá... <img className="icono-ir" src={IconoIr} alt="Icono Ir" /></button>
            <button className="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Previous</span>
            </button>
            <button className="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                <span className="carousel-control-next-icon" aria-hidden="true"></span>
                <span className="visually-hidden">Next</span>
            </button>
        </div>
    )
}
export default Banner;
import React from "react";

const Payment = () =>{


    return(
        <div>
            <div className="input-wrapper relative">
                        <label
                            htmlFor="metodoPago"
                            className="block text-slate-500 text-sm font-medium mb-2 pointer-events-none"
                        >
                            Método de Pago
                        </label>
                        <select
                            id="metodoPago"
                            name="metodoPago"
                            value={metodoPago}
                            onChange={onChange}
                            required
                            className="bg-white border-2 border-slate-200 rounded-lg px-4 pb-2 pt-3 text-slate-800 text-base transition-all duration-200 w-full outline-none focus:border-indigo-500 peer appearance-none"
                        >
                            <option value="" disabled hidden>Selecciona un método</option>
                            <option value="CONTRAENTREGA">Contraentrega</option>
                            <option value="NEQUI">Nequi</option>
                            <option value="BANCOLOMBIA">Bancolombia</option>
                            <option value="EFECTIVO">Efectivo</option>
                        </select>

                        {/* Icono de flecha opcional para indicar que es un desplegable */}
                        <div className="absolute right-4 top-12 pointer-events-none text-slate-400">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                                <path fillRule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clipRule="evenodd" />
                            </svg>
                        </div>
                    </div>
        </div>
    )

};
export default Payment
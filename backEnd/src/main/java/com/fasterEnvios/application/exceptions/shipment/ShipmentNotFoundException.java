package com.fasterEnvios.application.exceptions.shipment;

public class ShipmentNotFoundException extends RuntimeException {
    public ShipmentNotFoundException(String trackingNumber) {
        super("El envío con numero de guía: " + trackingNumber +" no se encuentra en base de datos");
    }
}

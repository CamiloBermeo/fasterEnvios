package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.PackageEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Shipment {
    private Long id;
    private PaymentTransaction paymentTransaction;
    private List<Package> packages;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime estimatedDeliveryDate;
    private String cityOrigin;
    private String cityDestination;
    private double distance;
    private StateEnum state;

    public Shipment() {
    }

    private Shipment(Long id, PaymentTransaction paymentTransaction, List<Package> packages, LocalDateTime createdAt, LocalDateTime estimatedDeliveryDate, double distance,String cityOrigin, String cityDestination, StateEnum state) {
        this.id = id;
        this.paymentTransaction = paymentTransaction;
        this.packages = packages;
        this.createdAt = createdAt;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.cityOrigin = cityOrigin;
        this.cityDestination = cityDestination;
        this.distance = distance;
        this.state = state;
    }

    public static ShipmentBuilder builder() {
        return new ShipmentBuilder();
    }

    private Shipment(ShipmentBuilder builder) {
        this.id = builder.id;
        this.paymentTransaction = builder.paymentTransaction;
        this.packages = builder.packages;
        this.createdAt = builder.createdAt;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.cityOrigin = builder.cityOrigin;
        this.cityDestination = builder.cityDestination;
        this.distance = builder.distance;
        this.state = builder.state;
    }

    public ShipmentBuilder toBuilder() {
        return new ShipmentBuilder()
                .withId(this.id)
                .withPaymentTransaction(this.paymentTransaction)
                .withPackages(this.packages)
                .withCreatedAt(this.createdAt)
                .withEstimatedDeliveryDate(this.estimatedDeliveryDate)
                .withCityOrigin(this.cityOrigin)
                .withCityDestination(this.cityDestination)
                .withDistance(this.distance)
                .withState(this.state);
    }

    public static class ShipmentBuilder {
        private Long id;
        private PaymentTransaction paymentTransaction;
        private List<Package> packages;
        private LocalDateTime createdAt;
        private LocalDateTime estimatedDeliveryDate;
        private String cityOrigin;
        private String cityDestination;
        private double distance;
        private StateEnum state;

        public ShipmentBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ShipmentBuilder withPaymentTransaction(PaymentTransaction paymentTransaction) {
            this.paymentTransaction = paymentTransaction;
            return this;
        }
        public ShipmentBuilder withDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public ShipmentBuilder withPackages(List<Package> packages) {
            this.packages = packages;
            return this;
        }

        public ShipmentBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ShipmentBuilder withEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
            this.estimatedDeliveryDate = estimatedDeliveryDate;
            return this;
        }

        public ShipmentBuilder withCityOrigin(String cityOrigin) {
            this.cityOrigin = cityOrigin;
            return this;
        }

        public ShipmentBuilder withCityDestination(String cityDestination) {
            this.cityDestination = cityDestination;
            return this;
        }

        public ShipmentBuilder withState(StateEnum state) {
            this.state = state;
            return this;
        }

        public Shipment build() {
            return new Shipment(this);
        }
    }

    public Long getId() {
        return id;
    }

    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public double getDistance() {
        return distance;
    }

    public String getCityOrigin() {
        return cityOrigin;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public StateEnum getState() {
        return state;
    }
}

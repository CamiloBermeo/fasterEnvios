package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.PackageEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Shipment {
    private Long id;
    private PaymentTransactionEntity paymentTransaction;
    private List<PackageEntity> packages;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDeliveryDate;
    private String cityOrigin;
    private String cityDestination;
    private String state;

    public Shipment() {
    }

    private Shipment(Long id, PaymentTransactionEntity paymentTransaction, List<PackageEntity> packages, LocalDateTime createdAt, LocalDateTime estimatedDeliveryDate, String cityOrigin, String cityDestination, String state) {
        this.id = id;
        this.paymentTransaction = paymentTransaction;
        this.packages = packages;
        this.createdAt = createdAt;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.cityOrigin = cityOrigin;
        this.cityDestination = cityDestination;
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
                .withState(this.state);
    }

    public static class ShipmentBuilder {
        private Long id;
        private PaymentTransactionEntity paymentTransaction;
        private List<PackageEntity> packages;
        private LocalDateTime createdAt;
        private LocalDateTime estimatedDeliveryDate;
        private String cityOrigin;
        private String cityDestination;
        private String state;

        public ShipmentBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ShipmentBuilder withPaymentTransaction(PaymentTransactionEntity paymentTransaction) {
            this.paymentTransaction = paymentTransaction;
            return this;
        }

        public ShipmentBuilder withPackages(List<PackageEntity> packages) {
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

        public ShipmentBuilder withState(String state) {
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

    public PaymentTransactionEntity getPaymentTransaction() {
        return paymentTransaction;
    }

    public List<PackageEntity> getPackages() {
        return packages;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public String getCityOrigin() {
        return cityOrigin;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public String getState() {
        return state;
    }
}

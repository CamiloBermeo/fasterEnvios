package com.fasterEnvios.domain.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Shipment {
    private Long id;
    private PaymentTransaction paymentTransaction;
    private List<PackageModel> packageModels;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime estimatedDeliveryDate;
    private String cityOrigin;
    private String cityDestination;
    private BigDecimal totalAmount;
    private double distance;
    private StateEnum state;

    public Shipment() {
    }

    private Shipment(Long id, PaymentTransaction paymentTransaction, List<PackageModel> packageModels, LocalDateTime createdAt, BigDecimal totalAmount,LocalDateTime estimatedDeliveryDate, double distance, String cityOrigin, String cityDestination, StateEnum state) {
        this.id = id;
        this.paymentTransaction = paymentTransaction;
        this.packageModels = packageModels;
        this.createdAt = createdAt;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.cityOrigin = cityOrigin;
        this.cityDestination = cityDestination;
        this.totalAmount = totalAmount;
        this.distance = distance;
        this.state = state;
    }

    public static ShipmentBuilder builder() {
        return new ShipmentBuilder();
    }

    private Shipment(ShipmentBuilder builder) {
        this.id = builder.id;
        this.paymentTransaction = builder.paymentTransaction;
        this.packageModels = builder.packageModels;
        this.createdAt = builder.createdAt;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.cityOrigin = builder.cityOrigin;
        this.cityDestination = builder.cityDestination;
        this.totalAmount = builder.totalAmount;
        this.distance = builder.distance;
        this.state = builder.state;
    }

    public ShipmentBuilder toBuilder() {
        return new ShipmentBuilder()
                .withId(this.id)
                .withPaymentTransaction(this.paymentTransaction)
                .withPackages(this.packageModels)
                .withCreatedAt(this.createdAt)
                .withEstimatedDeliveryDate(this.estimatedDeliveryDate)
                .withCityOrigin(this.cityOrigin)
                .withCityDestination(this.cityDestination)
                .withTotalAmount(this.totalAmount)
                .withDistance(this.distance)
                .withState(this.state);
    }

    public static class ShipmentBuilder {
        private Long id;
        private PaymentTransaction paymentTransaction;
        private List<PackageModel> packageModels;
        private LocalDateTime createdAt;
        private LocalDateTime estimatedDeliveryDate;
        private String cityOrigin;
        private String cityDestination;
        private BigDecimal totalAmount;
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

        public ShipmentBuilder withPackages(List<PackageModel> packageModels) {
            this.packageModels = packageModels;
            return this;
        }
        public ShipmentBuilder withTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<PackageModel> getPackageModels() {
        return packageModels;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
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

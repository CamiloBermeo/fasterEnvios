package com.fasterEnvios.domain.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Shipment {
    private Long id;
    private Person sender;
    private Person addressee;
    private PaymentTransaction paymentTransaction;
    private PackageModel packageModels;
    private LocalDateTime createdAt;
    private LocalDateTime estimatedDeliveryDate;
    private BigDecimal totalAmount;
    private double distance;
    private StateEnum state;

    public Shipment() {
    }

    private Shipment(Long id, Person sender, Person addressee,PaymentTransaction paymentTransaction, PackageModel packageModels, LocalDateTime createdAt, BigDecimal totalAmount,LocalDateTime estimatedDeliveryDate, double distance, StateEnum state) {
        this.id = id;
        this.sender = sender;
        this.addressee = addressee;
        this.paymentTransaction = paymentTransaction;
        this.packageModels = packageModels;
        this.createdAt = createdAt;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.totalAmount = totalAmount;
        this.distance = distance;
        this.state = state;
    }

    public static ShipmentBuilder builder() {
        return new ShipmentBuilder();
    }

    private Shipment(ShipmentBuilder builder) {
        this.id = builder.id;
        this.sender = builder.sender;
        this.addressee= builder.addressee;
        this.paymentTransaction = builder.paymentTransaction;
        this.packageModels = builder.packageModels;
        this.createdAt = builder.createdAt;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.totalAmount = builder.totalAmount;
        this.distance = builder.distance;
        this.state = builder.state;
    }

    public ShipmentBuilder toBuilder() {
        return new ShipmentBuilder()
                .withId(this.id)
                .withSender(this.sender)
                .withAddressee(this.addressee)
                .withPaymentTransaction(this.paymentTransaction)
                .withPackages(this.packageModels)
                .withCreatedAt(this.createdAt)
                .withEstimatedDeliveryDate(this.estimatedDeliveryDate)
                .withTotalAmount(this.totalAmount)
                .withDistance(this.distance)
                .withState(this.state);
    }

    public static class ShipmentBuilder {
        private Long id;
        private Person sender;
        private Person addressee;
        private PaymentTransaction paymentTransaction;
        private PackageModel packageModels;
        private LocalDateTime createdAt;
        private LocalDateTime estimatedDeliveryDate;
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

        public ShipmentBuilder withPackages(PackageModel packageModels) {
            this.packageModels = packageModels;
            return this;
        }
        public ShipmentBuilder withTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }
        public ShipmentBuilder withSender(Person sender) {
            this.sender = sender;
            return this;
        }
        public ShipmentBuilder withAddressee(Person addressee) {
            this.addressee = addressee;
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

    public PackageModel getPackageModels() {
        return packageModels;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public Person getSender() {
        return sender;
    }

    public Person getAddressee() {
        return addressee;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public double getDistance() {
        return distance;
    }

    public StateEnum getState() {
        return state;
    }
}

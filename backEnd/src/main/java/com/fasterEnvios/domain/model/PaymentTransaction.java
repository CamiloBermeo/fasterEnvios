package com.fasterEnvios.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentTransaction {
    private Long id;
    private Shipment shipments;
    private Person payingPerson;
    private PaymentMethod paymentMethods;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentStatusEnum paymentStatus;
    private String observations;

    public PaymentTransaction() {
    }

    private PaymentTransaction(Long id, Shipment shipments, Person payingPerson,PaymentMethod paymentMethods, BigDecimal amount, LocalDateTime paymentDate, PaymentStatusEnum paymentStatus, String observations) {
        this.id = id;
        this.shipments = shipments;
        this.payingPerson = payingPerson;
        this.paymentMethods = paymentMethods;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.observations = observations;
    }

    public static PaymentTransactionBuilder builder() {
        return new PaymentTransactionBuilder();
    }

    private PaymentTransaction(PaymentTransactionBuilder builder) {
        this.id = builder.id;
        this.shipments = builder.shipments;
        this.payingPerson = builder.payingPerson;
        this.paymentMethods = builder.paymentMethods;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentStatus = builder.paymentStatus;
        this.observations = builder.observations;
    }

    public PaymentTransactionBuilder toBuilder() {
        return new PaymentTransactionBuilder()
                .withId(this.id)
                .withShipments(this.shipments)
                .withPayingPerson(this.payingPerson)
                .withPaymentMethods(this.paymentMethods)
                .withAmount(this.amount)
                .withPaymentDate(this.paymentDate)
                .withPaymentStatus(this.paymentStatus)
                .withObservation(this.observations);
    }

    public static class PaymentTransactionBuilder {
        private Long id;
        private Shipment shipments;
        private Person payingPerson;
        private PaymentMethod paymentMethods;
        private BigDecimal amount;
        private LocalDateTime paymentDate;
        private PaymentStatusEnum paymentStatus;
        private String observations;

        public PaymentTransactionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PaymentTransactionBuilder withShipments(Shipment shipments) {
            this.shipments = shipments;
            return this;
        }
        public PaymentTransactionBuilder withPayingPerson(Person payingPerson) {
            this.payingPerson = payingPerson;
            return this;
        }

        public PaymentTransactionBuilder withPaymentMethods(PaymentMethod paymentMethods) {
            this.paymentMethods = paymentMethods;
            return this;
        }

        public PaymentTransactionBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public PaymentTransactionBuilder withPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public PaymentTransactionBuilder withPaymentStatus(PaymentStatusEnum paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }
        public PaymentTransactionBuilder withObservation(String observations) {
            this.observations = observations;
            return this;
        }

        public PaymentTransaction build() {
            return new PaymentTransaction(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Shipment getShipments() {
        return shipments;
    }

    public Person getPayingPerson() {
        return payingPerson;
    }

    public String getObservations() {
        return observations;
    }

    public PaymentMethod getPaymentMethods() {
        return paymentMethods;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }
}

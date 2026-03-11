package com.fasterEnvios.domain.model;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentTransaction {
    private Long id;
    private List<Shipment> shipments;
    private List<PaymentMethod> paymentMethods;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentStatusEnum paymentStatus;

    public PaymentTransaction() {
    }

    private PaymentTransaction(Long id, List<Shipment> shipments, List<PaymentMethod> paymentMethods, BigDecimal amount, LocalDateTime paymentDate, PaymentStatusEnum paymentStatus) {
        this.id = id;
        this.shipments = shipments;
        this.paymentMethods = paymentMethods;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public static PaymentTransactionBuilder builder() {
        return new PaymentTransactionBuilder();
    }

    private PaymentTransaction(PaymentTransactionBuilder builder) {
        this.id = builder.id;
        this.shipments = builder.shipments;
        this.paymentMethods = builder.paymentMethods;
        this.amount = builder.amount;
        this.paymentDate = builder.paymentDate;
        this.paymentStatus = builder.paymentStatus;
    }

    public PaymentTransactionBuilder toBuilder() {
        return new PaymentTransactionBuilder()
                .withId(this.id)
                .withShipments(this.shipments)
                .withPaymentMethods(this.paymentMethods)
                .withAmount(this.amount)
                .withPaymentDate(this.paymentDate)
                .withPaymentStatus(this.paymentStatus);
    }

    public static class PaymentTransactionBuilder {
        private Long id;
        private List<Shipment> shipments;
        private List<PaymentMethod> paymentMethods;
        private BigDecimal amount;
        private LocalDateTime paymentDate;
        private PaymentStatusEnum paymentStatus;

        public PaymentTransactionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PaymentTransactionBuilder withShipments(List<Shipment> shipments) {
            this.shipments = shipments;
            return this;
        }

        public PaymentTransactionBuilder withPaymentMethods(List<PaymentMethod> paymentMethods) {
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

        public PaymentTransaction build() {
            return new PaymentTransaction(this);
        }
    }

    public Long getId() {
        return id;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public List<PaymentMethod> getPaymentMethods() {
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

package com.fasterEnvios.domain.model;

public class PaymentMethod {
    private Long id;
    private String methodName;
    private boolean status;

    public PaymentMethod() {
    }

    private PaymentMethod(Long id, String methodName, boolean status) {
        this.id = id;
        this.methodName = methodName;
        this.status = status;
    }

    public static PaymentMethodBuilder builder() {
        return new PaymentMethodBuilder();
    }

    private PaymentMethod(PaymentMethodBuilder builder) {
        this.id = builder.id;
        this.methodName = builder.methodName;
        this.status = builder.status;
    }

    public PaymentMethodBuilder toBuilder() {
        return new PaymentMethodBuilder()
                .withId(this.id)
                .withMethodName(this.methodName)
                .withStatus(this.status);
    }

    public static class PaymentMethodBuilder {
        private Long id;
        private String methodName;
        private boolean status;

        public PaymentMethodBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PaymentMethodBuilder withMethodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public PaymentMethodBuilder withStatus(boolean status) {
            this.status = status;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean isStatus() {
        return status;
    }
}

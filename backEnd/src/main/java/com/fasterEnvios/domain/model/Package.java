package com.fasterEnvios.domain.model;

public class Package {
    private Long id;
    private double weightKg;
    private double dimensions;
    private float declaredValue;
    private String description;

    public Package() {
    }

    private Package(Long id, double weightKg, double dimensions, float declaredValue, String description) {
        this.id = id;
        this.weightKg = weightKg;
        this.dimensions = dimensions;
        this.declaredValue = declaredValue;
        this.description = description;
    }

    public static PackageBuilder builder() {
        return new PackageBuilder();
    }

    private Package(PackageBuilder builder) {
        this.id = builder.id;
        this.weightKg = builder.weightKg;
        this.dimensions = builder.dimensions;
        this.declaredValue = builder.declaredValue;
        this.description = builder.description;
    }

    public PackageBuilder toBuilder() {
        return new PackageBuilder()
                .withId(this.id)
                .withWeightKg(this.weightKg)
                .withDimensions(this.dimensions)
                .withDeclaredValue(this.declaredValue)
                .withDescription(this.description);
    }

    public static class PackageBuilder {
        private Long id;
        private double weightKg;
        private double dimensions;
        private float declaredValue;
        private String description;

        public PackageBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PackageBuilder withWeightKg(double weightKg) {
            this.weightKg = weightKg;
            return this;
        }

        public PackageBuilder withDimensions(double dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public PackageBuilder withDeclaredValue(float declaredValue) {
            this.declaredValue = declaredValue;
            return this;
        }

        public PackageBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Package build() {
            return new Package(this);
        }
    }

    public Long getId() {
        return id;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public double getDimensions() {
        return dimensions;
    }

    public float getDeclaredValue() {
        return declaredValue;
    }

    public String getDescription() {
        return description;
    }
}

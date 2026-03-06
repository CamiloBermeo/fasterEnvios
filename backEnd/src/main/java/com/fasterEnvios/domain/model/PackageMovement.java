package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.EmployeeEntity;
import com.fasterEnvios.infrastructure.entity.OfficeEntity;
import com.fasterEnvios.infrastructure.entity.PackageEntity;

import java.time.LocalDateTime;

public class PackageMovement {
    private Long id;
    private PackageEntity packageEntity;
    private OfficeEntity officeEntity;
    private EmployeeEntity employeeEntity;
    private LocalDateTime movementDateTime;
    private String movementType;

    public PackageMovement() {
    }

    private PackageMovement(Long id, PackageEntity packageEntity, OfficeEntity officeEntity, EmployeeEntity employeeEntity, LocalDateTime movementDateTime, String movementType) {
        this.id = id;
        this.packageEntity = packageEntity;
        this.officeEntity = officeEntity;
        this.employeeEntity = employeeEntity;
        this.movementDateTime = movementDateTime;
        this.movementType = movementType;
    }

    public static PackageMovementBuilder builder() {
        return new PackageMovementBuilder();
    }

    private PackageMovement(PackageMovementBuilder builder) {
        this.id = builder.id;
        this.packageEntity = builder.packageEntity;
        this.officeEntity = builder.officeEntity;
        this.employeeEntity = builder.employeeEntity;
        this.movementDateTime = builder.movementDateTime;
        this.movementType = builder.movementType;
    }

    public PackageMovementBuilder toBuilder() {
        return new PackageMovementBuilder()
                .withId(this.id)
                .withPackageEntity(this.packageEntity)
                .withOfficeEntity(this.officeEntity)
                .withEmployeeEntity(this.employeeEntity)
                .withMovementDateTime(this.movementDateTime)
                .withMovementType(this.movementType);
    }

    public static class PackageMovementBuilder {
        private Long id;
        private PackageEntity packageEntity;
        private OfficeEntity officeEntity;
        private EmployeeEntity employeeEntity;
        private LocalDateTime movementDateTime;
        private String movementType;

        public PackageMovementBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PackageMovementBuilder withPackageEntity(PackageEntity packageEntity) {
            this.packageEntity = packageEntity;
            return this;
        }

        public PackageMovementBuilder withOfficeEntity(OfficeEntity officeEntity) {
            this.officeEntity = officeEntity;
            return this;
        }

        public PackageMovementBuilder withEmployeeEntity(EmployeeEntity employeeEntity) {
            this.employeeEntity = employeeEntity;
            return this;
        }

        public PackageMovementBuilder withMovementDateTime(LocalDateTime movementDateTime) {
            this.movementDateTime = movementDateTime;
            return this;
        }

        public PackageMovementBuilder withMovementType(String movementType) {
            this.movementType = movementType;
            return this;
        }

        public PackageMovement build() {
            return new PackageMovement(this);
        }
    }

    public Long getId() {
        return id;
    }

    public PackageEntity getPackageEntity() {
        return packageEntity;
    }

    public OfficeEntity getOfficeEntity() {
        return officeEntity;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public LocalDateTime getMovementDateTime() {
        return movementDateTime;
    }

    public String getMovementType() {
        return movementType;
    }
}

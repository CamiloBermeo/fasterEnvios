package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.OfficeEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;

public class Employee {
    private Long id;
    private UserEntity user;
    private OfficeEntity office;

    public Employee() {
    }

    private Employee(Long id, UserEntity user, OfficeEntity office) {
        this.id = id;
        this.user = user;
        this.office = office;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }
    private Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.office = builder.office;
    }
    
 public EmployeeBuilder toBuilder() {
        return new EmployeeBuilder()
                .withId(this.id)
                .withUser(this.user)
                .withOffice(this.office);
    }
    public static class EmployeeBuilder {
        private Long id;
        private UserEntity user;
        private OfficeEntity office;

        public EmployeeBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public EmployeeBuilder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public EmployeeBuilder withOffice(OfficeEntity office) {
            this.office = office;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }

    }
       public Long getId() {
        return this.id;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public OfficeEntity getOffice() {
        return this.office;
    }

}

package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.RoleEntity;

import java.util.List;

public class UserModel {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private List<RoleEntity> roles;

    public UserModel() {
    }

    private UserModel(Long id, String name, String lastName, String email, String passwordHash, String phoneNumber, String address, List<RoleEntity> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roles = roles;
    }

    public static UserModelBuilder builder() {
        return new UserModelBuilder();
    }

    private UserModel(UserModelBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.passwordHash = builder.passwordHash;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.roles = builder.roles;
    }

    public UserModelBuilder toBuilder() {
        return new UserModelBuilder()
                .withId(this.id)
                .withName(this.name)
                .withLastName(this.lastName)
                .withEmail(this.email)
                .withPasswordHash(this.passwordHash)
                .withPhoneNumber(this.phoneNumber)
                .withAddress(this.address)
                .withRoles(this.roles);
    }

    public static class UserModelBuilder {
        private Long id;
        private String name;
        private String lastName;
        private String email;
        private String passwordHash;
        private String phoneNumber;
        private String address;
        private List<RoleEntity> roles;

        public UserModelBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserModelBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserModelBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserModelBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserModelBuilder withPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserModelBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserModelBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public UserModelBuilder withRoles(List<RoleEntity> roles) {
            this.roles = roles;
            return this;
        }

        public UserModel build() {
            return new UserModel(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }
}

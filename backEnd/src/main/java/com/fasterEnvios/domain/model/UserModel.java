package com.fasterEnvios.domain.model;



public class UserModel {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String identityDocument;
    private Role role;

    public UserModel() {
    }

    private UserModel(Long id, String name, String lastName, String email, String passwordHash, String phoneNumber, String identityDocument, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.identityDocument = identityDocument;
        this.role = role;
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
        this.identityDocument = builder.identityDocument;
        this.role = builder.role;
    }

    public UserModelBuilder toBuilder() {
        return new UserModelBuilder()
                .withId(this.id)
                .withName(this.name)
                .withLastName(this.lastName)
                .withEmail(this.email)
                .withPasswordHash(this.passwordHash)
                .withPhoneNumber(this.phoneNumber)
                .withIdentityDocument(this.identityDocument)
                .withRole(this.role);
    }

    public static class UserModelBuilder {
        private Long id;
        private String name;
        private String lastName;
        private String email;
        private String passwordHash;
        private String phoneNumber;
        private String identityDocument;
        private Role role;

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

        public UserModelBuilder withIdentityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
            return this;
        }

        public UserModelBuilder withRole(Role role) {
            this.role = role;
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

    public String getIdentityDocument() {
        return identityDocument;
    }

    public Role getRole() {
        return role;
    }
}

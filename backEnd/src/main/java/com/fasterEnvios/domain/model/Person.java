package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;

public class Person {
    private Long id;
    private String name;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String address;
    private CityDescriptionEntity city;
    private UserEntity user;

    public Person() {
    }

    public Person(Long id, String name, String lastName, String identityDocument, String phoneNumber, String address, CityDescriptionEntity city, UserEntity user) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identityDocument = identityDocument;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.user = user;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    private Person(PersonBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.identityDocument = builder.identityDocument;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.city = builder.city;
        this.user = user;
    }

    private PersonBuilder toBuilder(){
        return new PersonBuilder()
                .withId(this.id)
                .withName(this.name)
                .withLastName(this.lastName)
                .withIdentityDocument(this.identityDocument)
                .withPhoneNumber(this.phoneNumber)
                .withAddress(this.address)
                .withCity(this.city)
                .withUser(this.user);
    }
    public static class PersonBuilder {
        private Long id;
        private String name;
        private String lastName;
        private String identityDocument;
        private String phoneNumber;
        private String address;
        private CityDescriptionEntity city;
        private UserEntity user;

        public PersonBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withIdentityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
            return this;
        }

        public PersonBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PersonBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder withCity(CityDescriptionEntity city) {
            this.city = city;
            return this;
        }

        public PersonBuilder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public Person build() {
            return new Person(this);
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

    public String getIdentityDocument() {
        return identityDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public CityDescriptionEntity getCity() {
        return city;
    }

    public UserEntity getUser() {
        return user;
    }
}

package com.fasterEnvios.domain.model;

public class Office {
    private Long id;
    private String officeName;
    private String address;
    private CityDescription city;
    private String phoneNumber;
    private Employee employee;

    public Office() {
    }

    private Office(Long id, String officeName, String address, CityDescription city, String phoneNumber, Employee employee) {
        this.id = id;
        this.officeName = officeName;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.employee = employee;
    }

    public static OfficeBuilder builder() {
        return new OfficeBuilder();
    }

    private Office(OfficeBuilder builder) {
        this.id = builder.id;
        this.officeName = builder.officeName;
        this.address = builder.address;
        this.city = builder.city;
        this.phoneNumber = builder.phoneNumber;
        this.employee = builder.employee;
    }

    public OfficeBuilder toBuilder() {
        return new OfficeBuilder()
                .withId(this.id)
                .withOfficeName(this.officeName)
                .withAddress(this.address)
                .withCity(this.city)
                .withPhoneNumber(this.phoneNumber)
                .withEmployee(this.employee);
    }

    public static class OfficeBuilder {
        private Long id;
        private String officeName;
        private String address;
        private CityDescription city;
        private String phoneNumber;
        private Employee employee;

        public OfficeBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public OfficeBuilder withOfficeName(String officeName) {
            this.officeName = officeName;
            return this;
        }

        public OfficeBuilder withAddress(String address) {
            this.address = address;
            return this;
        }
        public OfficeBuilder withEmployee(Employee employee) {
            this.employee = employee;
            return this;
        }
        public OfficeBuilder withCity(CityDescription city) {
            this.city = city;
            return this;
        }

        public OfficeBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Office build() {
            return new Office(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getAddress() {
        return address;
    }

    public CityDescription getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee getEmployee() {
        return employee;
    }
}

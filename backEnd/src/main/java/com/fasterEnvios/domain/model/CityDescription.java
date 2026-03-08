package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.OfficeEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;

import java.util.List;

public class CityDescription {
    Long id;
    String name;
    String country;
    double latitude;
    double longitude;

    public CityDescription() {}

    public CityDescription(Long id, String country, String name, double latitude, double longitude) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public static CityDescription.CityDescriptionBuilder builder() {
        return new CityDescription.CityDescriptionBuilder();
    }
    private CityDescription(CityDescription.CityDescriptionBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.country = builder.country;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
    }

    public CityDescription.CityDescriptionBuilder toBuilder() {
        return new CityDescription.CityDescriptionBuilder()
                .withId(this.id)
                .withName(this.name)
                .withCountry(this.country)
                .withLatitude(this.latitude)
                .withLongitude(this.longitude);

    }
    public static class CityDescriptionBuilder {
        private Long id;
        private String name;
        private String country;
        private double latitude;
        private double longitude;


        public CityDescription.CityDescriptionBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CityDescription.CityDescriptionBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CityDescription.CityDescriptionBuilder withCountry(String country) {
            this.country = country;
            return this;
        }
        public CityDescription.CityDescriptionBuilder withLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }
        public CityDescription.CityDescriptionBuilder withLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public CityDescription build() {
            return new CityDescription(this);
        }

    }

    public Long getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

}

package com.fasterEnvios.domain.model;

import com.fasterEnvios.infrastructure.entity.OfficeEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;

import java.util.List;

public class CityDescription {
    Long id;
    String name;
    String country;
    List<String> coordinates;

    public CityDescription() {}

    public CityDescription(Long id, List<String> coordinates, String country, String name) {
        this.id = id;
        this.coordinates = coordinates;
        this.country = country;
        this.name = name;
    }
    public static CityDescription.CityDescriptionBuilder builder() {
        return new CityDescription.CityDescriptionBuilder();
    }
    private CityDescription(CityDescription.CityDescriptionBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.country = builder.country;
        this.coordinates = builder.coordinates;
    }

    public CityDescription.CityDescriptionBuilder toBuilder() {
        return new CityDescription.CityDescriptionBuilder()
                .withId(this.id)
                .withName(this.name)
                .withCountry(this.country)
                .withCoordinates(this.coordinates);
    }
    public static class CityDescriptionBuilder {
        private Long id;
        private String name;
        private String country;
        private List<String> coordinates;

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
        public CityDescription.CityDescriptionBuilder withCoordinates(List<String> coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public CityDescription build() {
            return new CityDescription(this);
        }

    }

    public Long getId() {
        return id;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

}

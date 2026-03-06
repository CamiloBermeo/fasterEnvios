package com.fasterEnvios.domain.model;

public class Role {
    private Long id;
    private String roleName;
    private String description;

    public Role() {
    }

    private Role(Long id, String roleName, String description) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    private Role(RoleBuilder builder) {
        this.id = builder.id;
        this.roleName = builder.roleName;
        this.description = builder.description;
    }

    public RoleBuilder toBuilder() {
        return new RoleBuilder()
                .withId(this.id)
                .withRoleName(this.roleName)
                .withDescription(this.description);
    }

    public static class RoleBuilder {
        private Long id;
        private String roleName;
        private String description;

        public RoleBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder withRoleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        public RoleBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }
}

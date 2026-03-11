CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(50)  NOT NULL,
    last_name     VARCHAR(60)  NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(300) NOT NULL,
    phone_number  VARCHAR(20),
    address       VARCHAR(200),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP INDEX idx_user_email (email)
) ENGINE=InnoDB;

CREATE TABLE roles
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL UNIQUE,
    description VARCHAR(500) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE shipments
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    creates_at              TIMESTAMP,
    estimated_delivery_date TIMESTAMP,
    city_origin             VARCHAR(50)      NOT NULL,
    city_destination        VARCHAR(50)      NOT NULL,
    distance                DOUBLE PRECISION NOT NULL,
    state                   VARCHAR(40)      NOT NULL UNIQUE
)ENGINE=InnoDB;

CREATE TABLE payment_transactions
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount         DECIMAL(19, 3) NOT NULL,
    payment_date   TIMESTAMP,
    payment_status VARCHAR(40)    NOT NULL UNIQUE
)ENGINE=InnoDB;

CREATE TABLE shipment_payments
(
    shipment_id            BIGINT NOT NULL,
    payment_transaction_id BIGINT NOT NULL,
    CONSTRAINT fk_shipment FOREIGN KEY (shipment_id) REFERENCES shipments (id) ON DELETE CASCADE,
    CONSTRAINT fk_payment FOREIGN KEY (payment_transaction_id) REFERENCES payment_transactions (id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE packageModels
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    weight_kg DOUBLE NOT NULL,
    dimensions DOUBLE NOT NULL,
    declared_value DECIMAL(19, 3) NOT NULL,
    description    VARCHAR(500)   NOT NULL
)ENGINE=InnoDB;

CREATE TABLE shipment_packages
(
    shipment_id BIGINT NOT NULL,
    package_id  BIGINT NOT NULL,
    CONSTRAINT fk_shipment FOREIGN KEY (shipment_id) REFERENCES shipments (id) ON DELETE CASCADE,
    CONSTRAINT fk_package FOREIGN KEY (package_id) REFERENCES packageModels (id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE payments_methods
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    method_name VARCHAR(50) NOT NULL UNIQUE,
    status      BOOL        NOT NULL
)ENGINE=InnoDB;

CREATE TABLE transaction_payment_methods
(
    payment_transaction_id BIGINT         NOT NULL,
    payment_method_id      BIGINT         NOT NULL,
    amount                 DECIMAL(19, 3) NOT NULL,
    PRIMARY KEY (transaction_id, payment_method_id),
    CONSTRAINT fk_transaction FOREIGN KEY (payment_transaction_id) REFERENCES payment_transactions (id) ON DELETE CASCADE,
    CONSTRAINT fk_method FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id) ON DELETE RESTRICT
)ENGINE=InnoDB;


CREATE TABLE offices
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    office_name  VARCHAR(100) NOT NULL UNIQUE,
    address      VARCHAR(200) NOT NULL UNIQUE,
    phone_number VARCHAR(20)  NOT NULL UNIQUE
)ENGINE=InnoDB;

CREATE TABLE employees
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY
)ENGINE=InnoDB;

CREATE TABLE employee_office
(
    employee_id BIGINT NOT NULL,
    office_id   BIGINT NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE,
    CONSTRAINT fk_office FOREIGN KEY (office_id) REFERENCES offices (id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE employee_office
(
    employee_id BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE package_movement
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    movement_date_time TIMESTAMP,
    movement_type      VARCHAR(50)
)ENGINE=InnoDB;

CREATE TABLE package_movement_package
(
    package_movement_id BIGINT NOT NULL,
    package_id          BIGINT NOT NULL,
    CONSTRAINT fk_package_movement FOREIGN KEY (package_movement_id) REFERENCES package_movement (id) ON DELETE CASCADE,
    CONSTRAINT fk_package FOREIGN KEY (package_id) REFERENCES packageModels (id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE package_movement_offices
(
    package_movement_id BIGINT NOT NULL,
    office_id           BIGINT NOT NULL,
    CONSTRAINT fk_package_movement FOREIGN KEY (package_movement_id) REFERENCES package_movement (id) ON DELETE CASCADE,
    CONSTRAINT fk_office FOREIGN KEY (office_id) REFERENCES offices (id) ON DELETE CASCADE
)ENGINE=InnoDB;
CREATE TABLE package_movement_employees
(
    package_movement_id BIGINT NOT NULL,
    employee_id         BIGINT NOT NULL,
    CONSTRAINT fk_package_movement FOREIGN KEY (package_movement_id) REFERENCES package_movement (id) ON DELETE CASCADE,
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE cities
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100)     NOT NULL UNIQUE,
    country   VARCHAR(60)      NOT NULL UNIQUE,
    latitude  DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
)ENGINE=InnoDB;
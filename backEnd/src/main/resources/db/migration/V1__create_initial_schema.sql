-- 1. Tablas independientes o Catálogos base
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       description VARCHAR(255) NOT NULL
);

CREATE TABLE cities (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        country VARCHAR(100) NOT NULL,
                        latitude DOUBLE NOT NULL,
                        longitude DOUBLE NOT NULL
);

CREATE TABLE payment_methods (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 name VARCHAR(100) NOT NULL,
                                 status BOOLEAN NOT NULL
);

CREATE TABLE packages (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          weight_kg DOUBLE NOT NULL,
                          dimensions DOUBLE NOT NULL,
                          declared_value DECIMAL(19, 2) NOT NULL,
                          description VARCHAR(255) NOT NULL
);

-- 2. Tablas de Primer Nivel de Dependencia
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,
                       phone_number VARCHAR(20) NOT NULL,
                       identity_document VARCHAR(50) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL, -- Corregido: Sin restricción UNIQUE
                       city_id BIGINT,
                       role_id BIGINT,
                       CONSTRAINT fk_users_city FOREIGN KEY (city_id) REFERENCES cities(id),
                       CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE offices (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         office_name VARCHAR(100) NOT NULL,
                         address VARCHAR(100) NOT NULL,
                         phone_number VARCHAR(100) NOT NULL,
                         city_id BIGINT,
                         CONSTRAINT fk_offices_city FOREIGN KEY (city_id) REFERENCES cities(id)
);

-- 3. Tablas de Segundo Nivel de Dependencia
CREATE TABLE employees (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           user_id BIGINT NOT NULL UNIQUE, -- Unique por el @OneToOne
                           office_id BIGINT NOT NULL,
                           CONSTRAINT fk_employees_user FOREIGN KEY (user_id) REFERENCES users(id),
                           CONSTRAINT fk_employees_office FOREIGN KEY (office_id) REFERENCES offices(id)
);

CREATE TABLE people (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        identity_document VARCHAR(50) NOT NULL,
                        phone_number VARCHAR(20) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        city_id BIGINT,
                        user_id BIGINT UNIQUE, -- Unique por el @OneToOne
                        CONSTRAINT fk_people_city FOREIGN KEY (city_id) REFERENCES cities(id),
                        CONSTRAINT fk_people_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 4. El Core del Dominio (Transaccional)
CREATE TABLE shipments (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           tracking_number VARCHAR(100) NOT NULL UNIQUE, -- Añadido UNIQUE lógico
                           sender_id BIGINT NOT NULL,
                           addressee_id BIGINT NOT NULL,
                           package_id BIGINT UNIQUE, -- Unique por el @OneToOne
                           created_at DATETIME NOT NULL,
                           estimated_delivery_date DATETIME NOT NULL,
                           total_amount DECIMAL(19, 2) NOT NULL,
                           distance DOUBLE NOT NULL,
                           state VARCHAR(50) NOT NULL,
                           CONSTRAINT fk_shipments_sender FOREIGN KEY (sender_id) REFERENCES people(id),
                           CONSTRAINT fk_shipments_addressee FOREIGN KEY (addressee_id) REFERENCES people(id),
                           CONSTRAINT fk_shipments_package FOREIGN KEY (package_id) REFERENCES packages(id)
);

-- 5. Tablas de Último Nivel de Dependencia
CREATE TABLE payment_transactions (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      id_transaction VARCHAR(100) NOT NULL UNIQUE,
                                      shipment_id BIGINT NOT NULL UNIQUE, -- Unique por el @OneToOne
                                      person_id BIGINT NOT NULL, -- Corregido: Sin UNIQUE porque debe ser ManyToOne
                                      payment_method_id BIGINT,
                                      amount DECIMAL(19, 2) NOT NULL,
                                      payment_date DATETIME NOT NULL,
                                      payment_status VARCHAR(50) NOT NULL,
                                      observations VARCHAR(255),
                                      CONSTRAINT fk_transactions_shipment FOREIGN KEY (shipment_id) REFERENCES shipments(id),
                                      CONSTRAINT fk_transactions_person FOREIGN KEY (person_id) REFERENCES people(id),
                                      CONSTRAINT fk_transactions_method FOREIGN KEY (payment_method_id) REFERENCES payment_methods(id)
);

CREATE TABLE package_movements (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   package_id BIGINT NOT NULL,
                                   office_id BIGINT NOT NULL,
                                   employee_id BIGINT NOT NULL,
                                   movement_date_time DATETIME NOT NULL,
                                   movement_type VARCHAR(50) NOT NULL,
                                   CONSTRAINT fk_movements_package FOREIGN KEY (package_id) REFERENCES packages(id),
                                   CONSTRAINT fk_movements_office FOREIGN KEY (office_id) REFERENCES offices(id),
                                   CONSTRAINT fk_movements_employee FOREIGN KEY (employee_id) REFERENCES employees(id)
);
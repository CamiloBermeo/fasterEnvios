package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "package_movements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class PackageMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private PackageEntity packageEntity;
    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private OfficeEntity officeEntity;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employeeEntity;
    @Column(nullable = false)
    private LocalDateTime movementDateTime;
    @Column(nullable = false)
    private String movementType;

}

package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "office_id",  nullable = false)
    private OfficeEntity office;

}

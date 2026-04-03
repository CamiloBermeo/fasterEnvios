package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "offices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class OfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String officeName;
    @Column(nullable = false, length = 100)
    private String address;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityDescriptionEntity city;
    @Column(nullable = false, length = 100)
    private String phoneNumber;
    @OneToMany(mappedBy = "office",cascade = CascadeType.ALL)
    private List<EmployeeEntity> employees;

}

package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "people")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;
    private String address;
    @ManyToOne
    @JoinColumn(name = "city_sender_id")
    private CityDescriptionEntity citySender;//origen
    @ManyToOne
    @JoinColumn(name = "city_addressee_id")
    private CityDescriptionEntity cityAddressee;//destino


}

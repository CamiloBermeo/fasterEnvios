package com.fasterEnvios.infrastructure.entity;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.model.PaymentStatusEnum;
import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.domain.model.Shipment;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "payment_transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class PaymentTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String idTransaction;
    @OneToOne()
    @JoinColumn(name = "shipment_id", nullable = false)
    private ShipmentEntity shipment;
    @OneToOne()
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity payingPerson;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodEntity paymentMethod;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDateTime paymentDate;
    @Column(nullable = false)
    private String paymentStatus;
    @Column
    private String observations;
}

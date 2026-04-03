package com.fasterEnvios.infrastructure.entity;

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
    @OneToOne(mappedBy = "paymentTransaction")
    private ShipmentEntity shipment;
    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodEntity paymentMethod;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDateTime paymentDate;
    @Column(nullable = false)
    private String paymentStatus;
}

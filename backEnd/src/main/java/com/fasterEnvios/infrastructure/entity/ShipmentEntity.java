package com.fasterEnvios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @OneToOne
    @JoinColumn(name = "payment_transaction_id")
    private PaymentTransactionEntity  paymentTransaction;
    @OneToOne
    @JoinColumn(name = "package_id")
    private PackageEntity  packages;
    @Column(nullable = false)
    private LocalDateTime  createdAt;
    @Column(nullable = false)
    private LocalDateTime estimatedDeliveryDate;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private PersonEntity sender;
    @ManyToOne
    @JoinColumn(name = "addressee_id")
    private PersonEntity Addressee;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private double distance;
    @Column(nullable = false)
    private String state;

}

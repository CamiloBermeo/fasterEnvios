package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethodEntity {

    private Long id;
    private String methodName;
    private boolean status;

}

package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethodEntity {

    private Long id;
    private String methodName;
    private boolean status;

}

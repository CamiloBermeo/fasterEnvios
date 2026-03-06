package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {

    private Long id;
    private UserEntity user;
    private OfficeEntity office;

}

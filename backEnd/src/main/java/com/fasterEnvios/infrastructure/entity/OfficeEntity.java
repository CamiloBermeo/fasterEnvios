package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeEntity {
    private Long id;
    private String officeName;
    private String address;
    private String city;
    private String phoneNumber;

}

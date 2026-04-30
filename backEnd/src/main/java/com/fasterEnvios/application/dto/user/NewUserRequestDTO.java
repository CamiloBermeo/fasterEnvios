package com.fasterEnvios.application.dto.user;

public record NewUserRequestDTO (
  String name,
  String lastName,
  String email,
  String password,
  String city,
  String phoneNumber,
  String identityDocument,
  String role
){}

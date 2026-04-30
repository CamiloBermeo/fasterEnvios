package com.fasterEnvios.application.useCase.user;

import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserByDocument {
    private final IUserRepository repository;

    public Optional<UserModel> execute(String document){
        return repository.findByDocument(document);
    }
}

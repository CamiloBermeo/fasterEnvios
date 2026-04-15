package com.fasterEnvios.application.useCase.person;

import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.domain.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindPersonByIdentityDocument {
    private final IPersonRepository personRepository;

    public Optional<Person> execute(String identityDocument){
        return personRepository.findPersonByIdentityDocument(identityDocument);
    }

}

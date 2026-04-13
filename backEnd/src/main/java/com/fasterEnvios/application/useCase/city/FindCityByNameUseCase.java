package com.fasterEnvios.application.useCase.city;

import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.ICityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindCityByNameUseCase {

    private final ICityRepository ICityRepository;

    public Optional<CityDescription> execute (String name){
        return ICityRepository.findCityByName(name);
    }

}

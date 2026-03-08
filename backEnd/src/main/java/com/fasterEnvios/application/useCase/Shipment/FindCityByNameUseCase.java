package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindCityByNameUseCase {

    private CityRepository cityRepository;

    public Optional<CityDescription> execute (String name){
        return cityRepository.findCityByName(name);
    }

}

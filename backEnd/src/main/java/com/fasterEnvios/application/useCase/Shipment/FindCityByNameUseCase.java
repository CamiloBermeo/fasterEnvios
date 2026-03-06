package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.domain.model.CityDescription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindCityByNameUseCase {

    public Optional<CityDescription> execute (String name){

    }

}

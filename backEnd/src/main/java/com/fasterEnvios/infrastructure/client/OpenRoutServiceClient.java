package com.fasterEnvios.infrastructure.client;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterEnvios.application.exceptions.client.ExternalServiceException;
import com.fasterEnvios.application.exceptions.client.JsonMapperInternalServiceException;
import com.fasterEnvios.application.exceptions.client.JsonMapperResponseClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OpenRoutServiceClient {
    @Value("${client.openRouteService-apiKey}")
    private String apiKey;


    //eticion para saber la distancia entre dos ciudades
    public ClientResponseDTO requestDistance(ClientRequestDTO dto) {
        String jsonBody;
        JsonNode root;
        HttpResponse<String> response = null;
        String nameApi = "Open Rout Service";
        System.out.println(dto.coordinates());
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            jsonBody = objectMapper.writeValueAsString(dto);

        } catch (IOException e) {
            throw new JsonMapperInternalServiceException(nameApi);
        }


        String url = "https://api.openrouteservice.org/v2/directions/driving-car";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExternalServiceException(nameApi, 502, "ERROR al conectar con el cliente");
        }catch (IOException e){
            throw new ExternalServiceException(nameApi, 502, "ERROR al conectar con el cliente");
        }

        if (response.statusCode() != 200) {
            throw new ExternalServiceException(nameApi, response.statusCode(), response.body());
        }
        try {

            root = objectMapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            throw new JsonMapperResponseClientException(nameApi);
        }

        JsonNode location = root.path("routes").get(0).path("summary");
        double distance = location.path("distance").asDouble();
        double duration = location.path("duration").asDouble();

        return new ClientResponseDTO(distance, duration);
    }

    //peticion para saber las coordenadas de las cudades
    public CityCoordinatesResponseDTO requestCoordinates(CityCoordinatesRequestDTO dto) {
        String jsonBody;
        JsonNode root;
        HttpResponse<String> response = null;
        String nameApi = "Open Rout Service";

        ObjectMapper objectMapper = new ObjectMapper();
        String encodeCity = URLEncoder.encode(dto.name(), StandardCharsets.UTF_8);
        String encodeCountry = URLEncoder.encode(dto.country(), StandardCharsets.UTF_8);
        String url = "https://api.openrouteservice.org/geocode/search?api_key=" + apiKey + "&text=" + encodeCity + "," + encodeCountry + "&size=1";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ExternalServiceException(nameApi, 502, "Error al conectar con el cliente");
        }catch (IOException e){
            throw new ExternalServiceException(nameApi, 502, "Error al conectar con el cliente");
        }
        if (response.statusCode() != 200) {
            nameApi = "Open Rout Service";
            throw new ExternalServiceException(nameApi, response.statusCode(), response.body());
        }
        try {

            root = objectMapper.readTree(response.body());
        } catch (JsonProcessingException e) {

            throw new JsonMapperResponseClientException(nameApi);
        }
        JsonNode firstFeature = root.path("features").get(0);
        JsonNode coordinates = firstFeature.path("geometry").path("coordinates");

        double latitude = coordinates.get(0).asDouble();
        double longitude = coordinates.get(1).asDouble();


        return new CityCoordinatesResponseDTO(latitude, longitude);
    }

}

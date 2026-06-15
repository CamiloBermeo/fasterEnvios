package com.fasterEnvios.infrastructure.client;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterEnvios.domain.exceptions.client.ExternalServiceException;
import com.fasterEnvios.domain.exceptions.client.JsonMapperInternalServiceException;
import com.fasterEnvios.domain.exceptions.client.JsonMapperResponseClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenRouteServiceClientTest {

    @Mock
    ObjectMapper objectMapper;
    @Mock
    HttpClient httpClient;
    @Mock
    HttpResponse<String> httpResponse;
    OpenRouteServiceClient openRouteServiceClient;

    @BeforeEach
    void setUp() {
        openRouteServiceClient = new OpenRouteServiceClient(
                objectMapper,
                httpClient,
                "API_KEY_TEST"
        );
    }

    @Test
    void requestDistance_whenSerializationFails_shouldThrowJsonMapperException() throws JsonProcessingException {
        ClientRequestDTO dto = buildClientRequestForTest();
        when(objectMapper.writeValueAsString(any()))
                .thenThrow(new JsonProcessingException("CLIENTE") {
                });

        assertThrows(JsonMapperInternalServiceException.class, () ->
                openRouteServiceClient.requestDistance(dto));
    }

    @Test
    void requestDistance_whenConnectionWithApFails_shouldThrowExternalServiceException() throws IOException, InterruptedException {
        ClientRequestDTO dto = buildClientRequestForTest();

        when(objectMapper.writeValueAsString(any()))
                .thenReturn("JSON_TEST");
        when(httpClient.send(any(), any()))
                .thenThrow(new InterruptedException());

        assertThrows(ExternalServiceException.class, () ->
                openRouteServiceClient.requestDistance(dto));
    }

    @Test
    void requestDistance_whenIOExceptionOccurs_shouldThrowExternalServiceException() throws IOException, InterruptedException {
        ClientRequestDTO dto = buildClientRequestForTest();

        when(objectMapper.writeValueAsString(any()))
                .thenReturn("JSON_TEST");
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException());

        assertThrows(ExternalServiceException.class, () ->
                openRouteServiceClient.requestDistance(dto));
    }

    @Test
    void requestCoordinates_whenConnectionWithApFails_shouldThrowExternalServiceException() throws IOException, InterruptedException {
        CityCoordinatesRequestDTO dto = new CityCoordinatesRequestDTO("CALI", "COLOMBIA");

        when(httpClient.send(any(), any()))
                .thenThrow(new InterruptedException());

        assertThrows(ExternalServiceException.class, () ->
                openRouteServiceClient.requestCoordinates(dto));
    }

    @Test
    void requestCoordinates_whenIOExceptionOccurs_shouldThrowExternalServiceException() throws IOException, InterruptedException {
        CityCoordinatesRequestDTO dto = new CityCoordinatesRequestDTO("CALI", "COLOMBIA");
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException());
        assertThrows(ExternalServiceException.class, () -> openRouteServiceClient.requestCoordinates(dto));
    }

    @Test
    void requestDistance_whenStatusCodeIsNot200_shouldThrowExternalServiceException() throws IOException, InterruptedException {
        ClientRequestDTO dto = buildClientRequestForTest();
        when(objectMapper.writeValueAsString(any()))
                .thenReturn("JSON_TEST");
        when(httpResponse.statusCode()).thenReturn(500);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);
        assertThrows(ExternalServiceException.class, () ->
                openRouteServiceClient.requestDistance(dto));
    }

    @Test
    void requestCoordinates_whenStatusCodeIsNot200_shouldThrowExternalServiceException() throws IOException, InterruptedException {
        CityCoordinatesRequestDTO dto = new CityCoordinatesRequestDTO("CALI", "COLOMBIA");
        when(httpResponse.statusCode()).thenReturn(500);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);
        assertThrows(ExternalServiceException.class, () ->
                openRouteServiceClient.requestCoordinates(dto));
    }

    @Test
    void requestDistance_whenFiledObjectMapper_shouldThrowJsonMapperResponseClientException() throws IOException, InterruptedException {
        ClientRequestDTO dto = buildClientRequestForTest();

        when(objectMapper.writeValueAsString(any()))
                .thenReturn("JSON_TEST");
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);
        when(objectMapper.readTree(httpResponse.body()))
                .thenThrow(JsonProcessingException.class);
        assertThrows(JsonMapperResponseClientException.class, () ->
                openRouteServiceClient.requestDistance(dto));
    }

    @Test
    void requestCoordinates_whenFiledObjectMapper_shouldThrowJsonMapperResponseClientException() throws IOException, InterruptedException {
        CityCoordinatesRequestDTO dto = new CityCoordinatesRequestDTO("CALI", "COLOMBIA");

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);
        when(objectMapper.readTree(httpResponse.body()))
                .thenThrow(JsonProcessingException.class);
        assertThrows(JsonMapperResponseClientException.class, () ->
                openRouteServiceClient.requestCoordinates(dto));
    }

    @Test
    void requestDistance_whenIsSuccess_shouldReturnClientResponseDto() throws IOException, InterruptedException {
        ClientRequestDTO dto = buildClientRequestForTest();
        String validJson = "{\"routes\":[{\"summary\":{\"distance\":150.0,\"duration\":3600.0}}]}";


        when(objectMapper.writeValueAsString(any()))
                .thenReturn("JSON_TEST");
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);
        ObjectMapper realMapper = new ObjectMapper();
        JsonNode realJsonNode = realMapper.readTree(validJson);
        when(httpResponse.body()).thenReturn(validJson);
        when(objectMapper.readTree(validJson))
                .thenReturn(realJsonNode);

        ClientResponseDTO result = openRouteServiceClient.requestDistance(dto);
        assertNotNull(result);
        assertEquals(150.0, result.distance());
        assertEquals(3600.0, result.duration());
    }

    @Test
    void requestCoordinates_whenIsSuccess_shouldReturnClientResponseDto() throws IOException, InterruptedException {
        CityCoordinatesRequestDTO dto = new CityCoordinatesRequestDTO("CALI", "COLOMBIA");
        String validJson = "{\"features\":[{\"geometry\":{\"coordinates\":[-76.5225,3.4372]}}]}";

        when(httpResponse.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);
        ObjectMapper realMapper = new ObjectMapper();
        JsonNode realJsonNode = realMapper.readTree(validJson);
        when(httpResponse.body()).thenReturn(validJson);
        when(objectMapper.readTree(validJson))
                .thenReturn(realJsonNode);

        CityCoordinatesResponseDTO result = openRouteServiceClient.requestCoordinates(dto);
        assertNotNull(result);
        assertEquals(3.4372, result.latitude());
        assertEquals(-76.5225, result.longitude());
    }

    private ClientRequestDTO buildClientRequestForTest() {
        List<double[]> coordinates = List.of(
                new double[]{123.3, 321.1},
                new double[]{456.6, 654.4}
        );
        return new ClientRequestDTO(
                coordinates,
                "KM",
                "ES"
        );
    }
}
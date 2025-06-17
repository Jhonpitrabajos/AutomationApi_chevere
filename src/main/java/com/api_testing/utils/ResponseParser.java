package com.api_testing.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class ResponseParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T parseResponse(Response response, Class<T> responseType) {
        try {
            if (response.getContentType().contains("application/json") || response.getContentType().contains("text/plain")) {
                return objectMapper.readValue(response.getBody().asString(), responseType);
            } else {
                throw new IllegalStateException("Cannot parse object because no supported Content-Type was specified in response. Content-Type was '" + response.getContentType() + "'.");
            }
        } catch (JsonProcessingException | IllegalStateException e) {
            throw new IllegalStateException("Failed to parse response as " + responseType.getSimpleName() + ". Response body: " + response.getBody().asString(), e);
        }
    }

    public static String extractAsString(Response response) {
        return response.asString();
    }
}

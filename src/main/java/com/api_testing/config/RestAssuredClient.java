package com.api_testing.config;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {

    public RestAssuredClient() {
    }

    public RestAssuredClient addHeader(RequestSpecification request, String key, String value) {
        request.header(key, value);
        return this;
    }

    public RestAssuredClient addQueryParam(RequestSpecification request, String key, String value) {
        request.queryParam(key, value);
        return this;
    }

    public RestAssuredClient addPathParam(RequestSpecification request, String key, String value) {
        request.pathParam(key, value);
        return this;
    }

    public RestAssuredClient addBody(RequestSpecification request, Object body) {
        request.body(body);
        return this;
    }

    public Response get(RequestSpecification request, String endpoint) {
        logRequest("GET", endpoint);
        Response response = request.get(endpoint);
        logResponse(response);
        return response;
    }

    public Response post(RequestSpecification request, String endpoint) {
        logRequest("POST", endpoint);
        Response response = request.post(endpoint);
        logResponse(response);
        return response;
    }

    public Response put(RequestSpecification request, String endpoint) {
        logRequest("PUT", endpoint);
        Response response = request.put(endpoint);
        logResponse(response);
        return response;
    }

    public Response delete(RequestSpecification request, String endpoint) {
        logRequest("DELETE", endpoint);
        Response response = request.delete(endpoint);
        logResponse(response);
        return response;
    }

    private void logRequest(String method, String endpoint) {
        System.out.println(">>> " + method + " " + endpoint);
    }

    private void logResponse(Response response) {
        String status = response.getStatusCode() >= 200 && response.getStatusCode() < 300 ? "SUCCESS" : "ERROR";
        System.out.println("<<< " + status + " (" + response.getStatusCode() + ")");
        
        // Only show response body if it's short and relevant
        String body = response.getBody().asString();
        if (body.length() < 200 && !body.isEmpty()) {
            System.out.println("    " + body);
        }
        System.out.println();
    }
}

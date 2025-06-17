package com.api_testing.config;

import com.api_testing.utils.ConfigUtil;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfig {

    private static final String BASE_URI = ConfigUtil.getProperty("base.url");

    public static RequestSpecification getRequestSpecification() {
        // Simple configuration without API key (will be added in factory)
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType("application/json")
                .build();
    }

    public static void configure() {
        // Don't set global request specification to avoid conflicts
        // RestAssured.requestSpecification = getRequestSpecification();
    }
}

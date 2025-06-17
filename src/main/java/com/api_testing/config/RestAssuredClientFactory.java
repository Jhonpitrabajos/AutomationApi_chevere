package com.api_testing.config;

import com.api_testing.utils.ConfigUtil;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClientFactory {

    public static RequestSpecification createRequest() {
        // Create clean request without API key (reqres.in is public)
        RequestSpecification request = RestAssured.given();
        
        // Set base URI and content type
        request.baseUri(ConfigUtil.getProperty("base.url"));
        request.contentType("application/json");
        
        return request;
    }
}

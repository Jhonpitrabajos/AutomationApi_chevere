package com.api_testing.api.impl;

import com.api_testing.api.interfaces.AuthApiService;
import com.api_testing.config.RestAssuredClient;
import com.api_testing.config.RestAssuredClientFactory;
import com.api_testing.models.LoginRequest;
import com.api_testing.models.LoginResponse;
import com.api_testing.models.RegisterRequest;
import com.api_testing.models.RegisterResponse;
import com.api_testing.utils.ResponseParser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthApiServiceImpl implements AuthApiService {
    
    private static final Logger logger = LogManager.getLogger(AuthApiServiceImpl.class);
    private final RestAssuredClient restAssuredClient;
    
    public AuthApiServiceImpl() {
        this.restAssuredClient = new RestAssuredClient();
    }
    
    @Override
    public Response register(RegisterRequest registerRequest) {
        logger.info("Registering user with email: {}", registerRequest.getEmail());
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addBody(request, registerRequest);
        return restAssuredClient.post(request, "/api/register");
    }
    
    @Override
    public Response login(LoginRequest loginRequest) {
        logger.info("Logging in user with email: {}", loginRequest.getEmail());
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addBody(request, loginRequest);
        return restAssuredClient.post(request, "/api/login");
    }
    
    @Override
    public RegisterResponse parseRegisterResponse(Response response) {
        return ResponseParser.parseResponse(response, RegisterResponse.class);
    }
    
    @Override
    public LoginResponse parseLoginResponse(Response response) {
        return ResponseParser.parseResponse(response, LoginResponse.class);
    }
}

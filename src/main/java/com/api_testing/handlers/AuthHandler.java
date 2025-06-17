package com.api_testing.handlers;

import com.api_testing.constants.TestContextKeys;
import com.api_testing.context.TestContext;
import com.api_testing.facades.AuthFacade;
import com.api_testing.models.LoginResponse;
import com.api_testing.models.RegisterResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthHandler {
    
    private static final Logger logger = LogManager.getLogger(AuthHandler.class);
    private final AuthFacade authFacade;
    private final TestContext testContext;
    
    public AuthHandler(TestContext testContext) {
        this.testContext = testContext;
        this.authFacade = new AuthFacade();
    }
    
    public void registerUser(String email, String password) {
        logger.info("Handler: Registering user with email: {}", email);
        RegisterResponse registerResponse = authFacade.registerUser(email, password);
        
        testContext.set(TestContextKeys.REGISTER_RESPONSE, registerResponse);
        testContext.set(TestContextKeys.USER_EMAIL, email);
        testContext.set(TestContextKeys.USER_PASSWORD, password);
        
        if (registerResponse.getToken() != null) {
            testContext.set(TestContextKeys.AUTH_TOKEN, registerResponse.getToken());
            logger.info("User registered successfully with token");
        } else {
            logger.error("Registration failed: {}", registerResponse.getError());
        }
    }
    
    public void loginUser(String email, String password) {
        logger.info("Handler: Logging in user with email: {}", email);
        LoginResponse loginResponse = authFacade.loginUser(email, password);
        
        testContext.set(TestContextKeys.LOGIN_RESPONSE, loginResponse);
        
        if (loginResponse.getToken() != null) {
            testContext.set(TestContextKeys.AUTH_TOKEN, loginResponse.getToken());
            testContext.set(TestContextKeys.USER_TOKEN, loginResponse.getToken());
            logger.info("User logged in successfully with token");
        } else {
            logger.error("Login failed: {}", loginResponse.getError());
        }
    }
    
    public void loginUserFromContext() {
        String email = testContext.get(TestContextKeys.USER_EMAIL, String.class);
        String password = testContext.get(TestContextKeys.USER_PASSWORD, String.class);
        
        if (email == null || password == null) {
            throw new IllegalStateException("Email or password not found in context");
        }
        
        loginUser(email, password);
    }
    
    public void registerUserRaw(String email, String password) {
        logger.info("Handler: Registering user (raw response) with email: {}", email);
        Response response = authFacade.registerUserRaw(email, password);
        
        testContext.set(TestContextKeys.RESPONSE_DATA, response);
        testContext.set(TestContextKeys.STATUS_CODE, response.getStatusCode());
        testContext.set(TestContextKeys.USER_EMAIL, email);
        testContext.set(TestContextKeys.USER_PASSWORD, password);
        
        logger.info("Registration raw response status: {}", response.getStatusCode());
    }
    
    public void loginUserRaw(String email, String password) {
        logger.info("Handler: Logging in user (raw response) with email: {}", email);
        Response response = authFacade.loginUserRaw(email, password);
        
        testContext.set(TestContextKeys.RESPONSE_DATA, response);
        testContext.set(TestContextKeys.STATUS_CODE, response.getStatusCode());
        
        logger.info("Login raw response status: {}", response.getStatusCode());
    }
}

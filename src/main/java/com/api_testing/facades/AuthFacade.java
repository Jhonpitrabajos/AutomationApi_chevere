package com.api_testing.facades;

import com.api_testing.api.interfaces.AuthApiService;
import com.api_testing.api.impl.AuthApiServiceImpl;
import com.api_testing.data.services.AuthDataService;
import com.api_testing.models.LoginRequest;
import com.api_testing.models.LoginResponse;
import com.api_testing.models.RegisterRequest;
import com.api_testing.models.RegisterResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthFacade {
    
    private static final Logger logger = LogManager.getLogger(AuthFacade.class);
    private final AuthApiService authApiService;
    private final AuthDataService authDataService;
    
    public AuthFacade() {
        this.authApiService = new AuthApiServiceImpl();
        this.authDataService = new AuthDataService();
    }
    
    public AuthFacade(AuthApiService authApiService, AuthDataService authDataService) {
        this.authApiService = authApiService;
        this.authDataService = authDataService;
    }
    
    public RegisterResponse registerUser(String email, String password) {
        logger.info("Facade: Registering user with email {}", email);
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email(email)
                .password(password)
                .build();
        
        Response response = authApiService.register(registerRequest);
        RegisterResponse registerResponse = authApiService.parseRegisterResponse(response);
        
        if (response.getStatusCode() == 200) {
            logger.info("User registered successfully with ID: {}", registerResponse.getId());
            // Store registration data with encrypted token
            authDataService.storeRegistration(email, registerResponse);
        } else {
            logger.error("Registration failed: {}", registerResponse.getError());
        }
        
        return registerResponse;
    }
    
    public LoginResponse loginUser(String email, String password) {
        logger.info("Facade: Logging in user with email {}", email);
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
        
        Response response = authApiService.login(loginRequest);
        LoginResponse loginResponse = authApiService.parseLoginResponse(response);
        
        if (response.getStatusCode() == 200) {
            logger.info("User logged in successfully. Token received.");
            // Store login data with encrypted token
            authDataService.storeLogin(email, loginResponse);
        } else {
            logger.error("Login failed: {}", loginResponse.getError());
        }
        
        return loginResponse;
    }
    
    public Response registerUserRaw(String email, String password) {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email(email)
                .password(password)
                .build();
        return authApiService.register(registerRequest);
    }
    
    public Response loginUserRaw(String email, String password) {
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
        return authApiService.login(loginRequest);
    }
    
    // Data service methods
    public String getDecryptedToken(String email) {
        return authDataService.getDecryptedToken(email);
    }
    
    public boolean isUserRegistered(String email) {
        return authDataService.isUserRegistered(email);
    }
    
    public boolean isUserLoggedIn(String email) {
        return authDataService.isUserLoggedIn(email);
    }
    
    public boolean isValidToken(String token) {
        return authDataService.isValidToken(token);
    }
    
    public void clearAuthData() {
        authDataService.clearAllAuthData();
    }
}

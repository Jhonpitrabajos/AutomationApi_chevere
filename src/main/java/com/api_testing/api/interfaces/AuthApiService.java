package com.api_testing.api.interfaces;

import com.api_testing.models.LoginRequest;
import com.api_testing.models.LoginResponse;
import com.api_testing.models.RegisterRequest;
import com.api_testing.models.RegisterResponse;
import io.restassured.response.Response;

public interface AuthApiService {
    
    /**
     * Register new user
     * @param registerRequest registration data
     * @return Response containing registration result
     */
    Response register(RegisterRequest registerRequest);
    
    /**
     * Login user
     * @param loginRequest login credentials
     * @return Response containing login result
     */
    Response login(LoginRequest loginRequest);
    
    /**
     * Parse registration response
     * @param response API response
     * @return RegisterResponse object
     */
    RegisterResponse parseRegisterResponse(Response response);
    
    /**
     * Parse login response
     * @param response API response
     * @return LoginResponse object
     */
    LoginResponse parseLoginResponse(Response response);
}

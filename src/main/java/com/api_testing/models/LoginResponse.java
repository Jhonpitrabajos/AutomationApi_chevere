package com.api_testing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    
    @JsonProperty("token")
    private String token;
    
    @JsonProperty("error")
    private String error;
    
    // Default constructor
    public LoginResponse() {}
    
    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}

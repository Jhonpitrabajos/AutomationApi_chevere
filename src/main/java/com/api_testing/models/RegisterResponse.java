package com.api_testing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("token")
    private String token;
    
    @JsonProperty("error")
    private String error;
    
    // Default constructor
    public RegisterResponse() {}
    
    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}

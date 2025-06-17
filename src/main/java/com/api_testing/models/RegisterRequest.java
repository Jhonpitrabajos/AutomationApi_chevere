package com.api_testing.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("password")
    private String password;
    
    // Default constructor
    public RegisterRequest() {}
    
    // Constructor with parameters
    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    // Builder pattern
    public static RegisterRequestBuilder builder() {
        return new RegisterRequestBuilder();
    }
    
    public static class RegisterRequestBuilder {
        private RegisterRequest request = new RegisterRequest();
        
        public RegisterRequestBuilder email(String email) {
            request.setEmail(email);
            return this;
        }
        
        public RegisterRequestBuilder password(String password) {
            request.setPassword(password);
            return this;
        }
        
        public RegisterRequest build() {
            return request;
        }
    }
}

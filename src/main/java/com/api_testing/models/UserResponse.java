package com.api_testing.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    
    @JsonProperty("data")
    private User data;
    
    @JsonProperty("support")
    private UserListResponse.Support support;
    
    // Default constructor
    public UserResponse() {}
    
    // Getters and Setters
    public User getData() { return data; }
    public void setData(User data) { this.data = data; }
    
    public UserListResponse.Support getSupport() { return support; }
    public void setSupport(UserListResponse.Support support) { this.support = support; }
}

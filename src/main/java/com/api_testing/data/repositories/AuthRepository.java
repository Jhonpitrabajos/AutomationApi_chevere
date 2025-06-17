package com.api_testing.data.repositories;

import com.api_testing.models.LoginResponse;
import com.api_testing.models.RegisterResponse;

public interface AuthRepository {
    
    /**
     * Save registration data
     */
    void saveRegistration(String email, RegisterResponse response);
    
    /**
     * Save login data
     */
    void saveLogin(String email, LoginResponse response);
    
    /**
     * Get encrypted token by email
     */
    String getEncryptedToken(String email);
    
    /**
     * Check if user is registered
     */
    boolean isUserRegistered(String email);
    
    /**
     * Check if user is logged in
     */
    boolean isUserLoggedIn(String email);
    
    /**
     * Clear all auth data (for testing)
     */
    void clear();
}

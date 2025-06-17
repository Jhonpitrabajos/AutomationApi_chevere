package com.api_testing.data.services;

import com.api_testing.data.repositories.AuthRepository;
import com.api_testing.data.repositories.impl.InMemoryAuthRepository;
import com.api_testing.models.LoginResponse;
import com.api_testing.models.RegisterResponse;
import com.api_testing.security.TokenEncryption;

public class AuthDataService {
    
    private final AuthRepository authRepository;
    
    public AuthDataService() {
        this.authRepository = new InMemoryAuthRepository();
    }
    
    public AuthDataService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }
    
    public void storeRegistration(String email, RegisterResponse response) {
        authRepository.saveRegistration(email, response);
    }
    
    public void storeLogin(String email, LoginResponse response) {
        authRepository.saveLogin(email, response);
    }
    
    public String getDecryptedToken(String email) {
        return authRepository.getEncryptedToken(email);
    }
    
    public boolean isUserRegistered(String email) {
        return authRepository.isUserRegistered(email);
    }
    
    public boolean isUserLoggedIn(String email) {
        return authRepository.isUserLoggedIn(email);
    }
    
    public boolean isValidToken(String token) {
        return TokenEncryption.isValidToken(token);
    }
    
    public void clearAllAuthData() {
        authRepository.clear();
    }
}

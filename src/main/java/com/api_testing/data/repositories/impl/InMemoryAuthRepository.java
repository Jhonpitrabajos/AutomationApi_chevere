package com.api_testing.data.repositories.impl;

import com.api_testing.data.repositories.AuthRepository;
import com.api_testing.models.LoginResponse;
import com.api_testing.models.RegisterResponse;
import com.api_testing.security.TokenEncryption;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAuthRepository implements AuthRepository {
    
    private final Map<String, RegisterResponse> registrations = new ConcurrentHashMap<>();
    private final Map<String, LoginResponse> logins = new ConcurrentHashMap<>();
    private final Map<String, String> encryptedTokens = new ConcurrentHashMap<>();
    
    @Override
    public void saveRegistration(String email, RegisterResponse response) {
        registrations.put(email, response);
        if (response.getToken() != null) {
            String encryptedToken = TokenEncryption.encrypt(response.getToken());
            encryptedTokens.put(email, encryptedToken);
        }
    }
    
    @Override
    public void saveLogin(String email, LoginResponse response) {
        logins.put(email, response);
        if (response.getToken() != null) {
            String encryptedToken = TokenEncryption.encrypt(response.getToken());
            encryptedTokens.put(email, encryptedToken);
        }
    }
    
    @Override
    public String getEncryptedToken(String email) {
        String encryptedToken = encryptedTokens.get(email);
        if (encryptedToken != null) {
            return TokenEncryption.decrypt(encryptedToken);
        }
        return null;
    }
    
    @Override
    public boolean isUserRegistered(String email) {
        return registrations.containsKey(email);
    }
    
    @Override
    public boolean isUserLoggedIn(String email) {
        return logins.containsKey(email) && encryptedTokens.containsKey(email);
    }
    
    @Override
    public void clear() {
        registrations.clear();
        logins.clear();
        encryptedTokens.clear();
    }
}

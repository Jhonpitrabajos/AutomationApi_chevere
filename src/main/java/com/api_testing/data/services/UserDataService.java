package com.api_testing.data.services;

import com.api_testing.data.repositories.UserRepository;
import com.api_testing.data.repositories.impl.InMemoryUserRepository;
import com.api_testing.models.User;

import java.util.List;
import java.util.Optional;

public class UserDataService {
    
    private final UserRepository userRepository;
    
    public UserDataService() {
        this.userRepository = new InMemoryUserRepository();
    }
    
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void storeUser(User user) {
        userRepository.save(user);
    }
    
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public void updateUser(User user) {
        userRepository.update(user);
    }
    
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
    
    public boolean userExists(Integer id) {
        return userRepository.existsById(id);
    }
    
    public void clearAllUsers() {
        userRepository.clear();
    }
}

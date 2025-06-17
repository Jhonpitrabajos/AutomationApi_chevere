package com.api_testing.data.repositories;

import com.api_testing.models.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    
    /**
     * Save user data to storage
     */
    void save(User user);
    
    /**
     * Find user by ID
     */
    Optional<User> findById(Integer id);
    
    /**
     * Find user by email
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Get all users
     */
    List<User> findAll();
    
    /**
     * Update user data
     */
    void update(User user);
    
    /**
     * Delete user by ID
     */
    void deleteById(Integer id);
    
    /**
     * Check if user exists
     */
    boolean existsById(Integer id);
    
    /**
     * Clear all data (for testing)
     */
    void clear();
}

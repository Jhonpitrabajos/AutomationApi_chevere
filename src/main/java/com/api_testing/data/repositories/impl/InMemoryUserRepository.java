package com.api_testing.data.repositories.impl;

import com.api_testing.data.repositories.UserRepository;
import com.api_testing.models.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final Map<String, User> usersByEmail = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1000);
    
    @Override
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.incrementAndGet());
        }
        users.put(user.getId(), user);
        if (user.getEmail() != null) {
            usersByEmail.put(user.getEmail(), user);
        }
    }
    
    @Override
    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }
    
    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
    
    @Override
    public void update(User user) {
        if (user.getId() != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            if (user.getEmail() != null) {
                usersByEmail.put(user.getEmail(), user);
            }
        }
    }
    
    @Override
    public void deleteById(Integer id) {
        User user = users.remove(id);
        if (user != null && user.getEmail() != null) {
            usersByEmail.remove(user.getEmail());
        }
    }
    
    @Override
    public boolean existsById(Integer id) {
        return users.containsKey(id);
    }
    
    @Override
    public void clear() {
        users.clear();
        usersByEmail.clear();
    }
}

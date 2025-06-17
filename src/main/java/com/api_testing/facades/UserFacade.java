package com.api_testing.facades;

import com.api_testing.api.interfaces.UserApiService;
import com.api_testing.api.impl.UserApiServiceImpl;
import com.api_testing.data.services.UserDataService;
import com.api_testing.models.User;
import com.api_testing.models.UserListResponse;
import com.api_testing.models.UserResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserFacade {
    
    private static final Logger logger = LogManager.getLogger(UserFacade.class);
    private final UserApiService userApiService;
    private final UserDataService userDataService;
    
    public UserFacade() {
        this.userApiService = new UserApiServiceImpl();
        this.userDataService = new UserDataService();
    }
    
    public UserFacade(UserApiService userApiService, UserDataService userDataService) {
        this.userApiService = userApiService;
        this.userDataService = userDataService;
    }
    
    public UserListResponse getUserList(int page) {
        logger.info("Facade: Getting user list for page {}", page);
        Response response = userApiService.getUsers(page);
        
        if (response.getStatusCode() == 200) {
            UserListResponse userList = userApiService.parseUserListResponse(response);
            // Store users in data service for later use
            userList.getData().forEach(userDataService::storeUser);
            return userList;
        } else {
            logger.error("Failed to get user list. Status code: {}", response.getStatusCode());
            throw new RuntimeException("Failed to get user list: " + response.getStatusCode());
        }
    }
    
    public User getUserById(int userId) {
        logger.info("Facade: Getting user by ID {}", userId);
        
        // First check local data service
        var localUser = userDataService.getUserById(userId);
        if (localUser.isPresent()) {
            logger.info("User found in local data service");
            return localUser.get();
        }
        
        // If not found locally, call API
        Response response = userApiService.getUserById(userId);
        
        if (response.getStatusCode() == 200) {
            UserResponse userResponse = userApiService.parseUserResponse(response);
            User user = userResponse.getData();
            // Store in data service for future use
            userDataService.storeUser(user);
            return user;
        } else if (response.getStatusCode() == 404) {
            logger.warn("User not found with ID: {}", userId);
            return null;
        } else {
            logger.error("Failed to get user by ID. Status code: {}", response.getStatusCode());
            throw new RuntimeException("Failed to get user: " + response.getStatusCode());
        }
    }
    
    public User createUser(String name, String job) {
        logger.info("Facade: Creating user with name {} and job {}", name, job);
        User user = User.builder()
                .name(name)
                .job(job)
                .build();
        
        Response response = userApiService.createUser(user);
        
        if (response.getStatusCode() == 201) {
            User createdUser = userApiService.parseUserCreationResponse(response);
            // Store in data service
            userDataService.storeUser(createdUser);
            return createdUser;
        } else {
            logger.error("Failed to create user. Status code: {}", response.getStatusCode());
            throw new RuntimeException("Failed to create user: " + response.getStatusCode());
        }
    }
    
    public User updateUser(int userId, String name, String job) {
        logger.info("Facade: Updating user ID {} with name {} and job {}", userId, name, job);
        User user = User.builder()
                .name(name)
                .job(job)
                .build();
        
        Response response = userApiService.updateUser(userId, user);
        
        if (response.getStatusCode() == 200) {
            User updatedUser = userApiService.parseUserCreationResponse(response);
            updatedUser.setId(userId); // Ensure ID is set
            // Update in data service
            userDataService.updateUser(updatedUser);
            return updatedUser;
        } else {
            logger.error("Failed to update user. Status code: {}", response.getStatusCode());
            throw new RuntimeException("Failed to update user: " + response.getStatusCode());
        }
    }
    
    public boolean deleteUser(int userId) {
        logger.info("Facade: Deleting user ID {}", userId);
        Response response = userApiService.deleteUser(userId);
        
        if (response.getStatusCode() == 204) {
            logger.info("User deleted successfully");
            // Remove from data service
            userDataService.deleteUser(userId);
            return true;
        } else {
            logger.error("Failed to delete user. Status code: {}", response.getStatusCode());
            return false;
        }
    }
    
    public Response getUserByIdRaw(int userId) {
        return userApiService.getUserById(userId);
    }
    
    // Data service methods
    public boolean userExistsLocally(int userId) {
        return userDataService.userExists(userId);
    }
    
    public void clearLocalUserData() {
        userDataService.clearAllUsers();
    }

    // Data service methods for system tests
    public boolean userExistsLocally(Integer userId) {
        return userDataService.userExists(userId);
    }
    
}

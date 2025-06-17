package com.api_testing.api.impl;

import com.api_testing.api.interfaces.UserApiService;
import com.api_testing.config.RestAssuredClient;
import com.api_testing.config.RestAssuredClientFactory;
import com.api_testing.models.User;
import com.api_testing.models.UserListResponse;
import com.api_testing.models.UserResponse;
import com.api_testing.utils.ResponseParser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserApiServiceImpl implements UserApiService {
    
    private static final Logger logger = LogManager.getLogger(UserApiServiceImpl.class);
    private final RestAssuredClient restAssuredClient;
    
    public UserApiServiceImpl() {
        this.restAssuredClient = new RestAssuredClient();
    }
    
    @Override
    public Response getUsers(int page) {
        logger.info("Getting users for page: {}", page);
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addQueryParam(request, "page", String.valueOf(page));
        return restAssuredClient.get(request, "/api/users");
    }
    
    @Override
    public Response getUserById(int userId) {
        logger.info("Getting user by ID: {}", userId);
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        return restAssuredClient.get(request, "/api/users/" + userId);
    }
    
    @Override
    public Response createUser(User user) {
        logger.info("Creating user: {}", user.getName());
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addBody(request, user);
        return restAssuredClient.post(request, "/api/users");
    }
    
    @Override
    public Response updateUser(int userId, User user) {
        logger.info("Updating user ID: {} with data: {}", userId, user.getName());
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        restAssuredClient.addBody(request, user);
        return restAssuredClient.put(request, "/api/users/" + userId);
    }
    
    @Override
    public Response deleteUser(int userId) {
        logger.info("Deleting user ID: {}", userId);
        RequestSpecification request = RestAssuredClientFactory.createRequest();
        return restAssuredClient.delete(request, "/api/users/" + userId);
    }
    
    @Override
    public UserListResponse parseUserListResponse(Response response) {
        return ResponseParser.parseResponse(response, UserListResponse.class);
    }
    
    @Override
    public UserResponse parseUserResponse(Response response) {
        return ResponseParser.parseResponse(response, UserResponse.class);
    }
    
    @Override
    public User parseUserCreationResponse(Response response) {
        return ResponseParser.parseResponse(response, User.class);
    }
}

package com.api_testing.api.interfaces;

import com.api_testing.models.User;
import com.api_testing.models.UserListResponse;
import com.api_testing.models.UserResponse;
import io.restassured.response.Response;

public interface UserApiService {
    
    /**
     * Get list of users with pagination
     * @param page page number
     * @return Response containing user list
     */
    Response getUsers(int page);
    
    /**
     * Get single user by ID
     * @param userId user ID
     * @return Response containing user data
     */
    Response getUserById(int userId);
    
    /**
     * Create new user
     * @param user user data
     * @return Response containing created user
     */
    Response createUser(User user);
    
    /**
     * Update existing user
     * @param userId user ID
     * @param user updated user data
     * @return Response containing updated user
     */
    Response updateUser(int userId, User user);
    
    /**
     * Delete user by ID
     * @param userId user ID
     * @return Response
     */
    Response deleteUser(int userId);
    
    /**
     * Parse user list response
     * @param response API response
     * @return UserListResponse object
     */
    UserListResponse parseUserListResponse(Response response);
    
    /**
     * Parse single user response
     * @param response API response
     * @return UserResponse object
     */
    UserResponse parseUserResponse(Response response);
    
    /**
     * Parse created/updated user response
     * @param response API response
     * @return User object
     */
    User parseUserCreationResponse(Response response);
}

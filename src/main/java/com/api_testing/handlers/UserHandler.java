package com.api_testing.handlers;

import com.api_testing.constants.TestContextKeys;
import com.api_testing.context.TestContext;
import com.api_testing.facades.UserFacade;
import com.api_testing.models.User;
import com.api_testing.models.UserListResponse;
import com.api_testing.utils.FakerUtils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserHandler {
    
    private static final Logger logger = LogManager.getLogger(UserHandler.class);
    private final UserFacade userFacade;
    private final TestContext testContext;
    
    public UserHandler(TestContext testContext) {
        this.testContext = testContext;
        this.userFacade = new UserFacade();
    }
    
    public void createRandomUser() {
        logger.info("Handler: Creating random user");
        String name = FakerUtils.generateRandomName();
        String job = "QA Engineer";
        
        User createdUser = userFacade.createUser(name, job);
        
        testContext.set(TestContextKeys.USER_DATA, createdUser);
        testContext.set(TestContextKeys.USER_ID, createdUser.getId());
        
        logger.info("Random user created with ID: {}", createdUser.getId());
    }
    
    public void createUserWithData(String name, String job) {
        logger.info("Handler: Creating user with name: {} and job: {}", name, job);
        User createdUser = userFacade.createUser(name, job);
        
        testContext.set(TestContextKeys.USER_DATA, createdUser);
        testContext.set(TestContextKeys.USER_ID, createdUser.getId());
        
        logger.info("User created with ID: {}", createdUser.getId());
    }
    
    public void getUserById(int userId) {
        logger.info("Handler: Getting user by ID: {}", userId);
        User user = userFacade.getUserById(userId);
        
        if (user != null) {
            testContext.set(TestContextKeys.USER_DATA, user);
            logger.info("User retrieved: {}", user.getFirstName());
        } else {
            logger.warn("User not found with ID: {}", userId);
            testContext.set(TestContextKeys.USER_DATA, null);
        }
    }
    
    public void getUserByIdRaw(int userId) {
        logger.info("Handler: Getting user by ID (raw response): {}", userId);
        Response response = userFacade.getUserByIdRaw(userId);
        
        testContext.set(TestContextKeys.RESPONSE_DATA, response);
        testContext.set(TestContextKeys.STATUS_CODE, response.getStatusCode());
        
        logger.info("Raw response status: {}", response.getStatusCode());
    }
    
    public void getUserList(int page) {
        logger.info("Handler: Getting user list for page: {}", page);
        UserListResponse userList = userFacade.getUserList(page);
        
        testContext.set(TestContextKeys.USER_LIST, userList);
        
        logger.info("Retrieved {} users from page {}", userList.getData().size(), page);
    }
    
    public void updateUser(String name, String job) {
        logger.info("Handler: Updating user with name: {} and job: {}", name, job);
        Integer userId = testContext.get(TestContextKeys.USER_ID, Integer.class);
        
        if (userId == null) {
            throw new IllegalStateException("No user ID found in context for update");
        }
        
        User updatedUser = userFacade.updateUser(userId, name, job);
        testContext.set(TestContextKeys.USER_DATA, updatedUser);
        
        logger.info("User updated successfully");
    }
    
    public void deleteUser() {
        logger.info("Handler: Deleting user");
        Integer userId = testContext.get(TestContextKeys.USER_ID, Integer.class);
        
        if (userId == null) {
            throw new IllegalStateException("No user ID found in context for deletion");
        }
        
        boolean deleted = userFacade.deleteUser(userId);
        testContext.set("user.deleted", deleted);
        
        logger.info("User deletion result: {}", deleted);
    }
    
    public void deleteUserById(int userId) {
        logger.info("Handler: Deleting user by ID: {}", userId);
        boolean deleted = userFacade.deleteUser(userId);
        testContext.set("user.deleted", deleted);
        
        logger.info("User deletion result: {}", deleted);
    }
}

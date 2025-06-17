package com.api_testing.constants;

/**
 * Standardized keys for TestContext to avoid magic strings and improve maintainability
 */
public final class TestContextKeys {
    
    // User related keys
    public static final String USER_ID = "user.id";
    public static final String USER_DATA = "user.data";
    public static final String USER_EMAIL = "user.email";
    public static final String USER_PASSWORD = "user.password";
    public static final String USER_TOKEN = "user.token";
    public static final String USER_LIST = "user.list";
    
    // Request/Response keys
    public static final String REQUEST_PAYLOAD = "request.payload";
    public static final String RESPONSE_DATA = "response.data";
    public static final String STATUS_CODE = "response.status.code";
    
    // Authentication keys
    public static final String AUTH_TOKEN = "auth.token";
    public static final String LOGIN_REQUEST = "login.request";
    public static final String LOGIN_RESPONSE = "login.response";
    public static final String REGISTER_REQUEST = "register.request";
    public static final String REGISTER_RESPONSE = "register.response";
    
    // Scenario keys
    public static final String SCENARIO_NAME = "scenario.name";
    public static final String TEST_DATA = "test.data";
    
    // Error keys
    public static final String ERROR_MESSAGE = "error.message";
    public static final String VALIDATION_ERRORS = "validation.errors";
    
    private TestContextKeys() {
        // Utility class - prevent instantiation
    }
}

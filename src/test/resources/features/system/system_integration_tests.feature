Feature: System Integration Tests - Multi-Service Workflows

  @system @integration
  Scenario: User Registration and Profile Creation Flow
    Given I clear all system data
    When I register a user with email "integration.user@reqres.in" and password "secure123"
    And I store the registration result in auth service
    And I create a user profile using the registration data
    Then the auth service should contain the user registration
    And the user service should contain the profile data
    And both services should have consistent user information

  @system @token_management
  Scenario: Authentication Token Management and User Data Sync
    Given I clear all system data
    When I register system user "token.user@reqres.in" with password "token123"
    And I encrypt and store the authentication token
    And I use the encrypted token to create user profile data
    Then the token should be properly encrypted in storage
    And I should be able to decrypt and validate the token
    And the user profile should be linked to the encrypted token

  @system @data_consistency
  Scenario: Multi-Service Data Consistency Validation
    Given I clear all system data
    When I create test data in auth service for user "consistency.user@reqres.in"
    And I create corresponding profile data in user service
    And I update the user information in both services
    Then both services should maintain data consistency
    And the data should be properly synchronized between services
    And no data corruption should occur during updates

  @system @error_handling
  Scenario: Error Handling Across Services
    Given I clear all system data
    When I attempt operations that will fail in auth service
    And I attempt operations that will fail in user service
    Then the auth service should handle errors gracefully
    And the user service should handle errors gracefully
    And no corrupted data should be stored in either service
    And the system should maintain data integrity

  @system @data_persistence
  Scenario: Data Persistence and Service Integration
    Given I clear all system data
    When I perform a complete user workflow:
      | step | service | action | data |
      | 1 | auth | register | user.persistence@reqres.in |
      | 2 | auth | encrypt_token | registration_token |
      | 3 | user | create_profile | user_profile_data |
      | 4 | user | update_profile | updated_profile_data |
      | 5 | auth | validate_token | encrypted_token |
    Then all workflow steps should complete successfully
    And data should persist correctly in both services
    And the services should maintain referential integrity

  @system @service_separation
  Scenario: Service Layer Separation and Token Security
    Given I clear all system data
    When I register user "separation.user@reqres.in" through auth facade
    And I create user profile through user facade
    And I verify token encryption through auth data service
    And I verify profile storage through user data service
    Then the auth facade should only handle authentication concerns
    And the user facade should only handle user management concerns
    And the data services should be properly separated
    And token security should be maintained throughout the process

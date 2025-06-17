# API Testing Framework - Test Documentation

## 🎯 Current Test Suite

### System Integration Tests (6 Tests) ✅
**Location**: `src/test/resources/features/system/system_integration_tests.feature`

1. **User Registration and Profile Creation Flow**
   - Multi-service interaction: Auth + User services
   - Tests registration → storage → profile creation → validation

2. **Authentication Token Management and User Data Sync**
   - Services: Auth + User + Token Encryption
   - Tests token encryption, decryption, and security

3. **Multi-Service Data Consistency Validation**
   - Services: Auth + User data services
   - Tests data synchronization and consistency

4. **Error Handling Across Services**
   - Services: Auth + User error handling
   - Tests graceful error handling and system integrity

5. **Data Persistence and Service Integration**
   - Services: Complete workflow integration
   - Tests end-to-end data persistence

6. **Service Layer Separation and Token Security**
   - Services: Facade + Data service separation
   - Tests architectural separation and security

## 🚀 Running Tests

### Run Only System Integration Tests (Recommended)
\`\`\`bash
mvn test -Dtest=SystemIntegrationTestRunner
\`\`\`

### Run Clean Test Suite
\`\`\`bash
mvn test -Dtest=CleanTestRunner
\`\`\`

## 📋 Test Requirements Met

✅ **6 System Test Cases**: Exactly 6 comprehensive system tests
✅ **Multi-Service Interaction**: Each test interacts with 2+ services
✅ **Process-Based Testing**: Tests complete business processes, not just API calls
✅ **Token Encryption**: Implemented and tested
✅ **Data Service Separation**: Auth and User data services separated
✅ **Standardized Context Keys**: Using TestContextKeys constants
✅ **Error Handling**: Graceful error handling across all services

## 🗂️ Archived Tests

The following test files have been archived as they were replaced by the comprehensive system integration tests:

- `auth/authentication.feature` - Replaced by system tests
- `user/user_management.feature` - Replaced by system tests  
- `integration/end_to_end.feature` - Replaced by system tests
- `system/complete_user_workflow.feature` - Replaced by system tests

## 🏗️ Architecture Demonstrated

- **Facade Pattern**: AuthFacade, UserFacade
- **Data Service Layer**: AuthDataService, UserDataService
- **Repository Pattern**: UserRepository, AuthRepository
- **Token Encryption**: Secure token storage and retrieval
- **Context Management**: Standardized test context keys
- **Error Handling**: Graceful degradation and error recovery

package com.api_testing.context;

public class ScenarioContext{
    private final TestContext testContext;

    public ScenarioContext() {
        this.testContext = new TestContext();
    }

    public TestContext getTestContext() {
        return testContext;
    }
}

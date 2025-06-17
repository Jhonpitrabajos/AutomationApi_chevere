package steps;

import com.api_testing.context.ScenarioContext;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private final ScenarioContext scenarioContext;

    public CucumberHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STARTING: " + scenario.getName());
        System.out.println("Feature: " + scenario.getUri().toString().replaceAll(".*features/", ""));
        System.out.println("Tags: " + scenario.getSourceTagNames());
        System.out.println("=".repeat(60));
        
        scenarioContext.getTestContext().set("scenarioName", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        String symbol = scenario.isFailed() ? "FAILED" : "PASSED";

        System.out.println("\n" + "-".repeat(60));
        System.out.println("RESULT: " + symbol + " - " + scenario.getName());
        System.out.println("STATUS: " + scenario.getStatus().toString());
        if (scenario.isFailed()) {
            System.out.println("ERROR: Check details above");
        }
        System.out.println("-".repeat(60) + "\n");
    }

    @Before("@movie")
    public void beforeMovieScenario(Scenario scenario) {
        System.out.println("Starting movie scenario: " + scenario.getName());
    }
}

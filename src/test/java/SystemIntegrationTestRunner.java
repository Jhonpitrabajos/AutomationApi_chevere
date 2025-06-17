import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.EXECUTION_DRY_RUN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("classpath:features")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty," +
                "rerun:target/reports/system-integration-rerun.txt," +
                "junit:target/reports/system-integration-cucumber.xml," +
                "json:target/reports/system-integration-cucumber.json," +
                "html:target/reports/system-integration-cucumber.html"
)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features/system/system_integration_tests.feature")
@ConfigurationParameter(key = "cucumber.object-factory", value = "io.cucumber.picocontainer.PicoFactory")
// Este runner SOLO incluye @system
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@system")
public class SystemIntegrationTestRunner {
    // Solo ejecuta tests con tag @system
    // Pero Maven puede encontrar otros tests y skipearlos
}

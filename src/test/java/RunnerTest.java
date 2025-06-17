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
                "rerun:target/reports/rerun.txt," +
                "junit:target/reports/cucumber.xml," +
                "json:target/reports/cucumber.json," +
                "html:target/reports/cucumber.html"
)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = "cucumber.object-factory", value = "io.cucumber.picocontainer.PicoFactory")
// Este runner EXCLUYE @system pero INCLUYE otros tests
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @otherProject and not @ignore and not @system")
public class RunnerTest {
    // Excluye @system, por eso cuando corres "mvn test" los system tests se skipean
    // PERO incluye DummyJsonTests y SimpleApiTest (que no son Cucumber)
}

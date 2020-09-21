package testSuite.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/features/SUT", // a file path to the root of all features
        glue = { // a list of package names, not directly related to file paths
                "testFramework", // the @Before and @After will not be run unless the package containing them is listed here
                "testSteps"
        },
        plugin = {
                "pretty",
                "html:target/stdReports",
                "json:target/cucumber.json",
                // see https://gitlab.com/monochromata-de/cucumber-reporting-plugin
                "de.monochromata.cucumber.report.PrettyReports:target/"
        }
        , tags = "@standards and not (@wip or @smoke)"
//        ,dryRun = true
)

public class TestRunner {
}
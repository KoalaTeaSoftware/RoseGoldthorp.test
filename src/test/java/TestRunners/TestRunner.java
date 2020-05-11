package TestRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/features", // a file path to the root of all features
        glue = { // a list of package names, not directly related to file paths
                "stepDefs", // as you may guess
                "framework" // the @Before and @After will not be run unless the package containing them is listed here
        },
        plugin = {
                "pretty",
                "html:target/stdReports",
                "json:target/cucumber.json",
                // see https://gitlab.com/monochromata-de/cucumber-reporting-plugin
                "de.monochromata.cucumber.report.PrettyReports:target/"
        }
        , tags = {"@functional"} // tags in one string: OR, in separate strings: AND
//        ,tags = {"@standards"} // tags in one string: OR, in separate strings: AND
//        ,dryRun = true
)

public class TestRunner {
}
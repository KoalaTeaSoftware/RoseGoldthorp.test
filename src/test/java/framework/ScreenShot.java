package framework;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Found this at https://www.softwaretestinghelp.com/page-object-model-pom-with-pagefactory/
 * and grabbed it while I was passing. Not yet used in anger
 */
public class ScreenShot {
    static WebDriver driver;

    // ToDo: set up the screen shot location in the test configuration file
    public static void embedScreenShot(Scenario scenario, String label) {
        driver = ContextOfScenario.getDriver();

        scenario.write(label);

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        // whilst this is complaining the embed is deprecated, it still works
        scenario.embed(screenshot, "image/png");
    }

    public static void writeScenarioMessage(String message) {
        ContextOfScenario.getScenario().write(message);
    }
}
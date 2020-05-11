package framework;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class ContextOfScenario {

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public static WebDriver getDriver() {
        return myDriver;
    }

    public static Scenario getScenario() {
        return myScenario;
    }

    // things to run before the start of every scenario. Starting the web driver is a favourite, but there might be other things
    @Before
    public void beforeScenario(Scenario scenario) throws NoSuchFieldException {
        ContextOfScenario.myScenario = scenario;
        driverManager = new DriverManager();
        myDriver = driverManager.getDriver();
    }

    // When the _scenario_ is complete.
    // Clearly, closeDriver is needed when the driver is created in the @Before
    // I have nothing do with providing the Scenario, but it is there
    @After
    public void afterScenario(Scenario scenario) {

        // this may seem a bit involved, but a direct use of getStatus().equals does not yield the hoped-for result
        if (!scenario.getStatus().name().equalsIgnoreCase("passed")) {

            ScreenShot.embedScreenShot(scenario, "Screenshot taken because this scenario is marked as " + scenario.getStatus().toString());
        }

        driverManager.closeDriver();
    }

    private static DriverManager driverManager;
    private static WebDriver myDriver;
    private static Scenario myScenario;
}
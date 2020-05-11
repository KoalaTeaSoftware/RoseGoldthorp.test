package framework;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    public DriverManager() throws NoSuchFieldException {
        driverType = ContextOfTest.testConfiguration.getProperty("browser");
        environmentType = ContextOfTest.testConfiguration.getProperty("environment");

        try {
            catchBrowserLogs = ContextOfTest.testConfiguration.getProperty("catchBrowserLogs");
        } catch (NoSuchFieldException e) {
            catchBrowserLogs = "";
        }
    }

    /**
     * Use this when a driver is needed
     *
     * @return the current automation driver
     * @throws NoSuchFieldException - possible exception when creating the driver
     */
    public WebDriver getDriver() throws NoSuchFieldException {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    /**
     * Use this instead of directly calling driver.get
     * It uses the ordinary driver get, but looks to see if the test context wants it to always
     * wait for the full load of the requested page.
     *
     * @param fullURL - of the page that you want to see. Can be relative
     */
    public void getPage(String fullURL) {
        driver.get(fullURL);

        try {
            awaitPageLoad(Integer.valueOf(ContextOfTest.testConfiguration.getProperty("waitForPageLoad")));
        } catch (NoSuchFieldException e) {
            // don't wait as it is not set in the properties file
        }
    }

    public static List<BrowserError> analyzeBrowserLog() {
        List<BrowserError> errorList = new ArrayList<BrowserError>();

        if (catchBrowserLogs.equalsIgnoreCase("true")) {
            // use the driver from the context as anything else gives you scope nightmares
            LogEntries logEntries = ContextOfScenario.getDriver().manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : logEntries) {
                // https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/logging/LogEntry.html
                errorList.add(new BrowserError(entry));

            }
        } else {
            Assert.fail("Set catchBrowserLogs=true in the test framework properties before you try to check them.");
        }
        return errorList;
    }

    private WebDriver createDriver() throws NoSuchFieldException {
        switch (environmentType) {
            case "local":
                driver = createLocalDriver();
                break;
            case "remote":
                driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() throws NoSuchFieldException {
        switch (driverType) {
            case "firefox":
                if (catchBrowserLogs.equalsIgnoreCase("true")) {
                    throw new IllegalArgumentException("Unfortunately, FF Selenium does not handle log files yet. Use Chrome.");
                }
                driver = new FirefoxDriver();
                break;
            case "chrome": {
                driver = DriverChrome.getDriver(catchBrowserLogs, ContextOfTest.testConfiguration.getProperty("chromeDriverPath"));
                break;
            }
            case "iexplorer":
                throw new io.cucumber.java.PendingException();
                //driver = new InternetExplorerDriver();
        }

        try {
            String implicitWait = ContextOfTest.testConfiguration.getProperty("implicitWaitTime");
            driver.manage().timeouts().implicitlyWait(Integer.valueOf(implicitWait), TimeUnit.SECONDS);
        } catch (NoSuchFieldException e) {
            // don't care, do nothing about the wait if it has not been specified
        }

        return driver;
    }

    /**
     * hide this down here because we want control of the existence of the driver
     */
    public void closeDriver() {
        driver.quit();
    }

    private void awaitPageLoad(int maxWaitSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < maxWaitSeconds; i++) {
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                return;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private WebDriver driver;
    private static String driverType;
    private static String environmentType;
    private static String catchBrowserLogs;
}

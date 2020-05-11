package framework;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Abstract the actual creation of the browser into its own class so that you can see more easily wat is going on
 */
public class DriverChrome {
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    /*
    If you leave the page load strategy NORMAL, then you can get lots of errors like this
    [1584601749.116][SEVERE]: Timed out receiving message from renderer: 0.100
    (which don't actually appear to be severe)
    If you set it to NONE (as in the following line),
    1) You don't get the errors
    2) the page's secondary stuff does not load before Selenium lets you go on
       so you will want to implement your own 'wait for load to complete' on the DriverManager's getPage
     */
    private static final PageLoadStrategy pls = PageLoadStrategy.NONE;

    public static WebDriver getDriver(String catchBrowserLogs, String path) {
        System.setProperty(CHROME_DRIVER_PROPERTY, path);
        ChromeOptions options = new ChromeOptions();
        // ChromeDriver is just AWFUL because every version or two it breaks unless you pass cryptic arguments
        options.setPageLoadStrategy(pls);
        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--headless"); // only if you are ACTUALLY running headless
//                options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
//                options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
//                options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
//                options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
//                options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
//                options.addArguments("enable-features=NetworkServiceInProcess");

        return new ChromeDriver(options);
    }
}

package framework.helpers;

import framework.ContextOfTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementHelpers {

    /**
     * @param myDriver     - probably get this from the test context
     * @param locator      - the thing used to locat the elementthat you will wait to see
     * @param milliSeconds - how long to wait
     */
    public static void waitForElementToBeDefined(WebDriver myDriver, By locator, long milliSeconds) throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofMillis(milliSeconds));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception TimeoutException) {
            ContextOfTest.actor.writeToHtmlReport("[Info] waited for " + milliSeconds + " milliseconds for the page title to be as expected");
            throw TimeoutException;
        }
    }
}

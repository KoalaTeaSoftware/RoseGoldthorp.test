package framework.helpers;

import framework.ContextOfTest;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementHelpers {

    /**
     * @param locator      - the thing used to locat the elementthat you will wait to see
     * @param milliSeconds - how long to wait
     */
    public static void waitForElementToBeDefined(By locator, long milliSeconds) throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(ContextOfTest.actor.getDriver(), Duration.ofMillis(milliSeconds));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception TimeoutException) {
            ContextOfTest.actor.writeToHtmlReport("[Info] waited for " + milliSeconds + " milliseconds for the page title to be as expected");
            throw TimeoutException;
        }
    }

    public static void waitForElementAttribute(WebElement webElement, String cssAttribute, String expectedValue, long milliSeconds)
            throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(ContextOfTest.actor.getDriver(), Duration.ofMillis(milliSeconds));
        try {
            wait.until(ExpectedConditions.attributeContains(webElement, cssAttribute, expectedValue));
        } catch (Exception TimeoutException) {
            String msg = String.format(
                    "[Info] waited for %d milliseconds for the attribute %s to be as %s",
                    milliSeconds, cssAttribute, expectedValue);
            ContextOfTest.actor.embedScreenShot(msg);
            throw TimeoutException;
        }
    }
}

package objects;

import framework.ContextOfTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver myDriver;

    public HomePage(WebDriver driver) {
        this.myDriver = driver;
//        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return myDriver.getTitle();
    }

    /**
     * Selenium does not provide a native 'wat for title to be defined' so use an expected value
     * As this is happening in the Page Object, do not make any assertions, but do make a note of it
     *
     * @param expectedTitle - what you expect it to be
     * @param seconds       - maximum number of seconds you are willing to wait
     */
    public void waitForPageTitleToBecome(String expectedTitle, int seconds) {
        // using this approach as it is simple to code
        // it would introduce a potentially long wait when the title does not match expectations
        // however, the page could be built with a title that is dynamically set, so maybe it is worth the wait anyway

        WebDriverWait wait = new WebDriverWait(myDriver, Duration.ofSeconds(seconds));
        try {
            wait.until(ExpectedConditions.titleIs(expectedTitle));
        } catch (Exception TimeoutException) {
            ContextOfTest.actor.writeToHtmlReport("[Info] waited for " + seconds + " second for the page title to be as expected");
        }
    }
}

package testFramework.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Context;
import testFramework.actors.Actor;

public class HtmlPage {
    public String readPageTitle() {
        return myDriver.getTitle();
    }

    public String readCurrentUrl() {
        return myDriver.getCurrentUrl();
    }

    public WebElement getBodyTag() {
        return bodyTag;
    }

    public HtmlPage(WebDriver driver) {
        this.myDriver = driver;

        waitForJavaScriptReadyStateComplete(Context.pageLoadWait);
        bodyTag = waitForElement(driver, By.tagName("BODY"), Context.pageLoadWait);

        // this would be where you would initialise the page factory
        // PageFactory.initElements(driver, this);
    }

    public static WebElement waitForElement(WebDriver myDriver, By givenBy, long waitFor) {
        // https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/WebDriverWait.html#WebDriverWait-org.openqa.selenium.WebDriver-long-
        // the above link does not indicate that this particular call is deprecated
        //noinspection deprecation
        return new WebDriverWait(myDriver, waitFor)
                // use the 'presence', i.e. is the element actually in the DOM - it may not be visible
                .until(ExpectedConditions.presenceOfElementLocated(givenBy));
    }


    /**
     * When looking at web pages, the implicit wait may not be sufficient,
     * so explicitly ask the browser if it thinks it has got everything
     * <p>
     * Even this may be less help than you like, so it may be good to override it in your own page object definitions with with
     * an explicit wait for the visibility of something that you know is slow
     * for example
     * <p>
     * WebDriverWait wait = new WebDriverWait(driver, 10);
     * WebElement elem = driver.findElement(By.id("diagnosticElement"));
     * wait.until(ExpectedConditions.visibilityOf(elem));
     *
     * @param maxWaitSeconds -
     */
    public void waitForJavaScriptReadyStateComplete(int maxWaitSeconds) {
        if (maxWaitSeconds == 0)
            return; // don't even create the executor

        String state = "";
        JavascriptExecutor js = (JavascriptExecutor) Context.defaultActor.getDriver();

        if (js != null)
            for (int i = 0; i < maxWaitSeconds; i++) {
                try {
                    state = js.executeScript("return document.readyState").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                    Actor.writeToHtmlReport("Failed to get document state " + e.getMessage());
                    // Right at the beginning, if the browser has got nothing yet.
                    // we may hit "org.openqa.selenium.JavascriptException: javascript error: Cannot read property 'outerHTML' of null"
                    // In this case, we do not stop waiting.
                }
                if (state.equals("complete")) {
                    return;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                        // don't care
                    }
                }
            }
        else {
            System.out.println("[warning] unable to execute JavaScript to determine if page has loaded");
            Actor.writeToHtmlReport("[warning] unable to execute JavaScript to determine if page has loaded");
        }
    }

    private final WebDriver myDriver;
    private final WebElement bodyTag;
}

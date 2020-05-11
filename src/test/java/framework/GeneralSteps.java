package framework;

import com.jcabi.w3c.ValidationResponse;
import com.jcabi.w3c.ValidatorBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GeneralSteps {
    @Given("I am on the {string} page")
    public void i_am_on_the_page(String pageName) {
        String fullURL = ContextOfTest.sutUrl;

        if (pageName.length() > 0) {
            fullURL += '/' + pageName;
        }

        ContextOfScenario.getDriverManager().getPage(fullURL);
    }

    @Then("the browser produces no messages")
    public void the_browser_produces_no_messages() {
        List<BrowserError> errorList = DriverManager.analyzeBrowserLog();

        if (!errorList.isEmpty()) {
            ScreenShot.writeScenarioMessage("The browser detected some errors");
            for (BrowserError b : errorList) {
                ScreenShot.writeScenarioMessage(b.toString());
            }
            Assert.assertTrue(errorList.isEmpty());
        }
    }

    @Then("the HTML syntax of that page is valid")
    public void theHTMLSyntaxOfThatPageIsValid() {
        ValidationResponse response = null;
        // unless you stick this in before anything, you will always get an error with a message
        // Start tag seen without seeing a doctype first. Expected
        String sourceHTML = "";
        sourceHTML = "<!doctype html>\n";
        sourceHTML += ContextOfScenario.getDriver().getPageSource();
        try {
            response = new ValidatorBuilder().html().validate(sourceHTML);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("HTML Validation was not possible: " + e.getLocalizedMessage() + ":");
        }
        Assert.assertTrue(
                String.format(
                        "The validator detected %d errors and %d warnings.\nSee https://koalatea-software.com/testing-html-css-syntax/",
                        response.errors().size(),
                        response.warnings().size()
                ),
                response.valid()
        );
    }

    @And("the syntax of CSS linked into in that page is valid")
    public void theSyntaxOfCSSLinkedIntoInThatPageIsValid() {
        /*
        ToDo: expand this so that it also looks at any CSS linked in using the style tag
         */
        WebDriver driver = ContextOfScenario.getDriver();
        List<WebElement> links = driver.findElements(By.tagName("link"));
        boolean stepFails = false;

        for (WebElement link : links) {
            if (link.getAttribute("rel").equalsIgnoreCase("stylesheet")) {
                ValidationResponse response = null;
                String href = link.getAttribute("href");
                try {
                    String sheet = UtilsFile.URLReader(new URL(href));
                    response = new ValidatorBuilder().html().validate(sheet);
                } catch (IOException e) {
                    stepFails = true;
                    e.printStackTrace();
                    ScreenShot.writeScenarioMessage(
                            String.format(
                                    "CSS Validation of the file:%s: was not possible:%s:\n",
                                    href,
                                    e.getLocalizedMessage()
                            )
                    );
                }
                if (!response.valid()) {
                    stepFails = true;
                    ScreenShot.writeScenarioMessage(
                            String.format(
                                    "CSS Validation of the linked file:%s: failed. Errors: :%d, Warnings: %d\n",
                                    href,
                                    response.errors().size(),
                                    response.warnings().size()
                            )
                    );
                }
            }
        }
        Assert.assertFalse("Validation of CSS fails", stepFails);
    }

}

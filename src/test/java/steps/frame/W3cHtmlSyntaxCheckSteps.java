package steps.frame;


import helpers.Urls;
import helpers.W3cHtmlChecker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;


public class W3cHtmlSyntaxCheckSteps {
    final Duration tout = Duration.ofSeconds(40);
    private W3cHtmlChecker w3cHtmlValidator = null;
    private String url;

    @Given("the w3C HTML tester reviews the file {string}")
    public void theW3CHTMLTesterReviewsTheFile(String urlOfFile) {
        url = Urls.interpretURL(urlOfFile);
        try {
            w3cHtmlValidator = new W3cHtmlChecker(url, tout);
        } catch (TimeoutException e) {
            Assert.fail("Failed to find results from:" + url + ": in " + tout + " " +
                    "seconds");
        }

    }

    @Then("the w3c HTML tester reports compliance")
    public void theW3CHTMLTesterReportsCompliance() {
        Assert.assertTrue(url + ": (unescaped) should be syntactically correct", w3cHtmlValidator.fileValidates());
    }
}

package steps.frame;


import helpers.Urls;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import helpers.W3cCssChecker;

import java.time.Duration;


public class W3cCssSyntaxCheckSteps {
    final Duration tout = Duration.ofSeconds(25);
    private W3cCssChecker w3cCssValidator = null;
    private String url;

    @Given("the w3C CSS tester reviews the file {string}")
    public void theWCCSSTesterReviewsTheFile(String urlOfFile) {
        this.url = Urls.interpretURL(urlOfFile);
        try {
            w3cCssValidator = new W3cCssChecker(url, tout);
        } catch (TimeoutException e) {
            Assert.fail("Failed to find results from:" + url + ": in " + tout + " seconds");
        }

    }

    @Then("the w3c CSS tester reports compliance")
    public void theWCCSSTesterReportsCompliance() {
        Assert.assertTrue("File :" + url + ": should be syntactically acceptable", w3cCssValidator.fileValidates());
    }
}

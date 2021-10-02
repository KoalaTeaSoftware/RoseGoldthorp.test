package steps.frame;


import helpers.Urls;
import helpers.W3cLinkChecker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import java.text.MessageFormat;
import java.time.Duration;

public class W3cPageLinkCheck {
    final Duration tout = Duration.ofSeconds(120); // the link checker can be quite slow

    private W3cLinkChecker w3cLinkChecker;
    private String url;

    @Given("the w3c link checker reviews the file {string}")
    public void theW3CLinkCheckerReviewsTheFile(String urlOfFile) {
        url = Urls.interpretURL(urlOfFile);
        try {
            w3cLinkChecker = new W3cLinkChecker(url, tout);
        } catch (TimeoutException e) {
            Assert.fail(MessageFormat.format("Failed to find results from:{0}: in {1} seconds", url, tout));
        }
    }

    @Then("the w3c link checker reports compliance")
    public void theW3CLinkCheckerReportsCompliance() {
        Assert.assertTrue("Links on page :" + url + ": appear broken", w3cLinkChecker.fileValidates());
    }
}

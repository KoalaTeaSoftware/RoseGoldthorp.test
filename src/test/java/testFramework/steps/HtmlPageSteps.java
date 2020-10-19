package testFramework.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testFramework.Context;
import testFramework.objects.HtmlPageObject;

import java.net.MalformedURLException;

/**
 * Steps that all HTML pages can support
 */
public class HtmlPageSteps {
    @Given("I navigate to the page {string}")
    public void iNavigateToThePage(String fullUrl) {
        String s = testFramework.helpers.urls.interpretURL(fullUrl);
        Assert.assertNotNull("Unable to make the URL work", s);
        Context.defaultActor.getResource(s);
    }

    @Then("the page title is {string}")
    public void thePageTitleIs(String expected) {
        Assert.assertEquals("The page title is not as expected", expected, getMyPage().readPageTitle());
    }

    @And("the first heading is {string}")
    public void theFirstHeadingIs(String expected) {
        Assert.assertEquals("Unexpected H1", expected, getMyPage().readFirstHeader());
    }

    @And("the first heading does not contain {string}")
    public void theFirstHeadingDoesNotContain(String needle) {
        boolean haystackDoesContainNeedle = getMyPage().readFirstHeader().contains(needle);

        Assert.assertFalse(
                "The first header " + page.readFirstHeader() + ":should not contain :" + needle + ":",
                haystackDoesContainNeedle);
    }

    @And("the page scheme is {string}")
    public void thePageSchemeIs(String expectedScheme) {
        try {
            if (expectedScheme.isEmpty()) expectedScheme = Context.sutConfiguration.getProperty("defaultScheme");

            Assert.assertEquals("Unexpected scheme for the current page",
                    expectedScheme.replaceAll("[:/]", ""),
                    getMyPage().readLocation().getProtocol()
            );
        } catch (NoSuchFieldException e) {
            Assert.fail("Please define a default scheme, in the SUT configuration file");
        } catch (MalformedURLException e) {
            Assert.fail("Current location can't be determined:" + e.getMessage() + ": ");
        }
    }

    private HtmlPageObject page;

    private HtmlPageObject getMyPage() {
        if (page == null)
            page = new HtmlPageObject(Context.defaultDriver);
        return page;
    }
}

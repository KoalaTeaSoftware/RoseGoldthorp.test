package testSteps;

import framework.ContextOfScenario;
import io.cucumber.java.en.Then;
import objects.HomePage;
import org.junit.Assert;


public class SiteSmoke {
    @Then("The page title is {string}")
    public void thePageTitleIs(String expectedTitle) {

        HomePage homePage = new HomePage(ContextOfScenario.driver);

        String pageTitle = homePage.getPageTitle();

        // the site has a slow load (because some of the page is driven from WP - this should be made asynchronous
        homePage.waitForPageTitleToBecome(expectedTitle, 15);

        Assert.assertEquals("The page title is not as expected", expectedTitle, pageTitle);
    }
}

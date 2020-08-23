package testSteps;

import framework.ContextOfScenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import objects.ui.pages.AllPages;
import org.junit.Assert;


public class SiteSmoke {
    @Then("The page title is {string}")
    public void thePageTitleIs(String expectedTitle) {

        AllPages page = new AllPages(ContextOfScenario.driver);

        System.out.println("In the actual test, the page title is :" + page.title + ":");

        Assert.assertEquals("The page title is not as expected", expectedTitle, page.title);
    }

    @Then("I see the banner section")
    public void iSeeTheBannerSection() {
        AllPages page = new AllPages(ContextOfScenario.driver);

        Assert.assertNotNull(page.furniture);
    }

    @And("I see the body section")
    public void iSeeTheBodySection() {
        AllPages page = new AllPages(ContextOfScenario.driver);

        Assert.assertNotNull(page.main);
    }

    @And("I see the footer section")
    public void iSeeTheFooterSection() {
        AllPages page = new AllPages(ContextOfScenario.driver);

        Assert.assertNotNull(page.footer);
    }
}

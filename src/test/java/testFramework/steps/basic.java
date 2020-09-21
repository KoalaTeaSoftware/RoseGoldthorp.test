package testFramework.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import testFramework.Context;
import testFramework.actors.Actor;
import testFramework.objects.HtmlPage;

public class basic {

    @Given("I write to the html report {string}")
    public void iWriteToTheHtmlReport(String arg0) {
        System.out.println(arg0);
        Actor.writeToHtmlReport(arg0);
    }

    @Given("I fail a test")
    public void iFailATest() {
        Assert.fail("A deliberately failing test");
    }

    @Given("I navigate to the page {string}")
    public void iNavigateToThePage(String fullUrl) {
        Context.defaultActor.getResource(fullUrl);
    }

    @Then("the page title is {string}")
    public void thePageTitleIs(String expected) {
        HtmlPage page = new HtmlPage(Context.driver);

        Assert.assertEquals("The page title is not as expected", expected, page.readPageTitle());
    }
}

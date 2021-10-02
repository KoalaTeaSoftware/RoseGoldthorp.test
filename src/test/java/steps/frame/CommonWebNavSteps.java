package steps.frame;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objects.frame.Context;
import objects.frame.webPages.CommonWebNavBar;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class CommonWebNavSteps {

    private CommonWebNavBar myNavBar = null;

    private CommonWebNavBar getMyBar() {
        if (myNavBar == null)
            myNavBar = new CommonWebNavBar(Context.defaultBrowser.getDriver());
        return myNavBar;
    }


    @Then("the number of nav items is {int}")
    public void theNumberOfNavItemsIs(int expectedCount) {
        Assert.assertEquals(expectedCount, getMyBar().getNavItemCount());
    }


    @When("I click on the nav link with text {string}")
    public void iClickOnTheNavLinksWithText(String navText) {
        WebElement link = getMyBar().getNavItem(navText);
        Assert.assertNotNull(link);
        link.click();
    }
}

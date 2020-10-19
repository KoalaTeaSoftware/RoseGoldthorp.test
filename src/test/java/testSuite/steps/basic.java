package testSuite.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import testFramework.Context;
import testSuite.objects.PageObjectRoseGoldthorpSite;

public class basic {
    @Given("the page is fully drawn")
    public void thePageIsFullyDrawn() {
        // the constructor for this sort of object will wait until it thinks that the page is complete
        new PageObjectRoseGoldthorpSite(Context.defaultDriver);
    }

    @When("I click on the nav link with text {string}")
    public void iClickOnTheNavLinkWithText(String linkText) {
        PageObjectRoseGoldthorpSite pageRoseGoldthorpSite = new PageObjectRoseGoldthorpSite(Context.defaultDriver);
        WebElement linkElement = pageRoseGoldthorpSite.getNavItem(linkText);

        Assert.assertTrue("The '" + linkText + "' link should be visible and enabled",
                linkElement.isDisplayed() && linkElement.isEnabled()
        );
        linkElement.click();
    }
}

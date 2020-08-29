package testSteps;

import framework.ContextOfScenario;
import framework.ContextOfTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objects.ui.pages.AllPages;
import objects.ui.widgets.SiteNavWidget;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class Navigation {

    @Given("I am on the {string} page")
    public void i_am_on_the_page(String string) {
        ContextOfScenario.driver.get(ContextOfTest.sutBaseURL + "/" + string);
        //noinspection unused
        AllPages page = new AllPages(); // make it wait for the page to be drawn
    }

    @Then("I follow nav links and see the correct page")
    public void iFollowNavLinksAndSeeTheCorrectPage(DataTable dt) {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);

        SiteNavWidget navWidget = new SiteNavWidget();

        SoftAssert softAssert = new SoftAssert();
        String lastPlace = "";
        for (Map<String, String> stringStringMap : list) {
            String linkText = stringStringMap.get("linkText");
            String expectedPage = stringStringMap.get("expectedPageTitle");

            WebElement navItem = navWidget.findNavButton(linkText);

            softAssert.assertNotNull(
                    navItem,
                    String.format("A nav item with text :%s: should be present", linkText)
            );

            navItem.click();

            AllPages newPage = new AllPages();

            softAssert.assertEquals(newPage.pageTitle, expectedPage, "Unexpected title of page");
            if (lastPlace.length() > 0) {
                /*
                To test all the links on the page suggested by the scenario outline, use the back button
                Otherwise, wherever you start, to go to the home and then test the next page, then from that to the next, and so on.
                First time around, however, there will be no place to go back to
                 */
                ContextOfTest.actor.getDriver().navigate().back();
            }
            lastPlace = linkText;
        }
        softAssert.assertAll("Step Fails");
    }

    @And("there are {int} items visible on the menu")
    public void thereAreItemsVisibleOnTheMenu(int expectedNumberOfNavItems) {
        SiteNavWidget navWidget = new SiteNavWidget();

        Assert.assertEquals("Unexpected number of menu items", expectedNumberOfNavItems, navWidget.countNavButtons());
    }
}

package testSteps;

import framework.ContextOfTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objects.ui.pages.AboutPage;
import objects.ui.pages.FilmDetailsPage;
import objects.ui.pages.PricingPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Chapters {

    @Then("the pricing page shows more than {int} packages")
    public void thePricingPageShowsMoreThanPackages(int minNumPackages) {
        PricingPage page = new PricingPage();

        Assert.assertTrue(
                String.format("There should be %d packages, there are %d", minNumPackages, page.packageCount()),
                (minNumPackages <= page.packageCount())
        );
    }

    @Then("the about page shows the following roles")
    public void theAboutPageShowsTheFollowingRoles(DataTable dataTable) {
        List<String> expectedRoleList = dataTable.asList(String.class);
        AboutPage aboutPage = new AboutPage();
        SoftAssert softAssert = new SoftAssert();

        for (String expectedRole : expectedRoleList) {
            softAssert.assertNotNull(aboutPage.findRoleNavByName(expectedRole), "Expected to find role " + expectedRole + ":");
        }
        softAssert.assertAll();
    }

    @And("the about page shows only {int} roles")
    public void theAboutPageShowsOnlyRoles(int expectedRoleCount) {
        AboutPage aboutPage = new AboutPage();
        Assert.assertEquals("Unexpected number of roles", expectedRoleCount, aboutPage.numberOfRoles());
    }

    @When("I select the {string} role")
    public void iSelectTheRole(String sectionName) {
        AboutPage aboutPage = new AboutPage();
        aboutPage.showRole(sectionName);
    }

    @Then("the about page shows more than {int} films on release")
    public void theAboutPageShowsMoreThanFilmsOnRelease(int minNumberOnRelease) {
        AboutPage aboutPage = new AboutPage();
        int numbOnReleaseFilmsFound = aboutPage.countFilmsOnRelease();
        Assert.assertTrue(
                String.format("Expecting at least %d films on release. There were %d", minNumberOnRelease, numbOnReleaseFilmsFound),
                numbOnReleaseFilmsFound > minNumberOnRelease
        );
    }

    @And("all links to film details arrive at a good page")
    public void allLinksToFilmDetailsArriveAtAGoodPage() {
        /*
        This is referring to links on the about > film maker page
        Each of these links will be like this /about/films/the-rocky-road-to-freedom, i.e. the name of film toLower and s/ /_/
         */
        SoftAssert softAssert = new SoftAssert();
        AboutPage aboutPage = new AboutPage();

        List<WebElement> listOfLinks = aboutPage.findAllOnReleaseTextualLinks();
        int len = listOfLinks.size();

        for (int i = 0; i < len; i++) {
            WebElement link = listOfLinks.get(i);
            String expectedTitle = link.getText();
            link.click();

            FilmDetailsPage filmDetailsPage = new FilmDetailsPage();
            softAssert.assertEquals(filmDetailsPage.pageTitle, expectedTitle);
            softAssert.assertEquals(filmDetailsPage.filmTitle, expectedTitle);

            ContextOfTest.actor.getDriver().navigate().back();
            ContextOfTest.actor.waitForBrowser(5000);
            aboutPage.refresh();
            listOfLinks = aboutPage.findAllOnReleaseTextualLinks();
        }
        softAssert.assertAll("One of the textual links on About > Film Maker > On Release did not land on a page with the expected title");
    }
}

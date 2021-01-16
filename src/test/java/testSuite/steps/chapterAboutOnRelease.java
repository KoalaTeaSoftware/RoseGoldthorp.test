package testSuite.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import testFramework.Context;
import testSuite.objects.PageObjectAboutOnRelease;
import testSuite.objects.PageObjectFilmDetails;

import java.util.List;

public class chapterAboutOnRelease {
    private PageObjectAboutOnRelease pageAboutOnRelease;

    /**
     * Lazy instantiation of the handle on the page
     * This will (generally speaking) allow the instantiation to occur once for each scenario
     *
     * @return - the instance of the page being looked at
     */
    private PageObjectAboutOnRelease getMe() {
        if (pageAboutOnRelease == null) {
            refreshMe();
        }
        return pageAboutOnRelease;
    }

    private void refreshMe() {
        pageAboutOnRelease = new PageObjectAboutOnRelease(Context.defaultDriver);
    }


    @And("there is more than {int} listed film")
    public void thereIsMoreThanListedFilm(int minNumListings) {
        int actualCount = getMe().getFilmListItems().size();

        Assert.assertTrue(
                String.format("There are %d listings there should be at least %d", actualCount, minNumListings),
                actualCount > minNumListings);
    }

    @And("all thumbnails are populated")
    public void allThumbnailsArePopulated() {
        // the data driving sets the alt and src attributes. Of course, the src attribute, could fail to get a picture
        SoftAssert sa = new SoftAssert();
        int idx = 0;

        for (WebElement el : getMe().getFilmThumbnails()) {
            sa.assertTrue(
                    el.getAttribute("alt").length() > 1,
                    String.format("The alt attribute for film number %d is suspiciously short. Value :%s:",
                            idx, el.getAttribute("alt")
                    )
            );

            sa.assertTrue(
                    getMe().browserShowsImage(el),
                    String.format("The browser dislikes the image at index (%d)", idx)
            );
            idx++;
        }
        sa.assertAll();
    }

    @And("all titles are populated")
    public void allTitlesArePopulated() {
        SoftAssert sa = new SoftAssert();
        int idx = 0;

        for (WebElement el : getMe().getFilmTitles()) {
            String visibleText = el.getAttribute("innerText");
            sa.assertTrue(
                    visibleText.length() > 1,
                    String.format("The title attribute for film number %d is suspiciously short. Value :%s:",
                            idx, visibleText
                    )
            );
            idx++;
        }
        sa.assertAll();
    }

    @And("all puff paragraphs are populated")
    public void allPuffParagraphsArePopulated() {
        SoftAssert sa = new SoftAssert();
        int idx = 0;

        for (WebElement el : getMe().getFilmPuffs()) {
            String visibleText = el.getAttribute("innerText");
            sa.assertTrue(
                    visibleText.length() > 20,
                    String.format("The puff paragraph for film number %d is suspiciously short. Value :%s:",
                            idx, visibleText
                    )
            );
            idx++;
        }
        sa.assertAll();
    }

    @And("the numbers of thumbnails, titles and puffs are consistent")
    public void theNumbersOfThumbnailsTitlesAndPuffsAreConsistent() {
        Assert.assertTrue(
                String.format(
                        "Title count: %d, Thumbnail count: %d, Puff count: %d. These numbers should all the equal",
                        getMe().getFilmTitles().size(), getMe().getFilmThumbnails().size(),
                        getMe().getFilmPuffs().size()
                ),
                (getMe().getFilmTitles().size() == getMe().getFilmThumbnails().size()) &&
                        (getMe().getFilmTitles().size() == getMe().getFilmPuffs().size())
        );
    }

    @When("I follow each of the links to film details the page has the expected title")
    public void iFollowEachOfTheLinksToFilmDetailsThePageHasTheExpectedTitle() {

        SoftAssert sa = new SoftAssert();

        List<WebElement> titles = getMe().getFilmTitles();

        for (int idx = 0; idx < titles.size(); idx++) {
            WebElement el = titles.get(idx);
            String expectedTitle = el.getText();

            el.click();

            PageObjectFilmDetails pageFilmDetails = new PageObjectFilmDetails(Context.defaultDriver);

            sa.assertEquals(expectedTitle, pageFilmDetails.readPageTitle(), "Unexpected page title");

            Context.defaultDriver.navigate().back();

            // prevent stale elements by refreshing everything
            refreshMe(); // this will invoke the various wait-for-the-job-to-complete that is built into the page object
            titles = getMe().getFilmTitles();
        }
        sa.assertAll();
    }
}

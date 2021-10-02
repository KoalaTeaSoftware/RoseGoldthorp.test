package steps.sut;

import helpers.SoftAssert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import objects.frame.Context;
import objects.sut.PageObjectAboutOnRelease;
import objects.sut.PageObjectFilmDetails;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;


public class ChapterOnRelease {
    private PageObjectAboutOnRelease onRelease;

    /**
     * Lazy instantiation of the handle on the page
     * This will (generally speaking) allow the instantiation to occur once for each scenario
     *
     * @return - the instance of the page being looked at
     */
    private PageObjectAboutOnRelease getMe() {
        if (onRelease == null) {
            onRelease = new PageObjectAboutOnRelease(Context.defaultBrowser.getDriver());
        }
        return onRelease;
    }


    @And("there is more than {int} listed film(s)")
    public void thereIsMoreThanListedFilm(int minNumListings) {
        int actualCount = getMe().getFilmListItems().size();

        Assert.assertTrue(
                String.format("There are %d listings there should be at least %d", actualCount, minNumListings),
                actualCount > minNumListings);
    }

    @And("all thumbnails are shown")
    public void allThumbnailsAreShown() {
        SoftAssert sa = new SoftAssert("OnRelThumbs");
        int idx = 0;

        for (WebElement el : getMe().getFilmThumbnails()) {
            sa.assertTrue(
                    el.getAttribute("alt").length() > 1,
                    String.format("The alt attribute for film number %d is suspiciously short. Value :%s:",
                            idx, el.getAttribute("alt")
                    )
            );
            // the link checker (part of the framework) will also verify that the src attribute points to an actual file
            sa.assertTrue(
                    getMe().browserShowsImage(el),
                    String.format("The src attribute (%s) appears to be bad", el.getAttribute("src")
                    )
            );
            idx++;
        }
        sa.assertAll();
    }

    @And("all titles are populated")
    public void allTitlesArePopulated() {
        SoftAssert sa = new SoftAssert("OnRelTits");
        int idx = 0;

        for (WebElement el : getMe().getFilmTitles()) {
            sa.assertTrue(
                    el.getText().length() > 1,
                    String.format("The title attribute for film number %d is suspiciously short. Value :%s:",
                            idx, el.getText()
                    )
            );
            idx++;
        }
        sa.assertAll();
    }

    @And("all puff paragraphs are populated")
    public void allPuffParagraphsArePopulated() {
        SoftAssert sa = new SoftAssert("OnRelPuff");
        int idx = 0;

        for (WebElement el : getMe().getFilmPuffs()) {
            sa.assertTrue(
                    el.getText().length() > 20,
                    String.format("The puff paragraph for film number %d is suspiciously short. Value :%s:",
                            idx, el.getText()
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
                        getMe().getFilmTitles().size(),
                        getMe().getFilmThumbnails().size(),
                        getMe().getFilmPuffs().size()
                ),
                (
                        getMe().getFilmTitles().size() == getMe().getFilmThumbnails().size())
                        &&
                        (getMe().getFilmTitles().size() == getMe().getFilmPuffs().size()
                        )
        );
    }

    @And("all thumbnails are populated")
    public void allThumbnailsArePopulated() {
        // the data driving sets the alt and src attributes. Of course, the src attribute, could fail to get a picture
        SoftAssert sa = new SoftAssert("Thumbs");
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

    @When("following all of the links to film details produce a page with the expected title")
    public void followingAllOfTheLinksToFilmDetailsProduceAPageWithTheExpectedTitle() {

        SoftAssert sa = new SoftAssert("LinkTitles");

        List<WebElement> filmTitles = getMe().getFilmTitles();

        for (int idx = 0; idx < filmTitles.size(); idx++) {
            WebElement el = filmTitles.get(idx);
            // The text of the link gives the expected title
            String expectedTitle = el.getText();

            el.click();
            // this should give us a film details page, so we will need a temporary instance of this page object
            PageObjectFilmDetails pageFilmDetails = new PageObjectFilmDetails(Context.defaultBrowser.getDriver());
            sa.assertEquals(expectedTitle, pageFilmDetails.readPageTitle(), "Unexpected page title");
            // back to the page that we came from
            Context.defaultBrowser.getDriver().navigate().back();
            // prevent stale elements by refreshing everything
            onRelease = new PageObjectAboutOnRelease(Context.defaultBrowser.getDriver());
            filmTitles = getMe().getFilmTitles();
        }
        sa.assertAll();
    }

}

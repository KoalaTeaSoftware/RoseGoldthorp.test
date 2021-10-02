package steps.sut;

import io.cucumber.java.en.And;
import objects.frame.Context;
import objects.sut.PageObjectFilmDetails;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SectionFilmDetails {
    @And("the poster is populated")
    public void thePosterIsPopulated() {
        Assert.assertTrue("The poster appears faulty", getMyPage().browserShowsImage(getMyPage().getPoster()));
    }

    @And("the description is populated")
    public void theDescriptionIsPopulated() {
        WebElement desc = getMyPage().getDescriptionText();
        List<WebElement> list = desc.findElements(By.tagName("P"));

        Assert.assertTrue("Expected 1, or more paragraphs of description, got " + list.size(),
                list.size() >= 1);
    }

    @And("the dia {string} populated")
    public void theDiaPopulated(String isOrIsNot) {
        if (isOrIsNot.equalsIgnoreCase("is")) {
            Assert.assertTrue("Expected there to be a Director In Action section",
                    getMyPage().getDiaText().length() > 1);
        } else if (isOrIsNot.equalsIgnoreCase("isnot")) {
            try {
                @SuppressWarnings("unused") String diaText = getMyPage().getDiaText();
            } catch (NoSuchElementException e) {
                return;
            }
            Assert.fail("Expected there to be NO Director In Action section");
        } else {
            Assert.fail("Expected the parameter to be either\"is\", or \"isnot\"");
        }

        // assume that the link-check will have check
    }

    @And("there are {int} links")
    public void thereAreLinks(int numbLinks) {
        Assert.assertEquals("Unexpected number of links", numbLinks, getMyPage().getLinks().size());
    }

    // expect that the single page will be used by all of the various steps, so just make one and reuse it
    private PageObjectFilmDetails pageFilmDetails;

    PageObjectFilmDetails getMyPage() {
        if (pageFilmDetails == null)
            pageFilmDetails = new PageObjectFilmDetails(Context.defaultBrowser.getDriver());
        return pageFilmDetails;
    }
}

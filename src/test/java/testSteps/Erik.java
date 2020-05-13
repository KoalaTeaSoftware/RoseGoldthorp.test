package testSteps;

import framework.ContextOfScenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import objects.ui.pages.StoriesPage;
import org.junit.Assert;

public class Erik {

    @Then("the Erik cartoon shows {int} images")
    public void the_Erik_cartoon_shows_images(Integer numberOfImages) {
        StoriesPage page = new StoriesPage(ContextOfScenario.driver);

        Assert.assertEquals(
                "The number of Erik images is incorrect",
                numberOfImages,
                (Integer) page.comicStripImgList.size());
    }

    @And("each Erik image is well formed")
    public void each_Erik_image_is_well_formed() {
        StoriesPage page = new StoriesPage(ContextOfScenario.driver);

        Assert.assertEquals(
                "There should be no errors detected",
                "", page.opinionOfComicStripImages()
        );
    }
}


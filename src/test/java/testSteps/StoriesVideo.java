package testSteps;

import framework.ContextOfScenario;
import io.cucumber.java.en.Then;
import objects.ui.pages.StoriesPage;
import org.junit.Assert;

public class StoriesVideo {
    @Then("the stories video is is well formed")
    public void theStoriesVideoIsIsWellFormed() {
        StoriesPage page = new StoriesPage(ContextOfScenario.driver);

        Assert.assertTrue("The src attribute of the stories video should not be blank",
                page.storiesVideoEmbed.getAttribute("src").length() > 0);
    }

    @Then("the stories text is is well formed")
    public void theStoriesTextIsIsWellFormed() {
        StoriesPage page = new StoriesPage(ContextOfScenario.driver);

        Assert.assertTrue("The text associated with the stories video should not be blank",
                page.storiesVideoText.getText().length() > 0);
    }
}

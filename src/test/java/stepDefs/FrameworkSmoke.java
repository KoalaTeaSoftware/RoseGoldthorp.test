package stepDefs;

import framework.ContextOfScenario;
import framework.ContextOfTest;
import io.cucumber.java.en.Given;
import objects.ui.pages.HomePage;

public class FrameworkSmoke {

    @Given("^I say \"([^\"]*)\"$")
    public void iSay(String arg0) throws InterruptedException {

        System.out.println("started step " + arg0);

        // go the home page indicated in the configuration
        ContextOfScenario.getDriver().get(ContextOfTest.sutUrl);

        HomePage homePage = new HomePage(ContextOfScenario.getDriver());

        System.out.printf("Page has title :%s:\n", homePage.getPageTitle());

        Thread.sleep(500);
    }
}

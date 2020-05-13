package testSteps;

import framework.ContextOfScenario;
import framework.ContextOfTest;
import io.cucumber.java.en.Given;
import objects.ui.pages.HomePage;

public class FrameworkSmoke {

    @Given("^I say \"([^\"]*)\"$")
    public void iSay(String arg0) throws InterruptedException {

        System.out.println("started step " + arg0);

        // go the home page indicated in the configuration
        ContextOfScenario.driver.get(ContextOfTest.sutBaseURL);

        HomePage homePage = new HomePage(ContextOfScenario.driver);

        System.out.printf("Page has title :%s:\n", homePage.getPageTitle());

        Thread.sleep(500);
    }
}

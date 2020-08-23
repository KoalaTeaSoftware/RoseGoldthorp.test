package testSteps;

import framework.ContextOfScenario;
import framework.ContextOfTest;
import io.cucumber.java.en.Given;

public class Navigation {

    @Given("I am on the {string} page")
    public void i_am_on_the_page(String string) {
        ContextOfScenario.driver.get(ContextOfTest.sutBaseURL + "/" + string);
    }
}

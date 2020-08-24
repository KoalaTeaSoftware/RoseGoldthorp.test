package testSteps;

import io.cucumber.java.en.Then;
import objects.ui.pages.PricingPage;
import org.junit.Assert;

public class Chapters {

    @Then("There are more than {int} packages visible")
    public void thereAreMoreThanPackagesVisible(int minNumPackages) {
        PricingPage page = new PricingPage();

        Assert.assertTrue(
                String.format("There should be %d packages, there are %d", minNumPackages, page.packageCount()),
                (minNumPackages >= page.packageCount())
        );
    }
}

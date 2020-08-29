package objects.ui.pages;

import framework.ContextOfScenario;
import framework.ContextOfTest;
import framework.helpers.WebElementHelpers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

public class AllPages {

    public final String pageTitle;
    public final WebElement furniture;
    public final WebElement main;
    public final WebElement footer;

    public AllPages() {
        final By furnitureLocator = By.xpath("/html/body/section[@id='furniture']");
        final By mainLocator = By.xpath("/html/body/section[@id='page-content']");
        final By footerLocator = By.xpath("/html/body/section[@id='footer']");

        try {
            ContextOfTest.actor.waitForBrowser(5000);
        } catch (TimeoutException e) {
            Assert.fail("The page did not fully load (Browser never said 'ready' in 5000ms");
        }
        try {
            WebElementHelpers.waitForElementToBeDefined(footerLocator, 500);
        } catch (TimeoutException e) {
            Assert.fail("The page did not fully load (unable to find element using :" + footerLocator + ":");
        }

        pageTitle = ContextOfScenario.driver.getTitle();
        furniture = ContextOfScenario.driver.findElement(furnitureLocator);
        main = ContextOfScenario.driver.findElement(mainLocator);
        footer = ContextOfScenario.driver.findElement(footerLocator);

    }
}

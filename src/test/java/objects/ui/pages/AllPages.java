package objects.ui.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@SuppressWarnings("FieldCanBeLocal")
public class AllPages {
    private final By furnitureLocator = By.xpath("/html/body/section[@id='furniture']");
    private final By mainLocator = By.xpath("/html/body/section[@id='page-content']");
    private final By footerLocator = By.xpath("/html/body/section[@id='footer']");

    public final String title;
    public final WebElement furniture;
    public final WebElement main;
    public final WebElement footer;

    private final WebDriver myDriver;

    public AllPages(WebDriver d) {
        myDriver = d;
        try {
            framework.helpers.WebElement.waitForElementToBeDefined(d, footerLocator, 5);
        } catch (TimeoutException e) {
            Assert.fail("The page did not fully load (unable to find element using :" + footerLocator + ":");
        }

        title = myDriver.getTitle();
        furniture = myDriver.findElement(furnitureLocator);
        main = myDriver.findElement(mainLocator);
        footer = myDriver.findElement(footerLocator);
    }
}

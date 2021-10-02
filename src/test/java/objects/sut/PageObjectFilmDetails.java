package objects.sut;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObjectFilmDetails extends PageObjectRoseGoldthorpSite {
    final By header = By.tagName("H2");
    final By poster = By.id("poster");
    final By description = By.id("description"); // this is a div containing P tags
    final By links = By.xpath("//*[@id='listOfLinks']//A"); // this is a div containing A tags, a varying number thereof
    final By diaText = By.id("vidHeader"); // again, an item that might, or might not be present, for each different film

    // --Commented out by Inspection START (16/10/2020 17:46):
    //    public String  getHeaderText() {
    //        return myDriver.findElement(header).getAttribute("innerHTML");
    //    }
    // --Commented out by Inspection STOP (16/10/2020 17:46)

    public WebElement getPoster() {
        return myDriver.findElement(poster);
    }

    public WebElement getDescriptionText() {
        return myDriver.findElement(description);
    }

    public List<WebElement> getLinks() {
        return myDriver.findElements(links);
    }

    public String getDiaText() {
        return myDriver.findElement(diaText).getText();
    }

    /**
     * @param driver - asks for this in case you have a driver other than the default driver
     */
    public PageObjectFilmDetails(WebDriver driver) {
        super(driver);
    }
}

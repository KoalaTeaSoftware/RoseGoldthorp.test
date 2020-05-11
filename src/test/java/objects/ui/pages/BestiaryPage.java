package objects.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BestiaryPage {
    private WebDriver myDriver;

    private BestiaryPage(WebDriver driver) {
        myDriver = driver;

        PageFactory.initElements(myDriver, this);
    }

    @FindBy(how = How.TAG_NAME, using = "h2")
    List<WebElement> allPostTiles;

    public String getEntryTitle(WebElement handle) {
        return handle.getText();
    }
}

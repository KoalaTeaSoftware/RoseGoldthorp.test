package objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver myDriver;

    public HomePage(WebDriver driver) {
        System.out.println("Iniitalising the home page object");
        this.myDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return myDriver.getTitle();
    }

//    @FindBy(how = How.TAG_NAME, using = "title")
//    private WebElement pageTitle;
}

package objects.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver myDriver;

    public HomePage(WebDriver driver) {
        System.out.println("Initalising the home page object");
        this.myDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return myDriver.getTitle();
    }
}

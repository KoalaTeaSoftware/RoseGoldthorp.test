package objects.ui.widgets;

import framework.ContextOfScenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SiteNavWidget {

    public WebElement findNavButton(String navText) {
        return ContextOfScenario.driver.findElement(By.linkText(navText));
    }
}

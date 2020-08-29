package objects.ui.widgets;

import framework.ContextOfScenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SiteNavWidget {

    public WebElement findNavButton(String navText) {
        WebElement mainNav = ContextOfScenario.driver.findElement(By.id("mainNav"));
        return mainNav.findElement(By.linkText(navText));
    }

    public int countNavButtons() {
        WebElement mainNav = ContextOfScenario.driver.findElement(By.id("mainNav"));
        return mainNav.findElements(By.className("nav-link")).size();
    }
}

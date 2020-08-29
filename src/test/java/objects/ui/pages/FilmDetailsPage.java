package objects.ui.pages;

import framework.ContextOfScenario;
import org.openqa.selenium.By;

public class FilmDetailsPage extends AllPages {
    public FilmDetailsPage() {
        super();
        filmTitle = ContextOfScenario.driver.findElement(By.id("filmTitle")).getText();
    }

    public String filmTitle;
}

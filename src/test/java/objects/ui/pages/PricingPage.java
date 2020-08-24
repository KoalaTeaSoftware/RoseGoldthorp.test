package objects.ui.pages;

import framework.ContextOfScenario;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PricingPage extends AllPages {

    public PricingPage() {
        super();
        PageFactory.initElements(ContextOfScenario.driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "pricingPackage")
    List<WebElement> packageList;

    public int packageCount() {
        return packageList.size();
    }
}

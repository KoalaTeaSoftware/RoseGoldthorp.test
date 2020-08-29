package objects.ui.pages;

import framework.ContextOfScenario;
import framework.ContextOfTest;
import framework.helpers.WebElementHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AboutPage extends AllPages {
    public AboutPage() {
        super();
        refresh();
    }

    private WebElement rolesNavHolder;
    private WebElement rolesContentHolder;

    public void refresh() {
        rolesNavHolder = ContextOfScenario.driver.findElement(By.id("rolesNav"));
        rolesContentHolder = ContextOfScenario.driver.findElement(By.id("myTabContent"));
    }

    public int numberOfRoles() {
        return rolesNavHolder.findElements(By.className("nav-link")).size();
    }

    public WebElement findRoleNavByName(String roleName) {
        return rolesNavHolder.findElement(By.linkText(roleName));
    }

    public void showRole(String roleName) {
        findRoleNavByName(roleName).click();
        WebElementHelpers.waitForElementAttribute(findContentByRoleName(roleName), "class", "active", 500);
        ContextOfTest.actor.waitForBrowser(50);
    }

    // this is assuming that the tab content blocks are all named after the role
    public WebElement findContentByRoleName(String roleName) {
        String lookFor = roleName.toLowerCase().replaceAll("\\s+", "");
        return rolesContentHolder.findElement(By.id(lookFor));
    }

    public int countFilmsOnRelease() {
        return rolesContentHolder.findElements(By.cssSelector(".filmListItem")).size();
    }

    public List<WebElement> findAllOnReleaseTextualLinks() {
        return rolesContentHolder.findElements(By.className("textLinkToFilmDetails"));
    }

    public List<WebElement> findAllOnReleaseImgLinks() {
        return rolesContentHolder.findElements(By.className("imgLinkToFilmDetails"));
    }
}

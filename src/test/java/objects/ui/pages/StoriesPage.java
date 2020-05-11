package objects.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StoriesPage {
    private WebDriver myDriver;

    public StoriesPage(WebDriver driver) {
        System.out.println("Initalising the home page object");
        this.myDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return myDriver.getTitle();
    }

    // all imgs in the carousel
    //#comicCaro > div > div.carousel-item.active.align-content-center > img
    @FindBy(how = How.CSS, using = "#comicStrip img")
    public List<WebElement> comicStripImgList;

    @FindBy(how = How.CSS, using = "#storyBoardVid")
    public WebElement storiesVideoEmbed;

    @FindBy(how = How.CSS, using = "#storyBoardText")
    public WebElement storiesVideoText;

    /**
     * Determining if the images are well formed is delegated to this object so as to increase the
     * separation between the test logic and the understanding of the page structure.
     * <p>
     * At the moment, these images are provided by the CMS, by a call to an XML feed which is parsed
     * Looking at a small handful of attributes should give a good feeling for success, or failure
     *
     * @return - empty string if no fault found, else opinions
     */
    public String opinionOfComicStripImages() {

        StringBuilder msg = new StringBuilder();

        final int len = comicStripImgList.size();

        for (int idx = 0; idx < len; idx++) {
            WebElement thisImg = comicStripImgList.get(idx);

            if (thisImg.getAttribute("src").length() == 0) {
                msg.append("\nErik image number ").append(idx).append(" should have some text in its 'src' attribute");
            }
            if (thisImg.getAttribute("srcset").length() == 0) {
                msg.append("\nErik image number ").append(idx).append(" should have some text in its 'srcset' attribute");
            }
            // the alt tag, although an accessibility criterion, is derived via the CMS, so is a functional criterion too.
            if (thisImg.getAttribute("alt").length() == 0) {
                msg.append("\nErik image number ").append(idx).append(" should have some text in its 'alt' attribute");
            }
        }

        return msg.toString();
    }
}

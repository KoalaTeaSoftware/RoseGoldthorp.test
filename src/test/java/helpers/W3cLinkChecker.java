package helpers;

import objects.frame.Context;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helpers.Reports.writeToHtmlReport;

public class W3cLinkChecker {

    /**
     * It is best to aim this directly at the single files that you create.
     * For example, Bootstrap's css invokes error messages (false negatives?) from this tester.
     *
     * @param urlOfCssFile - make it a single file.Scheme is not necessary
     */
    public W3cLinkChecker(String urlOfCssFile, Duration tout) throws TimeoutException {
        String sut = "https://validator.w3.org/checklink?uri=";
        sut += urlOfCssFile;
        sut += "&summary=on&hide_type=all&depth=&check=Check";

        Context.defaultBrowser.getResource(sut);

        new WebDriverWait(Context.defaultBrowser.getDriver(), tout).until(ExpectedConditions.presenceOfElementLocated(By.tagName("H3")));
    }

    /**
     * @return - whether it contains text that indicates success, or failure
     */
    public Boolean fileValidates() {
        // the first h3 tells you the result
        if (Context.defaultBrowser.getDriver().findElement(By.tagName("h3")).getText().toLowerCase().contains("broken links")) {
            // it definitely says there is a problem
            writeToHtmlReport("Found evidence of broken links (an H3 saying just that)");
            writeToHtmlReport(Context.defaultBrowser.getDriver().findElement(By.xpath("//dl[@class='report']")).getAttribute("innerHTML"));
            return false;
        }
        // otherwise, hunt for the p that specifically indicates success
        for (WebElement p : Context.defaultBrowser.getDriver().findElements(By.tagName("p"))) {
            if (p.getText().equalsIgnoreCase("Valid links!"))
                return true;
        }
        // failing anything good, default to failure
        writeToHtmlReport("Failed to find evidence of valid links (a P saying just that)");
        return false;
    }
}
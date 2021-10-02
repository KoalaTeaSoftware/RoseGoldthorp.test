package objects.frame.browsers;

public class BrowserFactory {
    public static Browser make(BrowserType type) {
        if (type == BrowserType.CHROME) {
            return new ChromeBrowser();
        }
        System.out.println("[ERROR] Can't create an actor of type " + type + ":");
        return null;
    }
}

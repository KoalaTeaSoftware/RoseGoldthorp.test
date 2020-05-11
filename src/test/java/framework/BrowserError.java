package framework;

import org.openqa.selenium.logging.LogEntry;

import java.util.Date;

public class BrowserError {
    public String level;
    public Date timeStamp;
    public String message;

    public BrowserError(LogEntry jsEntry) {
        this.level = jsEntry.getLevel().getName();
        this.timeStamp = new Date(jsEntry.getTimestamp());
        this.message = jsEntry.getMessage();
    }

    public String toString() {
        return String.format("Level:%s\nTimestamp:%s\nMessage:%s", level, timeStamp.toString(), message);
    }
}

package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        OPTIONS_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        X_BUTTON = "id:Back";
        SYNC_YOUR_SAVED_DATA_POP_UP = "id:Sync your saved articles?";
        CLOSE_SYNC_YOUR_SAVED_DATA_POP_UP = "id:places auth close";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        ARTICLE_TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_BUTTON = "css:#page-actions #ca-watch[class*='mw-ui-icon-wikimedia-star'][role='button']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions #ca-watch.watched[class*='mw-ui-icon-wikimedia-unStar'][role='button']";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

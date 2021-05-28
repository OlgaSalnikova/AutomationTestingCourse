package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsObject extends MyListsPageObject {

    static
    {
        ARTICLE_BY_TITLE_TPL = "xpath://*[starts-with(@class,'page-summary')]//h3[contains(text(),'{ARTICLE_TITLE}')]";
        ARTICLES_LIST = "xpath://ul[contains(@class,'content-unstyled')]/li";
        REMOVE_FROM_SAVED_LIST_BUTTON_TPL = "xpath://*[starts-with(@class,'page-summary')][.//h3[contains(text(),'{ARTICLE_TITLE}')]]/*[contains(@class,'watched')]";
        REMAINED_SECOND_ARTICLE_TITLE = "css:ul.page-list>li.page-summary";
        JAVA_SCRIPT_TEXT = "xpath://b[contains(text(),'Java')]";
    }
    public MWMyListsObject(RemoteWebDriver driver) {
        super(driver);
    }
}

package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static
    {
       ARTICLE_BY_Title_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
       ARTICLES_LIST = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell";
       REMAINED_SECOND_ARTICLE_TITLE = "xpath://XCUIElementTypeLink[1]";
       JAVA_SCRIPT_TEXT ="id:JavaScript";

    }
    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}

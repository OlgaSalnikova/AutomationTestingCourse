package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static
    {
        FOLDERS_LIST = "id:org.wikipedia:id/item_container";
        FOLDER_NAME = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/item_title']";
        ARTICLES_LIST = "xpath://android.widget.ScrollView";
        ARTICLE_BY_Title_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}

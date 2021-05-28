package lib.ui.android;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static
    {
        FOLDERS_LIST = "id:org.wikipedia:id/item_container";
        FOLDER_NAME = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/item_title']";
        ARTICLES_LIST = "xpath://android.widget.ScrollView";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}

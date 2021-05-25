package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        OPTIONS_BUTTON = "xpath://*[@class='android.widget.ImageView'][@content-desc='More options']";
        MENU_WITH_OPTIONS = "xpath://android.widget.ListView";
        ADD_TO_READING_LIST_OPTION = "xpath://*[@text='Add to reading list']";
        GOT_IT_TIP_OVERLAY = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/onboarding_button']";
        INPUT_FIELD_TO_SAVE_ARTICLE_NAME = "id:org.wikipedia:id/text_input";
        ARTICLE_NAME = "xpath://About Poland";
        OK_BUTTON = "xpath://*[@text='OK']";
        X_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}

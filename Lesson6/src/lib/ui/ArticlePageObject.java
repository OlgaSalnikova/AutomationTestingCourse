package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
            ARTICLE_TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://*[@class='android.widget.ImageView'][@content-desc='More options']",
            MENU_WITH_OPTIONS = "xpath://android.widget.ListView",
            ADD_TO_READING_LIST_OPTION = "xpath://*[@text='Add to reading list']",
            GOT_IT_TIP_OVERLAY = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/onboarding_button']",
            INPUT_FIELD_TO_SAVE_ARTICLE_NAME = "id:org.wikipedia:id/text_input",
            ARTICLE_NAME = "xpath:About Poland",
            OK_BUTTON = "xpath://*[@text='OK']",
            X_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForArticleTitle(){
        return this.waitForElementPresent(ARTICLE_TITLE, "Can not find article title on page", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForArticleTitle();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT, "can not find the end of article",20);
    }

    public void addArticleToMyList() {
        this.waitForElementAndClick
        (
             OPTIONS_BUTTON,
             "Can not find button to open article options",
             5
        );


        this.waitForElementVisible
        (
             MENU_WITH_OPTIONS,
             "Can not find menu with options",
             15
        );

        this.waitForElementAndClick
                (
                        ADD_TO_READING_LIST_OPTION,
                        "Can not find option to add article to Reading List",
                        5
                );

        this.waitForElementAndClick
                (
                        GOT_IT_TIP_OVERLAY,
                        "Can not find Got It tip overlay",
                        5
                );

        this.waitForElementAndClear
                (
                        INPUT_FIELD_TO_SAVE_ARTICLE_NAME,
                        "Can not find input to save name of articles folder",
                        5
                );

        this.waitElementAndSendKeys
                (
                        INPUT_FIELD_TO_SAVE_ARTICLE_NAME,
                        ARTICLE_NAME,
                        "Can not put text to articles folder input",
                        5
                );

       this.waitForElementAndClick
                (
                        OK_BUTTON,
                        "Can not find Ok button",
                        5
                );
    }

    public void closeArticle(){
        this.waitForElementAndClick
        (
                X_BUTTON,
                "Can not find X link",
                5
        );
    }
}

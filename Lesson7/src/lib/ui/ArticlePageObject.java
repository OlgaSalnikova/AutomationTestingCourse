package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            FIRST_ARTICLE_TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            MENU_WITH_OPTIONS,
            ADD_TO_READING_LIST_OPTION,
            GOT_IT_TIP_OVERLAY,
            INPUT_FIELD_TO_SAVE_ARTICLE_NAME,
            ARTICLE_NAME ,
            OK_BUTTON,
            X_BUTTON,
            SYNC_YOUR_SAVED_DATA_POP_UP,
            CLOSE_SYNC_YOUR_SAVED_DATA_POP_UP;


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }



    public WebElement waitForArticleTitle(){

        return this.waitForElementPresent( FIRST_ARTICLE_TITLE, "Can not find article title on page", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        }else{
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "can not find the end of article", 40);
        }else{
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,"can not find the end of article", 80);
        }
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

    public void addArticlesToMySaved(){
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find option to save Article to Reading List",
                5);
    }

    public void closeSyncYourSavedArticles(){
        this.waitForElementVisible
                (
                        SYNC_YOUR_SAVED_DATA_POP_UP,
                        "Can not display Sync your saved articles? pop up",
                        15
                );

        this.waitForElementPresent
                (
                        SYNC_YOUR_SAVED_DATA_POP_UP,
                        "Can not find Sync your saved articles? pop up",
                        5)
                ;
        this.waitForElementAndClick
                (
                        CLOSE_SYNC_YOUR_SAVED_DATA_POP_UP,
                        "Can not find and click close button of the Sync your saved articles? pop up",
                        10
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

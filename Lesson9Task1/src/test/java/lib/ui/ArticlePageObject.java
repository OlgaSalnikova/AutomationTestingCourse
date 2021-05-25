package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            ARTICLE_TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            MENU_WITH_OPTIONS,
            ADD_TO_READING_LIST_OPTION,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            GOT_IT_TIP_OVERLAY,
            INPUT_FIELD_TO_SAVE_ARTICLE_NAME,
            ARTICLE_NAME,
            OK_BUTTON,
            X_BUTTON,
            SYNC_YOUR_SAVED_DATA_POP_UP,
            CLOSE_SYNC_YOUR_SAVED_DATA_POP_UP;


    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForArticleTitle() {
        return this.waitForElementPresent(ARTICLE_TITLE, "Can not find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "can not find the end of article",
                    100);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "can not find the end of article",
                    100);
        } else {
            this.scrollElementTillNotVisible(
                    FOOTER_ELEMENT,
                    "can not find the end of Article",
                    100
            );
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

    public void addArticlesToMySaved() {

        if (Platform.getInstance().isMW()) {
            this.removeArticlesToMySavedIfItAdded();
            this.waitForElementClickableAndClick
            (
                    OPTIONS_BUTTON,
                    "Can not find option to save Article to Reading List",
                    5
            );
        }else {
            this.waitForElementClickableAndClick(
                    OPTIONS_BUTTON,
                    "Can not find option to save Article to Reading List",
                    5
            );
        }
    }



    public void removeArticlesToMySavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementClickableAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Can not click button to remove article from saved",
                    5
            );
            this.waitForElementVisible(
                    OPTIONS_BUTTON,
                    "Can not find button to add article",
                    5
            );
        }
    }

    public void closeSyncYourSavedArticles() {
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

    public void closeArticle() {
            this.waitForElementAndClick
                    (
                            X_BUTTON,
                            "Can not find X link",
                            5
                    );
    }
}

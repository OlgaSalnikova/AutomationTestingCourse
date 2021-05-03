package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

private static final String

            ARTICLE_TITLE = "org.wikipedia:id/view_page_title_text",
            OPTIONS_BUTTON = "//*[@class='android.widget.ImageView'][@content-desc='More options']",
            MENU_WITH_OPTIONS = "//android.widget.ListView",
            ADD_TO_READING_LIST_OPTION = "//*[@text='Add to reading list']",
            GOT_IT_TIP_OVERLAY = "//android.widget.TextView[@resource-id='org.wikipedia:id/onboarding_button']",
            INPUT_FIELD_TO_SAVE_ARTICLE_NAME = "org.wikipedia:id/text_input",
            EXISTED_FOLDER = "//android.support.v7.widget.RecyclerView[@resource-id='org.wikipedia:id/list_of_lists']",
            ARTICLE_NAME = "About Poland",
            OK_BUTTON = "//*[@text='OK']",
            X_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForArticleTitle(){
        return this.waitForElementPresent(By.id(ARTICLE_TITLE), "Can not find article title on page", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForArticleTitle();
        return title_element.getAttribute("text").trim();
    }

    public void addArticleToMyListWhenOverlayAppear() {
        this.waitForElementAndClick
        (
             By.xpath(OPTIONS_BUTTON),
             "Can not find button to open article options",
             5
        );

        this.waitForElementVisible
        (
             By.xpath(MENU_WITH_OPTIONS),
             "Can not find menu with options",
             15
        );

        this.waitForElementAndClick
                (
                        By.xpath(ADD_TO_READING_LIST_OPTION),
                        "Can not find option to add article to Reading List",
                        5
                );

        this.waitForElementAndClick
                (
                        By.xpath(GOT_IT_TIP_OVERLAY),
                        "Can not find Got It tip overlay",
                        5
                );

        this.waitForElementAndClear
                (
                        By.id(INPUT_FIELD_TO_SAVE_ARTICLE_NAME),
                        "Can not find input to save name of articles folder",
                        5
                );

        this.waitElementAndSendKeys
                (
                        By.id(INPUT_FIELD_TO_SAVE_ARTICLE_NAME),
                        ARTICLE_NAME,
                        "Can not put text to articles folder input",
                        5
                );

       this.waitForElementAndClick
                (
                        By.xpath(OK_BUTTON),
                        "Can not find Ok button",
                        5
                );
    }

    public void addArticleToMyListWhenOverlayAbsent() {
        this.waitForElementAndClick
                (
                        By.xpath(OPTIONS_BUTTON),
                        "Can not find button to open article options",
                        5
                );

        this.waitForElementVisible
                (
                        By.xpath(MENU_WITH_OPTIONS),
                        "Can not find menu with options",
                        15
                );

        this.waitForElementAndClick
                (
                        By.xpath(ADD_TO_READING_LIST_OPTION),
                        "Can not find option to add article to Reading List",
                        5
                );

        this.waitForElementAndClick
                (
                        By.xpath(EXISTED_FOLDER),
                        "Can not find the existed folder",
                        5
                );
    }

    public void closeArticle(){
        this.waitForElementAndClick
        (
                By.xpath(X_BUTTON),
                "Can not find X link",
                5
        );
    }

    public void elementTitlePresent(){
        this.assertElementPresent
        (
             By.id(ARTICLE_TITLE),
             "Can not find article with Poland in title");
        }
}

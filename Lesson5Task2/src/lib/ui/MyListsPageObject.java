package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{

    private static final String
        FOLDERS_LIST = "org.wikipedia:id/item_container",
        FOLDER_NAME = "//android.widget.TextView[@resource-id='org.wikipedia:id/item_title']",
        ARTICLES_LIST = "//android.widget.ScrollView",
        ARTICLE_BY_Title_TPL = "//*[@text='{TITLE}']";

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* Template Methods */
    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_Title_TPL.replace("{TITLE}",article_title);
    }
    /* Template Methods */

    public void openFolderByName(){
        this.waitForElementVisible
                (
                        By.id(FOLDERS_LIST),
                        "Can not find folder list",
                        15
                );

        this.waitForElementAndClick
                (
                        By.xpath(FOLDER_NAME),
                        "Can not find created folder",
                        15
                );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent
                (
                        By.xpath(article_xpath),
                        "Cannot find saved article by title " + article_title,
                        15
                );
    }
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent
        (
              By.xpath(article_xpath),
              "Saved article still present with title " + article_title,
              15
        );
    }

    public void swipeArticleToDelete(String article_title){
        this.waitForElementVisible
        (
             By.xpath(ARTICLES_LIST),
             "Can not find Articles List",
             15
        );
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft
        (
              By.xpath(article_xpath),
              "Can not find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }
}

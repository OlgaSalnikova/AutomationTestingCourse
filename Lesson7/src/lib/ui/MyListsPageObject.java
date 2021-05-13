package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;

abstract public class MyListsPageObject extends MainPageObject{

    protected static  String
        FOLDERS_LIST,
        FOLDER_NAME,
        ARTICLES_LIST,
        REMAINED_SECOND_ARTICLE_TITLE,
        JAVA_SCRIPT_TEXT,
        ARTICLE_BY_Title_TPL;

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
                        FOLDERS_LIST,
                        "Can not find folder list",
                        15
                );

        this.waitForElementAndClick
                (
                        FOLDER_NAME,
                        "Can not find created folder",
                        15
                );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent
                (
                        article_xpath,
                        "Cannot find saved article by title " + article_title,
                        15
                );
    }
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent
        (
              article_xpath,
              "Saved article still present with title " + article_title,
              15
        );
    }

    public void swipeArticleToDelete(String article_title){

        this.waitForElementVisible
        (
                ARTICLES_LIST,
               "Can not find Articles List",
               15
        );

        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft
        (
              article_xpath,
              "Can not find saved article"
        );
        if(Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath,"Can not find saved article");

        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void checkSecondArticleRemainOnPage(){

        int amountOfSearchResult = this.getAmountOfElements(ARTICLES_LIST);
        this.waitForElementAndClick(REMAINED_SECOND_ARTICLE_TITLE, "Can not find second remained article", 5);
        String actual_remained_article = this.waitForElementAndGetAttribute(JAVA_SCRIPT_TEXT, "label","can not find text inside article", 5);
        String expected_text_inside_article = "JavaScript";

     Assert.assertTrue("can not find expected remained second article", ((amountOfSearchResult == 1) && ((actual_remained_article).equals(expected_text_inside_article))));

    }
}

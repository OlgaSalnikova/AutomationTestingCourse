package lib.ui;

import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static  String
        FOLDERS_LIST,
        FOLDER_NAME,
        ARTICLES_LIST,
        ARTICLE_BY_TITLE_TPL,
        REMOVE_FROM_SAVED_LIST_BUTTON_TPL,
        REMAINED_SECOND_ARTICLE_TITLE,
        JAVA_SCRIPT_TEXT;


    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* Template Methods */
    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{ARTICLE_TITLE}",article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_LIST_BUTTON_TPL.replace("{ARTICLE_TITLE}", article_title);
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
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        if (Platform.getInstance().isMW()) {
            long finish = System.currentTimeMillis() + 5000; // end time
            while (isElementPresent(article_xpath) && (System.currentTimeMillis() < finish)) {
                driver.navigate().refresh();
            }
        } else {
            this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
        }
    }

    public void clickByArticleWithTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementClickableAndClick(article_xpath, "Can not find and click article" + article_title, 5);
    }

    public void swipeArticleToDelete(String article_title) throws InterruptedException {

        this.waitForElementVisible(ARTICLES_LIST, "Can not find Articles List", 15);

        this.waitForArticleToAppearByTitle(article_title);

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if((Platform.getInstance().isIOS())||(Platform.getInstance().isAndroid())){
            this.swipeElementToLeft
                    (
                            article_xpath,
                            "Can not find saved article"
                    );
        }else{
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementClickableAndClick(
                    remove_locator,
                    "Can not click button to remove from saved",
                    10
            );
        }

        if(Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath,"Can not find saved article");
        }

        this.waitForArticleToDisappearByTitle(article_title);

    }

    public void checkAmountOfArticlesOnPageBeforeSaving() {

        int amountOfSearchResult = this.getAmountOfElements(ARTICLES_LIST);

            Assert.assertTrue("less than 2 articles on page", amountOfSearchResult == 2);

    }

    public void checkAmountOfArticlesOnPageAfterSaving() {

        int amountOfSearchResult = this.getAmountOfElements(ARTICLES_LIST);
            Assert.assertTrue("more than 1 article on page", amountOfSearchResult == 1);
    }

    public void checkArticleByTextInside(){
        this.waitForElementPresent(JAVA_SCRIPT_TEXT,"text not found",5);
    }

}

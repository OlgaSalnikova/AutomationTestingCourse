package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_SUBSTRING_TPL,
            SEARCH_RESULT_CONTAINER,
            EMPTY_RESULT_LABEL;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
    /* Template Methods */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* Template Methods */

    @Step("Find search field and tap on it")
    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Can not find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Can not find and click search init element",5);
    }

    @Step("Tap '{search_line}' on search field")
    public void typeSearchLine(String search_line)
    {
        this.waitElementAndSendKeys(SEARCH_INPUT,search_line,"Can not find and type into search input",5);
    }

    @Step("Wait Cancel button appear")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Can not find cancel button",5);
    }

    @Step("Tap on Cancel button")
    public void clickCancelSearchButton()
    {
        this.waitForElementClickableAndClick(SEARCH_CANCEL_BUTTON, "Can not find and click search cancel button",5);
    }

    @Step(" Wait Cancel button disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still displayed",5);
    }

    @Step("Wait search result")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Can not find search result with substring" + search_result_xpath);
    }

    @Step("Click on Article by substring")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Can not find and click search result with substring" + search_result_xpath,5);
    }

    @Step("Get amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent
        (
            SEARCH_RESULT_CONTAINER,
            "Can not find anything by the request",
            15
        );
        return this.getAmountOfElements(SEARCH_RESULT_CONTAINER);
    }

    @Step("Wait empty search result")
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                EMPTY_RESULT_LABEL,
                "Can not find empty request label after search ",
                15
        );
    }
    @Step("Check no search result")
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                SEARCH_RESULT_CONTAINER,
                "We suppose not to find any results"
        );
    }
}

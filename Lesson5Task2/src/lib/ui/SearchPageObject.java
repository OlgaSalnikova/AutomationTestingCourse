package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container'][2]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* Template Methods */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* Template Methods */

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Can not find and click search init element",5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Can not find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line)
    {
        this.waitElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line,"Can not find and type into search input",5);
    }

    public void waitForSearchResult()
    {

        this.waitForElementPresent(By.xpath(SEARCH_RESULT), "Can not find sesearch result with several articles" );
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Can not find cancel button",5);
    }

    public void clickCancelSearchButton()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Can not find and click search cancel button",5);
    }

    public void waitForSearchResultToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_RESULT), "Search Results are still displayed",5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Can not find and click search result with substring" + search_result_xpath,5);
    }
}

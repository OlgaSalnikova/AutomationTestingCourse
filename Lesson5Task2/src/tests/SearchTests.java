package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testCancelSearchResult() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Poland");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.waitForSearchResult();
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.waitForSearchResultToDisappear();
    }

    @Test
    public void testCheckByTitleAndDescription(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Sky");
        SearchPageObject.waitForSearchResult();
        SearchPageObject.waitForElementByTitleAndDescription("Sky", "Everything that is above the surface of the Earth");
        SearchPageObject.waitForElementByTitleAndDescription("Skylab", "Space station launched and operated by NASA");
        SearchPageObject.waitForElementByTitleAndDescription("Skyfall", "2012 James Bond film by Sam Mendes");
    }
}

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
}

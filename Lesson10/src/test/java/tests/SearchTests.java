package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Search and wait search result")
    @Description("Search by considered word and wait search result")
    @Step("Staring testSearch test")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check not empty search result")
    @Description("Initiate Search and check  not empty search result")
    @Step("Staring testAmountOfNotEmptySearch test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResult = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("We found too few results", amountOfSearchResult > 0);
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Check empty search result")
    @Description("Initiate Search and check empty search result")
    @Step("Staring testAmountOfEmptySearch test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "ijijijijhgtygvvgvgvgvggcb";
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}

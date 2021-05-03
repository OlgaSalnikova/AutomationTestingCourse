package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCheckArticleTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Poland");
        SearchPageObject.clickByArticleWithSubstring("Poland");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.elementTitlePresent();
    }
}

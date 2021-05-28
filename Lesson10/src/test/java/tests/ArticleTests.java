package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Compared article title with expected one")
    @Description("Get title of the article and compare with string")
    @Step("Staring testCompareArticleTitle test")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String getArticleTitle = ArticlePageObject.getArticleTitle();

       // ArticlePageObject.takeScreenshot("article_page");

        Assert.assertEquals(
                "we see unexpected title",
                "Java (programming language)",
                getArticleTitle
        );
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Swipe Article to the end od the screen")
    @Description("Open article and swipe article to footer")
    @Step("Staring testSwipeArticle test")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.swipeToFooter();
    }
}

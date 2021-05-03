package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testDeleteSecondArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Poland");
        SearchPageObject.clickByArticleWithSubstring("Poland");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForArticleTitle();
        String article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToMyListWhenOverlayAppear();
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Poland");
        SearchPageObject.clickByArticleWithSubstring("Poland national football team");

        ArticlePageObject.waitForArticleTitle();
        ArticlePageObject.addArticleToMyListWhenOverlayAbsent();
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName();
        MyListsPageObject.swipeArticleToDelete(article_title);
    }
}

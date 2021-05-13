package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveArticlesToMyList(){

        //Add first article
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitle();
        String article_title = ArticlePageObject.getArticleTitle().trim();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList();
        }else{
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeSyncYourSavedArticles();

        ArticlePageObject.closeArticle();

        //Add second article
        SearchPageObject.initSearchInput();

        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.typeSearchLine("Java");
        }
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject.waitForArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList();
        }else{
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName();
        }
        //Delete Article
        MyListsPageObject.swipeArticleToDelete(article_title);
        //Check that second article remains
        MyListsPageObject.checkSecondArticleRemainOnPage();
    }
}

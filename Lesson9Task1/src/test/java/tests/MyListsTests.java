package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String
            login = "VolhaSalnikova",
            password = "Dobbik123";

    @Test
    public void testSaveArticlesToMyList() throws InterruptedException {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Java";
        SearchPageObject.typeSearchLine(searchLine);

        final String substringForJavaScript = "High-level programming language";
        SearchPageObject.clickByArticleWithSubstring(substringForJavaScript);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitle();
        String article_title_for_js = ArticlePageObject.getArticleTitle().trim();


        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            assertEquals("we are not on the same page after login", article_title_for_js, ArticlePageObject.getArticleTitle());
        }


        if (Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncYourSavedArticles();
        }

        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {
            ArticlePageObject.closeArticle();
        }

        SearchPageObject.initSearchInput();
        searchLine = "Java";
        SearchPageObject.typeSearchLine(searchLine);
        final String substringForJava = "Object-oriented programming language";
        SearchPageObject.clickByArticleWithSubstring(substringForJava);

        ArticlePageObject.waitForArticleTitle();
        String article_title_for_java = ArticlePageObject.getArticleTitle().trim();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.checkAmountOfArticlesOnPageBeforeSaving();
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName();
        }
        MyListsPageObject.swipeArticleToDelete(article_title_for_js);
        MyListsPageObject.checkAmountOfArticlesOnPageAfterSaving();
        MyListsPageObject.clickByArticleWithTitle(article_title_for_java);

    }


}





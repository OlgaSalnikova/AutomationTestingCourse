package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Changing App Conditions")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Apps Orientation")
    @Description("Check apps orientation on Portrait and Landscape")
    @Step("Staring testScreenChangeOrientationOnSearchResults test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testScreenChangeOrientationOnSearchResults() {

        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article title have been changed after rotation",
                title_after_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Send App in Background")
    @Description("Send app in Background and Open it")
    @Step("Staring testCheckSearchArticleInBackground test")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchArticleInBackground(){

        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}

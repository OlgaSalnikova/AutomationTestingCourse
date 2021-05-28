package lib.ui.factories;

import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.AuthorizationPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.ios.IOSArticlePageObject;
import lib.ui.mobile_web.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObjectFactory {

    public static AuthorizationPageObject get(RemoteWebDriver driver) {

        return new AuthorizationPageObject(driver);
    }

}

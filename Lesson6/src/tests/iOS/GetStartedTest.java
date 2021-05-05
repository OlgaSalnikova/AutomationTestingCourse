package tests.iOS;

import lib.CoreIOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreIOSTestCase {

    @Test
    public void testPassThroughWelcome(){

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLanguagesLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedLink();
        WelcomePage.clickGetStartedButton();
    }
}

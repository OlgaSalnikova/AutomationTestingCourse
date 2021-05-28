package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for Get Started")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Get Started")})
    @DisplayName("Go thought Get Started Pages")
    @Description("Pass thought all Get Started Pages")
    @Step("Staring testPassThroughWelcome test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testPassThroughWelcome(){

        if((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())){
            return;
        }

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

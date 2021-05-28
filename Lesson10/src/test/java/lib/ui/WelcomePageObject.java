package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{

    private static final String

            STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
            STEP_NEW_WAYS_TO_EXPLORE ="xpath://XCUIElementTypeStaticText[@name='New ways to explore']",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES = "xpath://*[@name='Add or edit preferred languages']",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
            NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name='Next']",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP_BUTTON = "id:Skip";




    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Wait Learn More Link")
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Can not find Learn more about Wikipedia link",10);
    }

    @Step("Wait New Way To Explore Text")
    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE,"Can not find New ways to explore text",10);
    }

    @Step("Wait Add Or Edit Preferred Languages Link")
    public void waitForAddOrEditPreferredLanguagesLink()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES,"Can not find Add or edit preferred languages link",10);
    }

    @Step("Wait Learn More About Data Collected Link")
    public void waitForLearnMoreAboutDataCollectedLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,"Can not find Add or edit preferred languages link",10);
    }

    @Step("Tap Next button")
    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_BUTTON,"Can not find and click Next link",10);
    }

    @Step("Tap Get Started button")
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Can not find and click Get Started button",10);
    }

    @Step("Tap Skip")
    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP_BUTTON, "Can not find and click Skip button", 5);
    }
}

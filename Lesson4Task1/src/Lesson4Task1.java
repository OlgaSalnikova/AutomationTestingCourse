import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

import java.net.URL;

public class Lesson4Task1 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Android Test Device");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/olgasalnikova/Desktop/mobileAutomationtestingCourse/GitHub/AutomationTestingCourse/Lesson4Task1/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @Test
    public void saveArticlesToMyList()
    {
        waitForElementAndClick
        (
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Can not find Search Wikipedia field",
            5
        );

        waitElementAndSendKeys
        (
        By.xpath("//*[contains(@text,'Search…')]"),
            "Poland",
            "Can not find Search… field",
            5
        );

        waitForElementAndClick
        (
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Poland']"),
            "Can not find article with Poland in title",
            5
        );

        waitForElementPresent
         (
            By.id("org.wikipedia:id/view_page_title_text"),
            "Can not find article title of the opened article",
            15
        );


        waitForElementAndClick
        (
            By.xpath("//*[@class='android.widget.ImageView'][@content-desc='More options']"),
            "Can not find button to open article options",
            5
        );

        waitForElementPresent
        (
            By.xpath("//*[@class='android.widget.ListView']"),
            "Can not find menu with options",
            10
        );
        waitForElementAndClick
        (
            By.xpath("//android.widget.ListView//*[@text='Add to reading list']"),
            "Can not find option to add article to Reading List",
            5
        );
        waitForElementAndClick
        (
            By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/onboarding_button']"),
            "Can not find Got It tip overlay",
            5
        );

        waitForElementAndClear
        (
            By.id("org.wikipedia:id/text_input"),
            "Can not find input to save name of articles folder",
            5
        );

        waitElementAndSendKeys
        (
            By.id("org.wikipedia:id/text_input"),
            "About Poland",
            "Can not put text to articles folder input",
            5
        );

        waitForElementAndClick
        (
            By.xpath("//*[@text='OK']"),
            "Can not find Ok button",
            5
        );

        waitForElementAndClick
        (
            By.xpath("//*[@content-desc='Navigate up']"),
            "Can not find X link",
            5
        );

        waitForElementAndClick
        (
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Can not find Search Wikipedia field",
            5
        );

        waitElementAndSendKeys
        (
            By.xpath("//*[contains(@text,'Search…')]"),
            "Poland",
            "Can not find Search… field",
            5
        );

        waitForElementAndClick
        (
            By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Poland national football team']"),
            "Can not find article with Poland name in title",
            5
        );

        waitForElementPresent
        (
            By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/view_page_title_text']"),
            "Can not find article title of the opened article",
            15
        );

        waitForElementAndClick
        (
             By.xpath("//*[@class='android.widget.ImageView'][@content-desc='More options']"),
             "Can not find button to open article options",
             5
        );

        waitForElementPresent
        (
             By.xpath("//*[@class='android.widget.ListView']"),
             "Can not find menu with options",
             10
        );

        waitForElementAndClick
        (
             By.xpath("//android.widget.ListView//*[@text='Add to reading list']"),
             "Can not find option to add article to Reading List",
             5
        );

        waitForElementAndClick
        (
             By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='org.wikipedia:id/list_of_lists']"),
             "Can not find the existed folder",
             5
        );

        waitForElementAndClick
        (
             By.xpath("//*[@content-desc='Navigate up']"),
             "Can not find X link",
             5
        );

        waitForElementAndClick
        (
             By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
             "Can not find My List button",
             5
        );

        waitForElementPresent
        (
             By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='org.wikipedia:id/reading_list_list']"),
             "Can not find My list",
             15
        );

        waitForElementAndClick
        (
             By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title'][@text='About Poland']"),
             "Can not find created folder",
             5
        );

        waitForElementPresent
        (
            By.xpath("//android.support.v7.widget.RecyclerView[@resource-id='org.wikipedia:id/reading_list_contents']"),
            "Can not find article list",
            15
        );

        waitForElementPresent
        (
            By.xpath("//android.widget.LinearLayout[.//android.widget.TextView[@text='Poland']]"),
            "Can not find article",
            15
        );
        swipeElementToLeft
        (
            By.xpath("//android.widget.LinearLayout[.//android.widget.TextView[@text='Poland']]"),
            "Can not find saved article"
        );

//        waitForElementNotPresent
//        (
//           By.xpath("//android.widget.LinearLayout[.//android.widget.TextView[@text='Poland']]"),
//           "Can not delete saved article",
//           5
//        );

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.clear();

        return element;
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);

        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}

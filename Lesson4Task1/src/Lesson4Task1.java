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
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("deviceName", "Android Test Device");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/olgasalnikova/Desktop/MobileAutomationCourse/Lesson4Task1/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @Test
    public void saveArticlesToMyList()
    {
        //Save First Article
        String search_wikipedia_field = "//*[contains(@text,'Search Wikipedia')]";
        waitForElementAndClick
                (
                        By.xpath(search_wikipedia_field),
                        "Can not find Search Wikipedia field",
                        5
                );

        String value = "Poland";
        String search_placeholder = "//*[contains(@text,'Search…')]";
        waitElementAndSendKeys
                (
                        By.xpath(search_placeholder),
                        value,
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

        String more_options_button = "//*[@class='android.widget.ImageView'][@content-desc='More options']";
        waitForElementAndClick
                (
                        By.xpath(more_options_button),
                        "Can not find button to open article options",
                        5
                );

        String menu_with_options = "//android.widget.ListView";
        waitForElementVisible
                (
                        By.xpath(menu_with_options),
                        "Can not find menu with options",
                        15
                );

        String add_to_reading_list_option = "//*[@text='Add to reading list']";
        waitForElementAndClick
                (
                        By.xpath(add_to_reading_list_option),
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

        String x_button = "//android.widget.ImageButton[@content-desc='Navigate up']";
        waitForElementAndClick
                (
                        By.xpath(x_button),
                        "Can not find X link",
                        5
                );

        //Save First Article

        waitForElementAndClick
                (
                        By.xpath(search_wikipedia_field),
                        "Can not find Search Wikipedia field",
                        5
                );

        waitElementAndSendKeys
                (
                        By.xpath(search_placeholder),
                        value,
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
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Can not find article title of the opened article",
                        15
                );

        waitForElementAndClick
                (
                        By.xpath(more_options_button),
                        "Can not find button to open article options",
                        5
                );

        waitForElementVisible
                (
                        By.xpath(menu_with_options),
                        "Can not find menu with options",
                        15
                );

        waitForElementAndClick
                (
                        By.xpath(add_to_reading_list_option),
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
                        By.xpath(x_button),
                        "Can not find X link",
                        5
                );

        waitForElementAndClick
                (
                        By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                        "Can not find My List button",
                        5
                );

        waitForElementVisible
                (
                        By.id("org.wikipedia:id/item_container"),
                        "Can not find folder list",
                        15
                );

        waitForElementAndClick
                (
                        By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title']"),
                        "Can not find created folder",
                        15
                );

        waitForElementVisible
                (
                        By.xpath("//android.widget.ScrollView"),
                        "Can not find Articles List",
                        15
                );

        String first_article = "//*[@text='Poland']";
        waitForElementPresent
                (
                        By.xpath(first_article),
                        "Can not find article",
                        15
                );

        swipeElementToLeft
                (
                        By.xpath(first_article),
                        "Can not find saved article"
                );

        waitForElementNotPresent
                (
                        By.xpath(first_article),
                        "Can not delete saved article",
                        5
                );

        waitForElementAndClick
                (
                        By.xpath("//*[@text='Poland national football team']"),
                        "Can not find article",
                        15
                );

        waitForElementPresent
                (
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Can not find article title of the opened article",
                        15
                );
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

    private WebElement waitForElementVisible(By by,String error_message, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage("\n" + error_message + "\n");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
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

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

public class Lesson4Task2 {

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
    public void checkArticleTitle()
    {
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

        assertElementPresent
                (
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Can not find article with Poland in title"
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

    private void assertElementPresent(By by, String error_message)
    {
        WebElement element = driver.findElement(by);
        String article_title = element.getAttribute("text");
        if(article_title == "Poland"){
            System.out.print("test past");
        }else{
            String default_message = "An element" + by.toString() + " suppose present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

}

package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Task3 {

    AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("deviceName", "Anfroid Test Device");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("app", "/Users/olgasalnikova/Desktop/MobileAutomationCourse/Lesson5Task2/apks/org.wikipedia.apk");
        capabilities.setCapability("appActivity", ".main.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void checkWordsInSearchResult()
    {
        waitForElementPresentAndClick
        (
         By.xpath("//*[@resource-id='org.wikipedia:id/search_container']"),
         "Can not find Wikipedia search field",
         5
         );

        waitForElementPresentAndSendkey
        (
        By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
        "Can not find Search... field",
        5,
         "Java"
        );

        waitForElementPresent
        (
        By.xpath("//android.widget.ListView[@resource-id='org.wikipedia:id/search_results_list']//android.widget.LinearLayout[.//android.widget.TextView[contains(@text,'Java')] and .//android.widget.TextView[contains(@text,'Object-oriented programming language')]]"),
        "Can not find the result of the search",
        15
        );

//        waitForElementPresent
//        (
//        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']"),
//        "Can not find the result of the search",
//        15
//        );

//        getArticle
//        (
//         By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']/android.widget.LinearLayout"),
//         By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
//         By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']"),
//         "The Article text does not contain requested word",
//         "Java"
//        );
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    private WebElement waitForElementPresentAndClick(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementPresentAndSendkey(By by, String error_message, long timeOutInSeconds, String value)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void getArticle(By articleXpath, By titleXpath, By descriptionXpath, String error_message, String value)
    {
        List<WebElement> articles = driver.findElements(articleXpath);

        for (WebElement article : articles) {
            String title = article.findElement(titleXpath).getText();
            String description = article.findElement(descriptionXpath).getText();

            Assert.assertFalse(error_message, !title.contains(value) && !description.contains(value));
        }
    }
}


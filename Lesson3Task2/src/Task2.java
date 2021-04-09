import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;


public class Task2 {
    AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/olgasalnikova/Desktop/mobileAutomationtestingCourse/GitHub/AutomationTestingCourse/Lesson3Task2/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void cancelSearchResult()
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
                        "Can not find Wikipedia search field",
                        5,
                        "Poland"
                );

        waitForElementPresent
                (
                        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container'][2]"),
                        "Can not find element",
                        15
                );

        waitForElementAndClick
                (
                        By.id("org.wikipedia:id/search_close_btn"),
                        "Can not find X cancel search",
                        5
                );

        waitForElementNotPresent
                (
                        By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_container'][2]"),
                        "Can not find element",
                        15
                );
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

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


}

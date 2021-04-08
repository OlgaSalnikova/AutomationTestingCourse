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


public class Task1
{
    private AppiumDriver driver;

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
        capabilities.setCapability("app", "/Users/olgasalnikova/Desktop/mobileAutomationtestingCourse/GitHub/AutomationTestingCourse/JavaAppiumAutomation/Lesson2Task1/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void compareSearchFieldText()
    {
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Can not find article title",
                5
        );

        String search_field_text = title_element.getAttribute("text");

        assertElementHasText(
                "Unexpected search fields text",
                "Search Wikipedia",
                search_field_text
        );
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    private void assertElementHasText(String error_message, String expected, String search_field_text)
    {
        Assert.assertEquals(error_message, expected, search_field_text);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

}

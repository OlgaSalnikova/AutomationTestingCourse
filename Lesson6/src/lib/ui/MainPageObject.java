package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitElementAndSendKeys(String locator, String value, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementPresent(String locator, String error_message) {

        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeOutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }



    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(2000);
    }
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {

        int alreadySwipped = 0;
        By by = this.getLocatorByString(locator);
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipped > max_swipes) {
                waitForElementPresent(locator, "Can not find element by swipe. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipped;
        }

    }
    public WebElement waitForElementAndClear(String locator, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSeconds);
        element.clear();

        return element;
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);

        action.press(PointOption.point(right_x, middle_y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(150))).moveTo(PointOption.point(left_x, middle_y)).release().perform();
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amountOfSearchResult = getAmountOfElements(locator);
        if (amountOfSearchResult > 0) {
            String default_message = "An element" + locator + "supposed not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    public WebElement waitForElementVisible(String locator,String error_message, long timeOutInSeconds){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage("\n" + error_message + "\n");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if(by_type.equals("xpath")){
            return By.xpath(locator);
        }else if(by_type.equals("id")){
            return By.id(locator);
        }else{
            throw new IllegalArgumentException("Can not get type of locator. Locator: " + locator_with_type);
        }
    }
}

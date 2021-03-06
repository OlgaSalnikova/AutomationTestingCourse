package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }
    public WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementPresent(By by, String error_message) {

        return waitForElementPresent(by, error_message, 5);
    }

    public WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
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

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    public void swipeUpQuick() {
        swipeUp(2000);
    }

    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {

        int alreadySwipped = 0;
        while (driver.findElements(by).size() == 0) {
            if (alreadySwipped > max_swipes) {
                waitForElementPresent(by, "Can not find element by swipe. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipped;
        }

    }
    public WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.clear();

        return element;
    }

    public void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);

        action.press(right_x, middle_y).waitAction(150).moveTo(left_x, middle_y).release().perform();
    }

    public boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public int getAmountOfElements(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(By by, String error_message) {
        int amountOfSearchResult = getAmountOfElements(by);
        if (amountOfSearchResult > 0) {
            String default_message = "An element" + by.toString() + "supposed not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    public WebElement waitForElementVisible(By by,String error_message, long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage("\n" + error_message + "\n");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void assertElementPresent(By by, String error_message)
    {
        WebElement element = driver.findElement(by);
        String article_title = element.getAttribute("text").trim();
        if(article_title == "Poland"){
            System.out.print("test past");
        }else{
            String default_message = "An element" + by.toString() + " suppose present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
}

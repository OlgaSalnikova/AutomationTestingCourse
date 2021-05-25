package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{ ;

 protected static String
    MY_LIST_BUTTON,
    OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick
                    (
                            OPEN_NAVIGATION,
                            "Can not find and click Navigation menu",
                            5
                    );
        }else{
            System.out.println("Method openNavigation() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyList() throws InterruptedException {
        if(Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(
                    MY_LIST_BUTTON,
                    "Can not find WatchList button",
                    5
            );
        }else {
            this.waitForElementAndClick
                    (
                            MY_LIST_BUTTON,
                            "Can not find My List button",
                            5
                    );
        }
    }

}

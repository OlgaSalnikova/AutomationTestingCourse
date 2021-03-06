package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{ ;

    private static final String
    MY_LIST_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick
        (
              MY_LIST_BUTTON,
              "Can not find My List button",
              5
        );
    }

}

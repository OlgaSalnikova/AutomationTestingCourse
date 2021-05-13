package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_CONTAINER = "xpath://XCUIElementTypeLink";
        EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }

    public IOSSearchObject(AppiumDriver driver) {
        super(driver);
    }


}

package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{

    private static final String
            LOGIN_BUTTON = "xpath://*[contains(@class,'drawer-container__drawer')]//a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "id:wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementVisible(LOGIN_BUTTON, "Can not find Auth button", 10);
        this.waitForElementClickableAndClick(LOGIN_BUTTON, "Can not find and click Auth button", 10);
    }

    public void enterLoginData(String login, String password){
        this.waitElementAndSendKeys(LOGIN_INPUT, login, "Can not find and put login into login field", 5);
        this.waitElementAndSendKeys(PASSWORD_INPUT, password, "Can not find and put password into password field", 5);
    }

    public void submitForm(){

        this.waitForElementClickableAndClick(SUBMIT_BUTTON, "Can not find and click Submit button", 5);
    }
}

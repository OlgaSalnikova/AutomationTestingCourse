package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    // AppiumDriverLocalService appiumService = this.getService();

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        //  appiumService.start();
        driver = Platform.getInstance().getDriver();
        if((Platform.getInstance().isAndroid()) && (Platform.getInstance().isIOS())) {
            this.rotateScreenPortrait();
        }
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb();
    }

    @Override
    protected void tearDown() throws Exception {

        driver.quit();
        // appiumService.stop();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }


    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }else {
            System.out.println("Method backgroundApp() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPageForMobileWeb(){
        if(Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        }else{
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomePageForIOSApp() {
        if (Platform.getInstance().isIOS()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }


    private AppiumDriverLocalService getService() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .usingPort(4723)
                .withLogFile(new File("AppiumLog.txt"));


        return AppiumDriverLocalService.buildService(builder);
    }
}

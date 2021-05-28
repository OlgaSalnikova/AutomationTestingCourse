package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase{

    protected RemoteWebDriver driver;

    // AppiumDriverLocalService appiumService = this.getService();

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        //  appiumService.start();
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        if((Platform.getInstance().isAndroid()) && (Platform.getInstance().isIOS())) {
            this.rotateScreenPortrait();
        }
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Close driver and session")
    public void tearDown() {

        driver.quit();
        // appiumService.stop();

    }
    @Step("Rotate screen to Portrait mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Rotate screen to Landscape mode")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send app to background (does not fork for web)")
    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }else {
            System.out.println("Method backgroundApp() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Launch web wiki")
    protected void openWikiWebPageForMobileWeb(){
        if(Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org");
        }else{
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform: " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome pages for ios")
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

    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos,"See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        }catch (Exception e){
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}

package io.appium.tests;


import io.appium.config.AppiumDriverConfig;
import io.appium.config.AppiumServer;
import io.appium.config.Device;
import io.appium.config.DevicesConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.pages.MainPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.List;
import java.util.Optional;

public class BaseTest {

    private AppiumServer server;
    private AppiumDriver driver;

    @BeforeMethod
    public void setup() {
        List<Device> devices = DevicesConfig.devices;

        for (Device device : devices) {
             server = new AppiumServer()
                    .withServerPort(device.serverPort)
                    .build()
                    .start();

             driver = new AppiumDriverConfig()
                    .setDevice(device)
                    .startAppiumDriver();
        }
    }

    @AfterMethod
    public void shutdown(ITestResult result){
        attachScreenshotAndFiles(result);
        stopAppium();
    }

    private void stopAppium() {
        try {
            AppiumServer appiumServer = server;
            Optional.ofNullable(appiumServer).ifPresent(func -> appiumServer.stop());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected MainPage navigateToMainPage(){
        return getMainPage(driver);
    }

    protected MainPage getMainPage(AppiumDriver driver){
        return new MainPage(driver);
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String attachFile(String attachName, String message) {
        return Optional.ofNullable(message).orElse(null);
    }

    @Attachment(value = "{0}", type = "image/png")
    private synchronized byte[] attachScreenshot(String screenshotName) {
        if (driver == null) {
            return null;
        }

        return driver.getScreenshotAs(OutputType.BYTES);
    }

    private void attachScreenshotAndFiles(ITestResult result) {
        try {
            attachScreenshot("screenshot_web.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (result != null && !result.isSuccess() && driver != null) {
                Optional
                        .ofNullable(result.getThrowable())
                        .ifPresent(error -> attachFile("error_web.txt", error.getMessage()));

                attachFile("page-source_web.txt", driver.getPageSource());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

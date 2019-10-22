package io.appium.config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class AppiumDriverConfig {

    private static final String REMOTE_HOST = "http://127.0.0.1";
    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumDriverConfig.class);

    private Device device;

    public AppiumDriverConfig setDevice(Device device) {
        this.device = device;
        return this;
    }

    public AppiumDriver startAppiumDriver() {
        LOGGER.info("Start AppiumDriver...");
        LOGGER.info("Device name: " + device.deviceName);

        URL remoteAddress = getRemoteAddress();
        DesiredCapabilities capabilities = getCapabilities();
        AppiumDriver driver = new AndroidDriver(remoteAddress,capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        return driver;
    }

    private URL getRemoteAddress() {
        URL remoteAddress = null;

        LOGGER.info("Server port: " + device.serverPort);

        try {
            remoteAddress = new URL(REMOTE_HOST + ":" + device.serverPort + "/wd/hub");
        } catch (MalformedURLException e) {
            fail("Unable to create remote address");
            e.printStackTrace();
        }

        return remoteAddress;
    }

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.platformName);
        capabilities.setCapability(MobileCapabilityType.VERSION, device.platformVersion);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, device.udid);

//        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true); //if application is already installed and dont want to re-install
//        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\user\\IdeaProjects\\appium\\app\\sendinformationEmail.apk");//If application new build is available set path for it
       capabilities.setCapability("appActivity", "com.ottawaeast.mka.reprotingappv10.MainActivity");
       capabilities.setCapability("appPackage", "com.ottawaeast.mka.reprotingappv10");

        return capabilities;
    }

}

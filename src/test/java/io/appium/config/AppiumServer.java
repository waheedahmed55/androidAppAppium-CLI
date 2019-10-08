package io.appium.config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;

import static org.testng.Assert.fail;

public class AppiumServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumServer.class);
    private static final String SERVER_IP = "127.0.0.1";
    private static final int WAIT_SERVER_TIMEOUT_IN_SECONDS = 10;
    private static final int WAIT_INTERVAL_IN_SECONDS = 10;

    private AppiumDriverLocalService appiumService;
    private int serverPort;

    public AppiumServer withServerPort(int serverPort) {
        this.serverPort = serverPort;
        return this;
    }

    public AppiumServer build() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, false);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.PROXYING_EVERYTHING, false);
        String appiumPath = "C:\\Users\\user\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress(SERVER_IP)
                .usingPort(serverPort)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withArgument(GeneralServerFlag.ASYNC_TRACE)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withCapabilities(capabilities)
                .withAppiumJS(new File(appiumPath));

        appiumService = AppiumDriverLocalService.buildService(builder);

        return this;
    }

    public AppiumServer start() {
        if (isServerRunning()) {
            LOGGER.info("Appium server already running");
            return this;
        }

        LOGGER.info("Starting Appium Server at " + SERVER_IP + ":" + serverPort);

        getWaiter().until(func -> {
            appiumService.start();
            return appiumService.isRunning();
        });

        if (appiumService.isRunning()) {
            LOGGER.info("Appium Server started successfully!");
        } else {
            fail("Appium Server NOT started!");
        }

        return this;
    }

    public void stop() {
        LOGGER.info("Stop Appium Server...");

        getWaiter().until(func -> stopAppiumServer());
    }

    private boolean stopAppiumServer() {
        LOGGER.info("Stop Appium Server...");

        boolean isAppiumServerStopped;

        try {
            appiumService.stop();
            isAppiumServerStopped = !appiumService.isRunning();
        } catch (Exception e) {
            LOGGER.error("Exception while stop Appium Server.\n" + e.getMessage());
            isAppiumServerStopped = true;
        }

        return isAppiumServerStopped;
    }

    private boolean isServerRunning() {
        if (appiumService != null && appiumService.isRunning()) {
            return true;
        }

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(serverPort);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    private FluentWait<AppiumDriverLocalService> getWaiter() {
        return new FluentWait<>(appiumService)
                .withTimeout(Duration.ofSeconds(WAIT_SERVER_TIMEOUT_IN_SECONDS))
                .pollingEvery(Duration.ofSeconds(WAIT_INTERVAL_IN_SECONDS));
    }
}

package io.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {

    protected AppiumDriver driver;
    protected final String APP_PACKAGE_ID = "com.ottawaeast.mka.reprotingappv10";
    private static final long WAIT_ELEMENT_TIMEOUT = 90;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitUntilElementVisible(MobileElement element){
        getWaiter().until(visibilityOf(element));
    }

    private FluentWait<AppiumDriver> getWaiter(){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_ELEMENT_TIMEOUT))
                .ignoring(NoSuchElementException.class);
    }

}

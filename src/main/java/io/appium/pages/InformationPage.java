package io.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class InformationPage extends BasePage {

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/Emailbutton")
    private AndroidElement emailButton;

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/ActivityeditText")
    private AndroidElement activityTitleTxtField;

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/numberofkhuddameditText")
    private AndroidElement numOfKhuddamTxtField;

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/activitydecriptioneditText")
    private AndroidElement actvtyDescriptionTxtField;

    public InformationPage(AppiumDriver driver) {
        super(driver);
        waitUntilElementVisible(emailButton);
    }

    public boolean checkIsEmailButtonOnThePage() {
        return emailButton.isDisplayed();
    }

    public boolean checkactivityTxtFieldisEnabled() {
        return activityTitleTxtField.isEnabled();
    }

    public String getActivityFieldTxt() {
        return activityTitleTxtField.getText();
    }

    public void setTxtactivityField(String text) {
        activityTitleTxtField.sendKeys(text);
    }

    public boolean checkNumOfkhudFieldisEnabled() {
        return numOfKhuddamTxtField.isEnabled();
    }

    public String getNumofKhudFieldTxt() {
        return numOfKhuddamTxtField.getText();
    }

    public void setTxtnumofKhudField(String text) {
        numOfKhuddamTxtField.sendKeys(text);
    }

    public boolean checkactvtyDescpFieldisEnabled() {
        return actvtyDescriptionTxtField.isEnabled();
    }

    public String getactvtyDescpFieldTxt() {
        return actvtyDescriptionTxtField.getText();
    }

    public void setTextActivityDescpField(String text) {
        actvtyDescriptionTxtField.sendKeys(text);
    }

    @Step("Click button 'Back' and go to MainPage")
    public MainPage navigateBackToMainPage() {
        driver.navigate().back();
        return new MainPage(driver);
    }
}

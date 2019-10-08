package io.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class MainPage extends BasePage {

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/nextbutton")
    private AndroidElement nextButton;

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/date")
    private AndroidElement datePickerButton;

    @AndroidFindBy(accessibility = "07 October 2019")
    private AndroidElement date;

    @AndroidFindBy(id = "android:id/button1")
    private AndroidElement acceptDateButton;

    @AndroidFindBy(id = "com.ottawaeast.mka.reprotingappv10:id/spinner")
    private AndroidElement spinner;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[6]")
    private AndroidElement mal;

    @AndroidFindBy(id = "android:id/text1")
    private AndroidElement spinnerField;

    public MainPage(AppiumDriver driver) {
        super(driver);
        waitUntilElementVisible(nextButton);
    }

    @Step("Click button 'Date' and open datepicker")
    public MainPage openDatePicker(){
        waitUntilElementVisible(datePickerButton);
        datePickerButton.click();
        datePickerButton.click();
        return this;
    }

    @Step("Choose date")
    public MainPage chooseDate(){
        waitUntilElementVisible(date);
        date.click();
        waitUntilElementVisible(acceptDateButton);
        acceptDateButton.click();
        return this;
    }

    @Step("Choose office")
    public MainPage chooseOffice(){
        spinner.click();
        waitUntilElementVisible(mal);
        mal.click();
        return this;
    }

    @Step("Return date")
    public String getDate(){
        waitUntilElementVisible(datePickerButton);
        return datePickerButton.getText();
    }

    @Step("Click on the button 'Next' and navigate to InformationPage")
    public InformationPage navigateToInformationPage(){
        nextButton.click();
        return new InformationPage(driver);
    }

    @Step("Return text from spinner")
    public String getTextFromSpinner(){
        return spinnerField.getText();
    }

    @Step("Return state of 'Next' button on page")
    public boolean checkIsNextButtonOnThePage() {
        return nextButton.isDisplayed();
    }
}

package io.appium.tests;

import io.appium.pages.MainPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;


@Epic("Regression")
@Feature("Login")
@Story("Main")
public class MainPageTest extends BaseTest {

    @Test
    public void testAppLaunch(){
        boolean isNextButtonIsDisplayed =  navigateToMainPage()
                .checkIsNextButtonOnThePage();

        Assert.assertTrue(isNextButtonIsDisplayed);
    }

    @Test
    public void chooseDateTest(){
        MainPage mainPage = navigateToMainPage()
                .openDatePicker()
                .chooseDate();

        String actualDate = mainPage.getDate();
        String expectedDate = "7/10/2019";

        Assert.assertEquals(actualDate,expectedDate);
    }

    @Test
    public void chooseOfficeTest(){
        MainPage mainPage = navigateToMainPage()
                .chooseOffice();

        String actualOffice = mainPage.getTextFromSpinner();
        String expectedOffice = "Mal";

        Assert.assertEquals(actualOffice,expectedOffice);
    }
}

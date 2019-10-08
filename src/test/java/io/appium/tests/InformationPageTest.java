package io.appium.tests;

import io.appium.pages.InformationPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression")
@Feature("Login")
@Story("Information")
public class InformationPageTest extends BaseTest {

    @Test
    public void information(){
        InformationPage informationPage = navigateToMainPage()
                .navigateToInformationPage();

        boolean isEmailButtonIsDisplayed =  informationPage
                .checkIsEmailButtonOnThePage();

        Assert.assertTrue(isEmailButtonIsDisplayed);
    }
    @Test
    public void activityFieldTest(){
        InformationPage informationPage = navigateToMainPage()
                .navigateToInformationPage();

        boolean isactivityFieldTxtEnabled =  informationPage.checkactivityTxtFieldisEnabled();

        Assert.assertTrue(isactivityFieldTxtEnabled);

        informationPage.setTxtactivityField("Hello World");

        String actualTxt = informationPage.getActivityFieldTxt();
        String expectedTxt = "Hello World";

        Assert.assertEquals(actualTxt,expectedTxt);

    }

    @Test
    public void numOfKhudFieldTest(){
        InformationPage informationPage = navigateToMainPage()
                .navigateToInformationPage();

        boolean isnumofKhudFieldTxtEnabled =  informationPage.checkNumOfkhudFieldisEnabled();

        Assert.assertTrue(isnumofKhudFieldTxtEnabled);

        informationPage.setTxtnumofKhudField("5");

        String actualTxt = informationPage.getNumofKhudFieldTxt();
        String expectedTxt = "5";

        Assert.assertEquals(actualTxt,expectedTxt);

    }

    @Test
    public void activiyDescpFieldTest(){
        InformationPage informationPage = navigateToMainPage()
                .navigateToInformationPage();

        boolean isactvtyDescriptionFieldTxtEnabled =  informationPage.checkactvtyDescpFieldisEnabled();

        Assert.assertTrue(isactvtyDescriptionFieldTxtEnabled);

        informationPage.setTextActivityDescpField("Thursday Morning");

        String actualTxt = informationPage.getactvtyDescpFieldTxt();
        String expectedTxt = "Thursday Morning";

        Assert.assertEquals(actualTxt,expectedTxt);

    }
}

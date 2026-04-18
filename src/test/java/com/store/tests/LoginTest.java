package com.store.tests;

import com.store.pages.LoginPage;
import com.store.pages.RegisterPage;
import com.store.utils.BaseTest;
import com.store.utils.DataProviders;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "dpFromCsv", dataProviderClass = DataProviders.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can login successfully with valid credentials")
    @Epic("Ecommerce App")
    @Feature("Login")
    @Story("Verify that the user can login successfully with valid credentials")
    public void doLoginTestWithDataProvider(String username, String password) {
        homePage.clickLogin();
        loginPage.fillOutFormWithDataProvider(username, password);
        loginPage.clickLogin();
        //assertion segun sea el caso,
        Assert.assertEquals(loginPage.verifyEditAccount(), loginPage.EDIT_ACCOUNT);

    }
}

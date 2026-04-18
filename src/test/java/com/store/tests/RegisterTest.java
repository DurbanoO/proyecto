package com.store.tests;

import com.store.pages.RegisterPage;
import com.store.utils.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can register successfully")
    @Epic("Ecommerce App")
    @Feature("Register")
    @Story("Verify that the user can register successfully")
    public void verifyUserCanRegisterSuccessfully() {
        homePage.clickRegister();
        registerPage.fillOutFormWithFakeData();
        registerPage.clickRegister();
        //assert
        Assert.assertEquals(registerPage.verifyMessage(), "Your Account Has Been Created!");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Complete the form with false information, do not check the terms and conditions")
    @Epic("Ecommerce App")
    @Feature("Register")
    @Story("Complete the form with false information; do not check the terms and conditions.")
   public void fillOutFormWithFakeDataDoNotCheckTheTermsAndConditions() {

        homePage.clickRegister();
        registerPage.fillOutFormWithFakeDataDoNotCheckTheTermsAndConditions();
        registerPage.clickRegister();
        //assert
        Assert.assertEquals(registerPage.verifyMessageTC(), "Warning: You must agree to the Privacy Policy!");
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user cannot register with invalid email")
    @Epic("Ecommerce App")
    @Feature("Register")
    @Story("Verify that the user cannot register with invalid email")
    public void verifyUserCannotRegisterWithInvalidEmail() {
        homePage.clickRegister();
        registerPage.fillOutFormWithInvalidEmail();
        registerPage.clickRegister();
        registerPage.getEmailValidationMessage();
        Assert.assertEquals(registerPage.getEmailValidationMessage(), registerPage.mensajeErrorEmail());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user cannot register with mismatch password")
    @Epic("Ecommerce App")
    @Feature("Register")
    @Story("Verify that the user cannot register with mismatch password")
    public void verifyUserCannotRegisterWithMismatchPassword() {
        homePage.clickRegister();
        registerPage.fillOutFormMisMatchPassword();
        registerPage.clickRegister();
        Assert.assertEquals(registerPage.verifyErrorMismatchPassword(), RegisterPage.ERROR_MESSAGE_MISMATCH_PASSWORD);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the user cannot register with missing data")
    @Epic("Ecommerce App")
    @Feature("Register")
    @Story("Verify that the user cannot register with missing data")
    public void verifyUserCannotRegisterWithMissingData() {
        if (true) {
            throw new SkipException("Skipping test due to known issue with registration form validation for missing data");
        }
    }
}

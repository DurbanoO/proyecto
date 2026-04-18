package com.store.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    WebDriver driver;

    /**
     * Web Elements
     *
     */
    public static String EDIT_ACCOUNT = "Edit your account information";

    By inputEmail = By.id("input-email");
    By inputPassword = By.id("input-password");
    By buttonLogin = By.cssSelector("input[value='Login']");
    By divMessage = By.className("message-error");
    By labelEdit = By.cssSelector("a[href*='account/edit']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

//    @Step("Fill out the login form with valid data - happy path")
//    public void fillOutForm() {
//        type(inputEmail, "4rohaserrr@gmail.com");
//        type(inputPassword, "SueprPass@#$@@");
//    }

    @Step("Fill out the login form with data provider")
    public void fillOutFormWithDataProvider(String email, String password) {
        type(inputEmail, email);
        type(inputPassword, password);
    }

    public void fillOutFormWithInvalidEmal() {

    }

    @Step("Click on the login button")
    public void clickLogin() {
        click(buttonLogin);
    }


    public String getLoginErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By divMessage = By.className("message-error");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(divMessage)
        ).getText();
    }


    @Step("Verifica el logeo bucando la opcion editar perfil")
    public  String verifyEditAccount() {
        return text(labelEdit).trim();
    }

}

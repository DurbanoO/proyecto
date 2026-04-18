package com.store.pages;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {

    protected WebDriver driver;
    protected Faker faker;

    public static String ERROR_MESSAGE_MISMATCH_PASSWORD = "Password confirmation does not match password!";
    private String emailErrado;


    /**
     * Web Elements
     */
    By inputFname = By.id("input-firstname");
    By inputLname = By.id("input-lastname");
    By inputTelephone = By.id("input-telephone");
    By inputEmail = By.id("input-email");
    By inputPassword = By.id("input-password");
    By inputConfirmPass = By.id("input-confirm");
    By buttonRegister = By.cssSelector("input[type='submit'][value='Continue']");
    By divMessage = By.xpath("//h1[contains(., 'Your Account Has Been Created!')]");
    By divMessageTC = By.cssSelector("div.alert-danger");;
    By lblErrorEmail = By.xpath("//span[@data-valmsg-for='Email']/span");
    By lblErrorMismatchPassword = By.xpath("//div[text()='Password confirmation does not match password!']");
    By checkboxTc = By.xpath("//label[contains(text(), 'I have read and agree to the')]");
    /**
     * Constructor
     **/

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        faker = new Faker();
    }

    /**
     * methods
     *
     **/

    @Step("Complete the form with false information, do not check the terms and conditions")
    public void fillOutFormWithFakeDataDoNotCheckTheTermsAndConditions() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(10, 12, true, true, true);
        type(inputFname, faker.address().firstName());
        type(inputLname, faker.address().lastName());
        type(inputEmail, email);
        type(inputTelephone,faker.phoneNumber().cellPhone());
        type(inputPassword, password);
        type(inputConfirmPass, password);
        System.out.println("Email is: " + email + " And Password: " + password);
    }


    @Step("Completa el formulario - happy path")
    public void fillOutFormWithFakeData() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(10, 12, true, true, true);
        type(inputFname, faker.address().firstName());
        type(inputLname, faker.address().lastName());
        type(inputEmail, email);
        type(inputTelephone,faker.phoneNumber().cellPhone());
        type(inputPassword, password);
        type(inputConfirmPass, password);
        click(checkboxTc);
        System.out.println("Email is: " + email + " And Password: " + password);
    }

    @Step("Completa el formulario con un correo electrónico inválido")
    public void fillOutFormWithInvalidEmail() {
        this.emailErrado = "qa#qa.qa";
        String password = faker.internet().password(10, 12, true, true, true);
        type(inputFname, faker.address().firstName());
        type(inputLname, faker.address().lastName());
        type(inputTelephone,faker.phoneNumber().cellPhone());
        type(inputEmail, this.emailErrado);
        type(inputPassword, password);
        type(inputConfirmPass, password);
        click(checkboxTc);
        System.out.println("Email is: " + this.emailErrado + " And Password: " + password);

    }
    @Step("Revalido el mensaje concatenado")
    public String mensajeErrorEmail() {
        return String.format(
                "Incluye un signo \"@\" en la dirección de correo electrónico. La dirección \"%s\" no incluye el signo \"@\".",
                this.emailErrado);

    }


    @Step("Completa el formulario con una contraseña incorrecta")
    public void fillOutFormMisMatchPassword() {
        String email = faker.internet().emailAddress();
        type(inputFname, faker.address().firstName());
        type(inputLname, faker.address().lastName());
        type(inputEmail, email);
        type(inputTelephone,faker.phoneNumber().cellPhone());
        type(inputPassword, faker.internet().password(10, 12, true, true, true));
        type(inputConfirmPass, faker.internet().password(10, 12, true, true, true));
        System.out.println("Email is: " + email);
    }

    @Step("Haz clic en el botón de registro")
    public void clickRegister() {
        click(buttonRegister);
    }


    @Step("Verifica el mensaje después del registro")
    public String verifyMessage() {
        return text(divMessage);
    }

    @Step("Verifica el mensaje después del registro sin Los Terminos y Condiciones")
    public String verifyMessageTC() {
        return text(divMessageTC);
    }

    @Step("Verifica el mensaje de error por correo electrónico inválido")
    public String verifyErrorEmail() {
        return text(lblErrorEmail);
    }

    @Step("Obtener el mensaje de validación de HTML5 del campo Email")
       public String getEmailValidationMessage() {
        WebElement element = driver.findElement(inputEmail);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Intentamos obtener el mensaje hasta por 3 segundos si viene vacío
        String message = "";
        for (int i = 0; i < 30; i++) {
            message = (String) js.executeScript("return arguments[0].validationMessage;", element);
            if (message != null && !message.isEmpty()) {
                break;
            }
            try { Thread.sleep(100); } catch (InterruptedException e) { }
        }
        return message;
    }
    @Step("Verifica el mensaje de error por contraseña incorrecta")
    public String verifyErrorMismatchPassword() {
        return text(lblErrorMismatchPassword);
    }
}

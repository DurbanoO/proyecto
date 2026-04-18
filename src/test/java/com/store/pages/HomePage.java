package com.store.pages;

import com.store.utils.AllureUtils;
import com.store.utils.MenuCategory;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HomePage extends BasePage {

    WebDriver driver;
    /***
     *
     * web elements
     */

    By imgLogo = By.cssSelector("img[alt='Poco Electro']");
    By inputSearch = By.id("entry_217822");
    By myAccount = By.cssSelector("a[data-toggle='dropdown'][href*='account/account']");
    By linkRegister = By.partialLinkText("Register");
    By linkLogin = By.partialLinkText("Login");

    /**
     * Constructor
     *
     *
     */
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Methods
     *
     *
     */
    @Step("Verify logo is displayed")
    public boolean verifyLogoIsDisplayed() {

        return find(imgLogo).isDisplayed();
    }

    @Step("Verify logo is displayed with ScreenShot")
    public void verifyLogoIsDisplayedWithScreenShot() {
        boolean logo = find(imgLogo).isDisplayed();
        AllureUtils.takeScreenshot(driver, "HomePage - Logo Displayed");
    }

    @Step("Verificar que se muestre la búsqueda de entrada")
    public boolean verifyInputSearchIsDisplayed() {

        return find(inputSearch).isEnabled();
    }

    @Step("Abrir menú de cuenta y hacer clic en el enlace de registro")
    public void clickRegister() {
        // 1. Primero clic en el menú padre
        click(myAccount);
        click(linkRegister);
    }

    @Step("Click on login link")
    public void clickLogin() {
        click(myAccount);
        click(linkLogin);
    }

    @Step("Verify menu options")
    public void verifyMenuOptions() {
        for (MenuCategory category : MenuCategory.values()) {

            //locate option menu
            String menuName = "//ul[@class='navbar-nav horizontal']//span[contains(text(),'" + category.getLabel() + "')]";

            WebElement menu = find(By.xpath(menuName));

            String textoVisible = menu.getText();

            System.out.println("Recorriendo categoría: " + category.name());

            System.out.println(" -> Texto en Web: " + textoVisible);
            System.out.println(" -> XPath utilizado: " + menu);

            menu.click();//


        }
    }
}

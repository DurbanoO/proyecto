package com.store.utils;

import com.store.pages.HomePage;
import com.store.pages.LoginPage;
import com.store.pages.RegisterPage;
import com.store.pages.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    protected int TIME_OUT = 10;
    protected String URL = "https://ecommerce-playground.lambdatest.io/";

    // protected String URL = "https://demowebshop.tricentis.com";

    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected CartPage cartPage;
    protected DBValidation dbValidation;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        
        String executionMode = System.getProperty("execution", "local");
        if (executionMode.equalsIgnoreCase("grid")) {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } else {
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));
        driver.get(URL);

        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        dbValidation = new DBValidation();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }
}

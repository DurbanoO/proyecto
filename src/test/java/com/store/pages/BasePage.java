package com.store.pages;

import com.store.utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends BaseTest {

    /*
     * Variables
     */
    protected WebDriver driver;
    protected WebDriverWait wait;

    /*
     * Constructor
     */

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
    }

    /*
     * Methods
     */
    public WebElement find(By element) {
        return driver.findElement(element);
    }

    public void click(By element) {
        find(element).click();
    }

    public void submit(By element) {
        find(element).submit();
    }

    public void type(By element, String text) {
        find(element).sendKeys(text);
    }

    public String text(By element) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
        return find(element).getText();
    }

    public boolean isDisplayedElement(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            // refresh...
            try {
                /// location by other element
            } catch (Exception e) {
                // TODO: handle exception
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

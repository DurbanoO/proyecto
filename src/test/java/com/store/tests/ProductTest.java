package com.store.tests;

import io.qameta.allure.*;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class ProductTest {
    //(enabled = true) El test aparece como desactivado antes de empezar.
    @Test //(enabled = true)
    @Severity(SeverityLevel.NORMAL)
    @Epic("Ecommerce App")
    @Feature("Product Page")
    @Story("Verify that the product details are displayed correctly")
    @Description("Verify that the product details are displayed correctly")
    public void verifyProductDetails() {
        if (true) {
            throw new SkipException("Skipping test due to known issue with product details display");
        }
    }
}

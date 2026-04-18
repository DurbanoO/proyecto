package com.store.tests;

import com.store.utils.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Ecommerce App")
    @Feature("Cart")
    @Story("Verify that adding items to the cart works correctly")
    @Description("Verify that adding items to the cart works correctly")
    public void verifyAddToCart() {
       cartPage.clickHamburguesa();
       cartPage.clickCategoria();
       cartPage.agregarProductoCarrito();
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Epic("Ecommerce App")
    @Feature("Cart")
    @Story("Verify that the cart total calculation is correct")
    @Description("Verify that the cart total calculation is correct")
    public void verifyCartTotalCalculation() {
        // Debemos agregar un producto antes de verificar el checkout, el carrito inicialmente está vacío
        cartPage.clickHamburguesa();
        cartPage.clickCategoria();
        cartPage.agregarProductoCarrito();
        
        // Verificamos el total
        cartPage.validarTotalCarrito();
    }
}

package com.store.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    By btnHamburguesaMenu = By.id("entry_217832");
    // By btnHamburguesaMenu = By.cssSelector ("a[aria-label='Shop by Category']");
    By categorySoftware = By.xpath("//span[@class='title' and contains(text(), 'Software')]");
    // Usamos un xpath más genérico para apuntar al primer producto y no depender de
    // un ID quemado
    By selectProduct = By.xpath("(//div[contains(@class, 'product-thumb')])[1]");
    By iconCarrito = By.xpath(
            "//div[@id='main-header']//div[contains(@class, 'd-md-flex')]//div[contains(@class, 'widget-cart')]//a");
    // Usamos el título del botón para identificarlo, ya que en el mini-carrito no
    // existe la clase .input-group-append
    By eliminarProducto = By.xpath("//button[@title='Remove']");

    @Step("Haz clic en el botón de shop by Category")
    public void clickHamburguesa() {
        click(btnHamburguesaMenu);
    }

    @Step("Haz clic en la categoria Category")
    public void clickCategoria() {
        click(categorySoftware);
    }
    /*
     * @Step("Haz clic en el producto")
     * public void clickProducto() {
     * click(selectProduct);
     * }
     */

    @Step("Agregar producto al carrito (Hover y primer botón)")
    public void agregarProductoCarrito() {
        // 1. Identificamos el elemento del producto y le hacemos hover
        WebElement productElement = find(selectProduct);
        Actions actions = new Actions(driver);
        actions.moveToElement(productElement).perform();
        System.out.println("Hover realizado sobre el producto.");

        // 2. Buscamos el primer botón que aparece luego del hover.
        By firstItemBtnLocator = By
                .xpath("(//div[contains(@class, 'product-thumb')]//button[@title='Add to Cart'])[1]");

        // 3. Esperamos a que el botón esté presente y lo clickeamos usando JS para
        // saltarnos posibles menús fijos (sticky headers)
        WebElement btnCart = wait.until(ExpectedConditions.presenceOfElementLocated(firstItemBtnLocator));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCart);
        System.out.println("Primer botón clicado exitosamente con JS.");

        // 4. Esperar a que aparezca la notificación verde (Toast) de éxito
        // Esto evita que saltemos a la validación antes de que el servidor procese el
        // carrito
        By toastHeader = By.cssSelector(".toast-header");
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastHeader));
    }

    @Step("Verificar total del carrito")
    public void validarTotalCarrito() {
        // En lugar de esperar a que sea invisible solo, forzamos el cierre si el botón
        // está disponible
        By btnCerrarToast = By.cssSelector(".toast .close, .toast .btn-close, .toast-header button");
        wait.until(ExpectedConditions.elementToBeClickable(btnCerrarToast)).click();

        // Esperamos una fracción a que termine la animación de cierre para liberar el
        // área
        By toastHeader = By.cssSelector(".toast-header");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastHeader));

        click(iconCarrito);
        // Definir el localizador (con xpath robusto para manejar la vista pre-checkout)
        By totalLocator = By.xpath(
                "//tr[td[strong[contains(text(), 'Total')] or contains(normalize-space(), 'Total')] and not(contains(., 'Sub'))]/td[last()]");

        try {
            // Esperar visibilidad del valor
            WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalLocator));

            String precioTotal = totalElement.getText();

            // Pintar en consola
            System.out.println("---------------------------------");
            System.out.println("VALOR TOTAL: " + precioTotal);
            System.out.println("---------------------------------");

        } catch (Exception e) {
            System.err.println("No se encontró el total. Verifica que el locator aplique para la página de Checkout.");
            throw e;
        }
    }

    @Step("Eliminar producto del carrito")
    public void eliminarProducto() {
        // Usamos espera explícita para asegurar que el botón de eliminar sea clickeable
        WebElement btnRemove = wait.until(ExpectedConditions.elementToBeClickable(eliminarProducto));
        btnRemove.click();
        System.out.println("Producto eliminado del carrito exitosamente.");
    }

}

package tech.fertavora.gradle.tests.saucedemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.CartPage;
import tech.fertavora.pageobjects.saucedemo.CheckoutPage;
import tech.fertavora.pageobjects.saucedemo.ProductDetailsPage;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTests extends BaseTest {
    private static final String EXPECTED_PRODUCT_TITLE = "Sauce Labs Backpack";
    private static final String CART_EMPTY_ERROR = "Shopping cart is not empty!";
    private static final String CHECKOUT_PAGE_ERROR = "Checkout page is not displayed!";

    private CartPage cartPage;

    @BeforeEach
    public void cartPage(){
        cartPage = new CartPage(driver);
        cartPage.getTo();
    }

    @Test
    public void goToProductDetailsPage(){
        ProductDetailsPage productDetailsPage = cartPage.clickProductTitle();
        String actualItemTitle = productDetailsPage.getItemTitle();
        assertText(actualItemTitle, EXPECTED_PRODUCT_TITLE);
    }

    @Test
    public void removesProduct(){
        Boolean isCartEmpty = cartPage.clickRemoveButton().isCartEmpty();
        assertTrue(isCartEmpty, CART_EMPTY_ERROR);
    }

    @Test
    public void verifyContinueShopping(){
        ProductsPage productsPage = cartPage.clickContinueShopping();
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, EXPECTED_PRODUCT_TITLE);
    }

    @Test
    public void verifyCheckout(){
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        Boolean isCheckoutDisplayed = checkoutPage.isCheckoutDisplayed();
        assertTrue(isCheckoutDisplayed, CHECKOUT_PAGE_ERROR);
    }
}

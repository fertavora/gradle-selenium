package tech.fertavora.gradle.tests.saucedemo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.CartPage;
import tech.fertavora.pageobjects.saucedemo.CheckoutPage;
import tech.fertavora.pageobjects.saucedemo.ProductDetailsPage;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

public class CartTests extends BaseTest {
    private static final String EXPECTED_PRODUCT_TITLE = "Sauce Labs Backpack";
    private static final String CART_EMPTY_ERROR = "Shopping cart is not empty!";
    private static final String CHECKOUT_PAGE_ERROR = "Checkout page is not displayed!";

    private CartPage cartPage;

    @BeforeMethod
    public void productsPage(){
        cartPage = new CartPage(driver);
        cartPage.getTo();
    }

    @Test(description = "User goes to Product details from Cart page")
    public void goToProductDetailsPage(){
        ProductDetailsPage productDetailsPage = cartPage.clickProductTitle();
        String actualItemTitle = productDetailsPage.getItemTitle();
        assertText(actualItemTitle, EXPECTED_PRODUCT_TITLE);
    }

    @Test(description = "User removes a Product in the Cart page")
    public void removesProduct(){
        Boolean isCartEmpty = cartPage.clickRemoveButton().isCartEmpty();
        Assert.assertTrue(isCartEmpty, CART_EMPTY_ERROR);
    }

    @Test(description = "User resumes shopping from Cart page")
    public void verifyContinueShopping(){
        ProductsPage productsPage = cartPage.clickContinueShopping();
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, EXPECTED_PRODUCT_TITLE);
    }

    @Test(description = "User proceeds to checkout from Cart page")
    public void verifyCheckout(){
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        Boolean isCheckoutDisplayed = checkoutPage.isCheckoutDisplayed();
        Assert.assertTrue(isCheckoutDisplayed, CHECKOUT_PAGE_ERROR);
    }
}

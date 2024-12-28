package tech.fertavora.gradle.tests.saucedemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.CartPage;
import tech.fertavora.pageobjects.saucedemo.CheckoutPage;
import tech.fertavora.pageobjects.saucedemo.PaymentInformationPage;

public class CheckoutTests extends BaseTest {
    private static final String EXPECTED_PAYMENT_INFORMATION_TEXT = "SauceCard #31337";
    private static final String EXPECTED_SHIPPING_INFORMATION_TEXT = "Free Pony Express Delivery!";
    private static final String EXPECTED_INVENTORY_ITEM_PRICE_TEXT = "$29.99";
    private static final String EXPECTED_SUBTOTAL_PRICE_TEXT = "Item total: $29.99";
    private static final String EXPECTED_COMPLETE_CHECKOUT_TEXT = "Thank you for your order!";

    private CartPage cartPage;

    @BeforeEach
    public void cartPage(){
        cartPage = new CartPage(driver);
        cartPage.getTo();
    }

    @Test
    public void checkoutTest(){
        CheckoutPage checkoutPage = cartPage.clickCheckout();
        PaymentInformationPage paymentInformationPage = checkoutPage.enterFirstName("John")
                .enterLastName("Doe")
                .enterZipCode("90210")
                .clickContinue();

        assertText(paymentInformationPage.getPaymentInformationText(), EXPECTED_PAYMENT_INFORMATION_TEXT);
        assertText(paymentInformationPage.getInventoryItemPriceText(), EXPECTED_INVENTORY_ITEM_PRICE_TEXT);
        assertText(paymentInformationPage.getShippingInformationText(), EXPECTED_SHIPPING_INFORMATION_TEXT);
        assertText(paymentInformationPage.getSubtotalText(), EXPECTED_SUBTOTAL_PRICE_TEXT);

        String completeCheckout = paymentInformationPage.clickFinishButton()
                .getCompleteHeaderText();

        assertText(completeCheckout, EXPECTED_COMPLETE_CHECKOUT_TEXT);
    }
}

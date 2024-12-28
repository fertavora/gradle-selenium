package tech.fertavora.gradle.tests.saucedemo;

import org.junit.jupiter.api.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

public class LoginTests extends BaseTest {
    private static final String EXPECTED_PRODUCT_TITLE = "Sauce Labs Backpack";
    private static final String EXPECTED_USER_LOCKED_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";

    private ProductsPage productsPage;

    @Test
    public void successfulLogin() {
        loginPage.signIn("standard_user","secret_sauce");
        productsPage = new ProductsPage(driver);
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, EXPECTED_PRODUCT_TITLE);
    }

    @Test
    public void invalidLogin(){
        loginPage.signIn("locked_out_user","secret_sauce");
        String actualErrorMessage = loginPage.getErrorMessage();
        assertText(actualErrorMessage, EXPECTED_USER_LOCKED_MESSAGE);
    }

    @Test
    public void slowLogin(){
        loginPage.signIn("performance_glitch_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, EXPECTED_PRODUCT_TITLE);
    }

}

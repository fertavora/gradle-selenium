package tech.fertavora.gradle.tests.saucedemo;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import tech.fertavora.gradle.tests.BaseTest;
import tech.fertavora.pageobjects.saucedemo.ProductsPage;

@Epic("SauceDemo Tests")
@Feature("User Authentication")
@Link(name = "360449/SauceDemo", type="myLink")
public class LoginTests extends BaseTest {
    private static final String EXPECTED_PRODUCT_TITLE = "Sauce Labs Backpack";
    private static final String EXPECTED_USER_LOCKED_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";

    private ProductsPage productsPage;

    @Story("Successfull authentication")
//    @TmsLink("TC-1")
//    @Issue("TA-1")
    @Test(description = "User can succesfully sign in and lands in homepage.")
    public void successfulLogin() {
        loginPage.signIn("standard_user","secret_sauce");
        productsPage = new ProductsPage(driver);
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, EXPECTED_PRODUCT_TITLE);
    }

    @Test(description = "User gets error message on invalid login.")
    public void invalidLogin(){
        loginPage.signIn("locked_out_user","secret_sauce");
        String actualErrorMessage = loginPage.getErrorMessage();
        assertText(actualErrorMessage, EXPECTED_USER_LOCKED_MESSAGE);
    }

    @Test(description = "User takes to long to login.")
    public void slowLogin(){
        loginPage.signIn("performance_glitch_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        String actualFirstLinkText = productsPage.getLinkTextByIndex(0);
        assertText(actualFirstLinkText, EXPECTED_PRODUCT_TITLE);
    }

}

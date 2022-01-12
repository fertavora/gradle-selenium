package tech.fertavora.pageobjects.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tech.fertavora.pageobjects.common.BasePage;

public class CartPage extends BasePage {
    private final By buttonCheckout = By.cssSelector("[data-test=\"checkout\"]");
    private final By buttonRemove = By.cssSelector("[data-test^=\"remove-\"]");
    private final By buttonContinueShopping = By.cssSelector("[data-test=\"continue-shopping\"]");
    private final By productLink = By.cssSelector("div[class=\"inventory_item_name\"]");
    private final By sectionItems = By.cssSelector("div[class=\"cart_item\"]");

    public CartPage(WebDriver driver){
        super(driver);
    }

    @Step("Test runner loads the Cart Page")
    public CartPage getTo(){
        goToUrl(System.getenv("ENV_BASE_URL"));
        setCookie(driver,"session-username", "standard_user");
        setKeyValueOnLocalStorage("cart-contents", "[4]");
        goToUrl(System.getenv("ENV_BASE_URL")  + "/cart.html");
        return this;
    }

    @Step("User clicks the product title in the Cart Page")
    public ProductDetailsPage clickProductTitle(){
        waitForClickable(productLink).click();
        return new ProductDetailsPage(driver);
    }

    @Step("User clicks the Remove button in the Cart Page")
    public CartPage clickRemoveButton(){
        waitForClickable(buttonRemove).click();
        return this;
    }

    @Step("User clicks the Continue Shopping button in the Cart Page")
    public ProductsPage clickContinueShopping(){
        waitForClickable(buttonContinueShopping).click();
        return new ProductsPage(driver);
    }

    @Step("User clicks the Checkout button in the Cart Page")
    public CheckoutPage clickCheckout(){
        waitForClickable(buttonCheckout).click();
        return new CheckoutPage(driver);
    }

    @Step("Test runner returns whether cart is empty or not")
    public Boolean isCartEmpty(){
        ExpectedCondition<Boolean> isCartEmpty = ExpectedConditions.invisibilityOfElementLocated(sectionItems);
        return isCartEmpty.apply(driver);
    }
}

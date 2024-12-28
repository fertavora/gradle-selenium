package tech.fertavora.pageobjects.saucedemo;

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

    public CartPage getTo(){
        goToUrl(System.getenv("ENV_BASE_URL"));
        setCookie(driver,"session-username", "standard_user");
        setKeyValueOnLocalStorage("cart-contents", "[4]");
        goToUrl(System.getenv("ENV_BASE_URL")  + "/cart.html");
        return this;
    }

    public ProductDetailsPage clickProductTitle(){
        waitForClickable(productLink).click();
        return new ProductDetailsPage(driver);
    }

    public CartPage clickRemoveButton(){
        waitForClickable(buttonRemove).click();
        return this;
    }

    public ProductsPage clickContinueShopping(){
        waitForClickable(buttonContinueShopping).click();
        return new ProductsPage(driver);
    }

    public CheckoutPage clickCheckout(){
        waitForClickable(buttonCheckout).click();
        return new CheckoutPage(driver);
    }

    public Boolean isCartEmpty(){
        ExpectedCondition<Boolean> isCartEmpty = ExpectedConditions.invisibilityOfElementLocated(sectionItems);
        return isCartEmpty.apply(driver);
    }
}

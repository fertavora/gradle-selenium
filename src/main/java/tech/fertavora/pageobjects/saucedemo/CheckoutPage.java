package tech.fertavora.pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class CheckoutPage extends BasePage {
    private final By containerCheckoutInfo = By.id("checkout_info_container");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public Boolean isCheckoutDisplayed(){
        return driver.findElement(containerCheckoutInfo).isDisplayed();
    }
}

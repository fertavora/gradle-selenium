package tech.fertavora.pageobjects.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class CheckoutPage extends BasePage {
    private final By containerCheckoutInfo = By.id("checkout_info_container");
    private final By inputFirstName = By.id("first-name");
    private final By inputLastName = By.id("last-name");
    private final By inputZipCode = By.id("postal-code");
    private final By buttonContinue = By.id("continue");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public Boolean isCheckoutDisplayed(){
        return driver.findElement(containerCheckoutInfo).isDisplayed();
    }

    @Step("User fills the first name in the Checkout page")
    public CheckoutPage enterFirstName(String firstName){
        waitForVisibility(inputFirstName).sendKeys(firstName);
        return this;
    }

    @Step("User fills the last name in the Checkout page")
    public CheckoutPage enterLastName(String lastName){
        waitForVisibility(inputLastName).sendKeys(lastName);
        return this;
    }

    @Step("User fills the zip code in the Checkout page")
    public CheckoutPage enterZipCode(String zipCode){
        waitForVisibility(inputZipCode).sendKeys(zipCode);
        return this;
    }

    @Step("User clicks the Continue button in the Checkout page")
    public PaymentInformationPage clickContinue(){
        waitForClickable(buttonContinue).click();
        return new PaymentInformationPage(driver);
    }
}

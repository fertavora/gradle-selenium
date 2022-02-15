package tech.fertavora.pageobjects.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class PaymentInformationPage extends BasePage {
    private final By buttonFinish = By.id("finish");
    private final By textInventoryItemPrice = By.className("inventory_item_price");
    private final By textPaymmentInformation = By.cssSelector(".summary_value_label:nth-child(2)");
    private final By textShippingInformation = By.cssSelector(".summary_value_label:nth-child(4)");
    private final By textSubtotal = By.cssSelector(".summary_subtotal_label");

    public PaymentInformationPage(WebDriver driver){
        super(driver);
    }

    @Step("User clicks the Finish button in the Payment Information Page")
    public CheckoutCompletePage clickFinishButton(){
        waitForClickable(buttonFinish).click();
        return new CheckoutCompletePage(driver);
    }

    @Step("Test runner gets the inventory item price text")
    public String getInventoryItemPriceText(){
        return waitForVisibility(textInventoryItemPrice).getText();
    }

    @Step("Test runner gets the payment information text")
    public String getPaymentInformationText(){
        return waitForVisibility(textPaymmentInformation).getText();
    }

    @Step("Test runner gets the shipping information text")
    public String getShippingInformationText(){
        return waitForVisibility(textShippingInformation).getText();
    }

    @Step("Test runner gets the subtotal text")
    public String getSubtotalText(){
        return waitForVisibility(textSubtotal).getText();
    }
}

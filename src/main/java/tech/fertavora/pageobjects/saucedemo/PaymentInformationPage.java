package tech.fertavora.pageobjects.saucedemo;

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

    public CheckoutCompletePage clickFinishButton(){
        waitForClickable(buttonFinish).click();
        return new CheckoutCompletePage(driver);
    }

    public String getInventoryItemPriceText(){
        return waitForVisibility(textInventoryItemPrice).getText();
    }

    public String getPaymentInformationText(){
        return waitForVisibility(textPaymmentInformation).getText();
    }

    public String getShippingInformationText(){
        return waitForVisibility(textShippingInformation).getText();
    }

    public String getSubtotalText(){
        return waitForVisibility(textSubtotal).getText();
    }
}

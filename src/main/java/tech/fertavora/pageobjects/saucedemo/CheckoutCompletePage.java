package tech.fertavora.pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class CheckoutCompletePage extends BasePage {

    private final By completeHeader = By.cssSelector("h2.complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompleteHeaderText() {
        return waitForVisibility(completeHeader).getText();
    }
}

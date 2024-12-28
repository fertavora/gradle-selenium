package tech.fertavora.pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tech.fertavora.pageobjects.common.BasePage;

public class LoginPage extends BasePage {
    private final By inputUsername = By.cssSelector("[data-test=\"username\"]");
    private final By inputPassword = By.cssSelector("[data-test=\"password\"]");
    private final By buttonLogin = By.cssSelector("[data-test=\"login-button\"]");
    private final By textErrorMessage = By.cssSelector("[data-test=\"error\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage getTo(){
        goToUrl(System.getenv("ENV_BASE_URL"));
        return this;
    }

    public void signIn(String username, String password){
        this.enterUsername(username)
            .enterPassword(password)
            .clickButtonLogin();
    }

    public String getErrorMessage(){
        return waitForVisibility(textErrorMessage).getText();
    }

    private LoginPage enterUsername(String username) {
        waitForVisibility(inputUsername).sendKeys(username);
        return this;
    }

    private LoginPage enterPassword(String password) {
        waitForVisibility(inputPassword).sendKeys(password);
        return this;
    }

    private void clickButtonLogin(){
        waitForVisibility(buttonLogin).click();
    }
}

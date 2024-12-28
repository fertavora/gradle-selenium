package tech.fertavora.gradle.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tech.fertavora.pageobjects.saucedemo.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {
    private static final String ARGUMENT_START_MAXIMIZED = "start-maximized";
    private static final String ARGUMENT_WINDOWS_SIZE = "--window-size=1920,1080";
    private static final String ARGUMENT_DISABLE_INFOBARS = "disable-infobars";

    protected static WebDriver driver;
    protected static LoginPage loginPage;

    @BeforeAll
    public static void startBrowser() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                ARGUMENT_WINDOWS_SIZE,
                ARGUMENT_START_MAXIMIZED,
                ARGUMENT_DISABLE_INFOBARS
        );
        driver = new ChromeDriver(chromeOptions);
        loginPage = new LoginPage(driver);
    }

    @BeforeEach
    public void loadSaucedemo(){
        loginPage.getTo();
    }

    @AfterAll
    protected static void closeBrowser(){
        driver.quit();
    }

    protected void assertText(String actual, String expected){
        assertEquals(actual, expected);
    }

}
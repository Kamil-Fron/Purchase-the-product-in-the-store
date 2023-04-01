package shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;


import java.time.Duration;

public class MainSteps {

    private WebDriver driver;

    @Given("I am logged in to the same user account from task_1: {string} with password {string}")
    public void iAmlogged(String email, String password){

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");

        MainPage mainPage = new MainPage(driver);
        mainPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(email, password);
    }

    @When("I choose the Hummingbird Printed Sweater to purchase")
    public void iChooseTheHummingbirdPrintedSweaterToPurchase() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToMainPage();
        mainPage.goToProductPage();

        ProductPage productPage = new ProductPage(driver);
       Boolean czyTak = productPage.isDiscount20Precent();
        Assertions.assertTrue(productPage.isDiscount20Precent(), "Produkt nie ma 20% rabatu");
    }

    @And("select size {string}")
    public void selectSize(String size) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ProductPage productPage = new ProductPage(driver);
        productPage.selectSize(size);
    }
}

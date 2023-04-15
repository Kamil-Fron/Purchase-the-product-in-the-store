package shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.awt.*;
import java.io.IOException;
import java.time.Duration;

public class MainSteps {

    private WebDriver driver;
    CreateOrderPage createOrderPage;
    ConfirmedOrderPage confirmedOrderPage;

    String orderNumberTextFromConfirmedPage;
    String totalPriceFromConfirmedPage;

    @Given("I am logged in to the same user account from task_1: login {string} with password {string}")
    public void iAmlogged(String email, String password) {

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
        Assertions.assertTrue(productPage.isDiscount20Precent(), "Produkt nie ma 20% rabatu");
    }

    @And("select size {string}")
    public void selectSize(String size) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ProductPage productPage = new ProductPage(driver);
        productPage.selectSize(size);
    }

    @And("add {string} items to the cart")
    public void addItemsToTheCart(String itemCount) {
        ProductPage productPage = new ProductPage(driver);
        productPage.selectHowManyItem(itemCount);

        productPage.addToCart();
    }

    @And("proceed to checkout")
    public void proceedToCheckout() {
        ProductPage productPage = new ProductPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        productPage.goToTheCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.goToCreateOrderPage();
    }

    @And("confirm the address")
    public void confirmTheAddress() {
        CreateOrderPage createOrderPage = new CreateOrderPage(driver);
        createOrderPage.setConfirmAddress();
    }

    @And("choose delivery option")
    public void choosePrestaShopPickUpInStoreOption() {
        CreateOrderPage createOrderPage = new CreateOrderPage(driver);
        createOrderPage.setConfirmDelivery();
    }

    @And("select payment method Pay by Check")
    public void selectPaymentMethod() {
        CreateOrderPage createOrderPage = new CreateOrderPage(driver);
        createOrderPage.choicePaymentMethod();
    }

    @And("click on PLACE ORDER and take screenshot")
    public void clickOnPLACEORDER() throws IOException, AWTException {
        CreateOrderPage createOrderPage = new CreateOrderPage(driver);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        createOrderPage.goToTheConfirmedOrderPage();
        //Create screenshot
        ConfirmedOrderPage confirmedOrderPage = new ConfirmedOrderPage(driver);
        confirmedOrderPage.createSnapshot();
    }

    @Then("I should see a confirmation of the order with the correct total amount")
    public void iShouldSeeAConfirmationOfTheOrder() {
        ConfirmedOrderPage confirmedOrderPage = new ConfirmedOrderPage(driver);

        this.orderNumberTextFromConfirmedPage = confirmedOrderPage.getOrderNumberText();
        this.totalPriceFromConfirmedPage = confirmedOrderPage.getTextTotalPrice();
    }

    @And("I should see the order in the order history with status {string} and the same total amount")
    public void iShouldSeeTheOrderInTheOrderHistory(String awaitingCheckPaymentText) {
        ConfirmedOrderPage confirmedOrderPage = new ConfirmedOrderPage(driver);
        confirmedOrderPage.goToMyAccountPage();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.goToHistoryOrder();

        HistoryOrderPage historyOrderPage = new HistoryOrderPage(driver);

        String textAwaitingCheckPayment = historyOrderPage.getTextAwaitingCheckPayment();
        String textPrice = historyOrderPage.getTextPrice();
        String textNumberOrder = historyOrderPage.getTextNumberOrder();

        Assertions.assertTrue(textPrice.equals(totalPriceFromConfirmedPage), "The total price is not the same");
        Assertions.assertTrue(textAwaitingCheckPayment.equals(awaitingCheckPaymentText), "The text is not the same");
        Assertions.assertTrue(textNumberOrder.equals(orderNumberTextFromConfirmedPage), "The order number is not the same");

        MainPage mainPage = new MainPage(driver);
        mainPage.closeBrowse();
    }
}

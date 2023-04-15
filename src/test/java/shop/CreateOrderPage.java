package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrderPage {
    private final WebDriver driver;

    @FindBy(name = "confirm-addresses")
    private WebElement confirmAddressBtn;

    @FindBy(name = "confirmDeliveryOption")
    private WebElement confirmDeliveryBtn;

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckChb;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsOfServiceAgreeChb;

    @FindBy(css = "#payment-confirmation button[type='submit']")
    private WebElement placeOrderBtn;


    public CreateOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    public void setConfirmAddress(){
        confirmAddressBtn.click();
    }

    public void setConfirmDelivery(){
        confirmDeliveryBtn.click();
    }

    public void choicePaymentMethod(){
        payByCheckChb.click();
        termsOfServiceAgreeChb.click();

    }

    public ConfirmedOrderPage goToTheConfirmedOrderPage(){
        placeOrderBtn.click();
        return new ConfirmedOrderPage(driver);
    }
}

package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
    private final WebDriver driver;

    @FindBy(className = "cart-detailed-actions")
    private WebElement checkoutBtn;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CreateOrderPage goToCreateOrderPage(){
        checkoutBtn.click();
        return new CreateOrderPage(driver);
    }
}

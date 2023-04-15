package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryOrderPage {
    private final WebDriver driver;

    @FindBy(css = "tbody tr:nth-child(1) th")
    private WebElement numberOrderText;

    @FindBy(css = "tbody tr:first-child td:nth-child(5)")
    private WebElement awaitingCheckPaymentText;

    @FindBy(css = "tbody tr:first-child td:nth-child(3)")
    private WebElement priceText;


    public HistoryOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getTextNumberOrder(){
       return numberOrderText.getText();
    }

    public String getTextAwaitingCheckPayment(){
        return awaitingCheckPaymentText.getText();
    }

    public String getTextPrice(){
        return priceText.getText();
    }

}

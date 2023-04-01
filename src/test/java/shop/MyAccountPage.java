package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    private final WebDriver driver;

    @FindBy(id = "addresses-link")
    WebElement addressesBtn;

    @FindBy(id = "history-link")
    WebElement historyOrderBtn;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public HistoryOrderPage goToHistoryOrder(){
        historyOrderBtn.click();
        return new HistoryOrderPage(driver);
    }

}

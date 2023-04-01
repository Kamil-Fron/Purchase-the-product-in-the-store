package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HistoryOrderPage {
    private final WebDriver driver;


    public HistoryOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

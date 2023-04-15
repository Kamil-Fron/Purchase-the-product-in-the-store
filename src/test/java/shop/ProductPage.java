package shop;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;

    @FindBy(className = "discount-percentage")
    WebElement discountPriceInfo;

    @FindBy(id = "group_1")
    private WebElement sizeGroupInput;

    @FindBy(name = "qty")
    private WebElement itemCountInput;

    @FindBy(className = "add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = "a[href*='controller=cart'][class*='btn-primary']")
    private WebElement goToTheCartBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDiscount20Precent(){
        String precent = discountPriceInfo.getText().replaceAll("\\D+","");
        return discountPriceInfo.isDisplayed()&&(precent.equals("20"));
    }

    public void selectSize(String size){
        sizeGroupInput.click();
        sizeGroupInput.sendKeys(size);
        sizeGroupInput.click();
       // sizeGroup.submit();
    }

    public void selectHowManyItem(String itemCount){
        itemCountInput.click();
        itemCountInput.sendKeys(Keys.CONTROL + "a");
        itemCountInput.sendKeys(itemCount);
    }

    public void addToCart(){
        addToCartBtn.click();
    }

    public ShoppingCartPage goToTheCart(){
        goToTheCartBtn.click();
        return new ShoppingCartPage(driver);
    }
}

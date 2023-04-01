package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    private final WebDriver driver;

    @FindBy(className = "discount-percentage")
    WebElement discountPrice;

    @FindBy(id = "group_1")
    WebElement sizeGroup;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDiscount20Precent(){
        String precent = discountPrice.getText().replaceAll("\\D+","");
        return discountPrice.isDisplayed()&&(precent.equals("20"));
    }

    public void selectSize(String size){
        sizeGroup.click();
        sizeGroup.sendKeys(size);
        sizeGroup.click();
       // sizeGroup.submit();
    }
}

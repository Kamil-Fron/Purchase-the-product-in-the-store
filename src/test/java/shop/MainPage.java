package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver driver;

    @FindBy(css = "a[title='Log in to your customer account']")
    private WebElement loginBtn;

    @FindBy(css = "a.account")
    private WebElement accountBtn;

    @FindBy(id = "_desktop_logo")
    private WebElement logo;

    @FindBy(css = "img[src*='img/p/2/1/21-home_default.jpg']")
    private WebElement hummingbirdProduct;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage goToLoginPage(){
        loginBtn.click();
        return new LoginPage(driver);
    }

    public ProductPage goToProductPage(){
        hummingbirdProduct.click();
        return new ProductPage(driver);
    }

    public MainPage goToMainPage(){
        logo.click();
        return new MainPage(driver);
    }

    public MyAccountPage goToTheMyAccountPage (){
        accountBtn.click();
        return new MyAccountPage(driver);
    }
    public void closeBrowse (){
        driver.close();
    }

}

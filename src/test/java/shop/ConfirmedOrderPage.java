package shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConfirmedOrderPage {

    private final WebDriver driver;

    @FindBy(id = "order-reference-value")
    private WebElement orderNumberText;

    @FindBy(css = "div.qty > div.row > div.col-xs-4.text-sm-center.text-xs-right.bold")
    private WebElement totalPriceText;

    @FindBy(css = "a.account")
    private WebElement accountBtn;

    public ConfirmedOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void createSnapshot() throws AWTException, IOException {
        Robot robot = new Robot();
        BufferedImage screenshot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        File output = new File("screenshot.png");
        ImageIO.write(screenshot, "png", output);
    }

    public String getOrderNumberText(){
        String orderNumber = orderNumberText.getText();
        int startIndex = orderNumber.length() - 9; // określamy pozycję początkową
        return orderNumber.substring(startIndex); // wyodrębniamy fragment tekstu;
    }

    public String getTextTotalPrice(){
        return totalPriceText.getText();
    }

    public MyAccountPage goToMyAccountPage(){
        accountBtn.click();
        return new MyAccountPage(driver);
    }

}

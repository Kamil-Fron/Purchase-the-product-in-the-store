package shop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/features/shipping.feature", plugin = {"pretty", "html:out.html"})
public class ShippingTest {
}

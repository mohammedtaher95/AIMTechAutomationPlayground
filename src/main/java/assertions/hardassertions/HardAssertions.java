package assertions.hardassertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.Assertion;

public class HardAssertions {

    private Assertion assertion;
    private WebDriver driver;

    public HardAssertions(WebDriver driver) {
        this.driver = driver;
        this.assertion = new Assertion();
    }

    public ElementAssertions element(By by) {
        return new ElementAssertions(assertion, by, driver);
    }
}

package assertions.hardassertions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.Assertion;

public class ElementAssertions {

    private Assertion assertion;
    private WebDriver driver;
    private By by;

    public ElementAssertions(Assertion assertion, By by, WebDriver driver) {
        this.assertion = assertion;
        this.driver = driver;
        this.by = by;
    }

//    public Validations text() {
//        String actual = driver.findElement(by).getText();
//        return new Validations(actual, assertion);
//    }
//
//    public Validations attribute(String attributeName) {
//        String actual = driver.findElement(by).getAttribute(attributeName);
//        return new Validations(actual, assertion);
//    }

    public void isDisplayed() {
        try{
            assertion.assertTrue(driver.findElement(by).isDisplayed(),
                    "Element: " + by.toString().split(":", 2)[1] + " is Displayed");
        } catch (AssertionError e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is NOT Displayed");
            throw e;
        }

    }

    public void isHidden() {
        try{
            assertion.assertTrue(!driver.findElement(by).isDisplayed(),
                    "Element: " + by.toString().split(":", 2)[1] + " is Displayed");
        } catch (NoSuchElementException e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is NOT Displayed");
        }
    }

    public void isEnabled() {
        try{
            assertion.assertTrue(driver.findElement(by).isEnabled(),
                    "Element: " + by.toString().split(":", 2)[1] + " is Enabled");
        } catch (AssertionError e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is NOT Enabled");
            throw e;
        }
    }

    public void isDisabled() {
        try{
            assertion.assertTrue(!driver.findElement(by).isEnabled(),
                    "Element: " + by.toString().split(":", 2)[1] + " is NOT Enabled");
        } catch (AssertionError e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is Enabled");
            throw e;
        }
    }

    public void isSelected() {
        try{
            assertion.assertTrue(driver.findElement(by).isSelected(),
                    "Element: " + by.toString().split(":", 2)[1] + " is Selected");
        } catch (AssertionError e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is NOT Selected");
            throw e;
        }
    }

    public void isNotSelected() {
        try{
            assertion.assertTrue(!driver.findElement(by).isSelected(),
                    "Element: " + by.toString().split(":", 2)[1] + " is NOT Selected");
        } catch (AssertionError e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is Selected");
            throw e;
        }
    }

    public void isClickable() {
        try{
            assertion.assertTrue(driver.findElement(by).isEnabled(),
                    "Element: " + by.toString().split(":", 2)[1] + " is Clickable");
        } catch (AssertionError e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is NOT Clickable");
            throw e;
        }
    }

    public void isNotClickable() {
        try{
            assertion.assertTrue(!driver.findElement(by).isEnabled(),
                    "Element: " + by.toString().split(":", 2)[1] + " is NOT Clickable");
        } catch (AssertionError e) {
            System.out.println("Element: " + by.toString().split(":", 2)[1] + " is Clickable");
            throw e;
        }
    }

}

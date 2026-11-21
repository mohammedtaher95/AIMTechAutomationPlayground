package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationPage {

    private Driver driver;
//    private Actions actions;
//    private WebDriverWait wait;
//    private JavascriptExecutor js;

    By pageTitle = By.xpath("//h2[@class=\"title text-center\"]");
    By passwordField = By.id("password");
    By firstNameField = By.id("first_name");
    By lastNameField = By.id("last_name");
    By addressField = By.id("address1");
    By countryField = By.id("country");
    By stateField = By.id("state");
    By cityField = By.id("city");
    By zipCode = By.id("zipcode");
    By mobileNumber = By.id("mobile_number");
    By createAccountButton = By.xpath("//button[@data-qa=\"create-account\"]");

    /******************************* Constructor **********************************/

    public RegistrationPage(Driver driver) {
        this.driver = driver;
//        actions = new Actions(driver);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        js = (JavascriptExecutor) driver;
    }

    /******************************* Assertions ***********************************/

    @Step("Check that Registration Page should be loaded successfully")
    public RegistrationPage checkThatRegistrationPageShouldBeLoaded() {
        // wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.element().waitForElementToBeVisible(passwordField, Duration.ofSeconds(10));
        Assert.assertTrue(driver.element().isDisplayed(pageTitle));
        return this;
    }

    /******************************** Actions ************************************/

    @Step("Fill in the registration form")
    public RegistrationPage fillInRegistrationForm() {
        driver.element().fillField(passwordField, "12345678");
        driver.element().fillField(firstNameField, "Mohammed");
        driver.element().fillField(lastNameField, "Taher");
        driver.element().fillField(addressField, "Alexandria");
        driver.element().selectFromDropdownByValue(countryField, "Canada");

        driver.element().fillField(stateField, "Alex");
        driver.element().fillField(cityField, "Alexandria");

        driver.element().fillField(zipCode, "34597");
        driver.element().fillField(mobileNumber, "01234567893");
        return this;
    }

    @Step("Click on Create Account button")
    public AccountCreationPage clickOnCreateAccountButton() {
        driver.element().click(createAccountButton);
        return new AccountCreationPage(driver);
    }
}

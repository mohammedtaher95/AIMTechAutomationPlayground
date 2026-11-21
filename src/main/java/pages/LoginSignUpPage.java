package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSignUpPage {

    private Driver driver;

    By signUpName = By.xpath("//input[@data-qa=\"signup-name\"]");
    By signUpEmailAddress = By.xpath("//input[@data-qa=\"signup-email\"]");
    By signUpButton = By.xpath("//button[@data-qa=\"signup-button\"]");
    By loginForm = By.cssSelector("div.login-form");
    By loginEmailField = By.xpath("//input[@data-qa=\"login-email\"]");
    By passwordField = By.name("password");
    By loginButton = By.xpath("//button[@data-qa=\"login-button\"]");
    By incorrectCredentialsError = By.xpath("//p[contains(text(),\"Your email or password is incorrect!\")]");

    /****************************** Constructor ************************************/

    public LoginSignUpPage(Driver driver) {
        this.driver = driver;
    }

    /****************************** Assertions ***********************************/

    @Step("Check that Login/SignUp Page should be loaded successfully")
    public LoginSignUpPage checkThatLoginSignUpPageShouldBeLoaded() {
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/login"));
        Assert.assertTrue(driver.element().isDisplayed(signUpButton));
        return this;
    }

    /******************************* Actions *********************************/

    @Step("Fill SignUp form with name: {signUpName} and email: {signUpEmailAddress}")
    public LoginSignUpPage fillSignUpform(String signUpName, String signUpEmailAddress) {
        driver.element().fillField(this.signUpName, signUpName);
        driver.element().fillField(this.signUpEmailAddress, signUpEmailAddress);
        return this;
    }

    @Step("Click on SignUp button")
    public RegistrationPage clickOnSignUpButton() {
        driver.element().click(signUpButton);
        return new RegistrationPage(driver);
    }

    @Step("Fill Login form with email: {email} and password: {password}")
    public LoginSignUpPage fillLoginForm(String email, String password) {
        driver.element().fillField(loginEmailField, email);
        driver.element().fillField(passwordField, password);
        return this;
    }

    @Step("Click on Login button")
    public Homepage clickOnLoginButton() {
        driver.element().click(loginButton);
        return new Homepage(driver);
    }
}

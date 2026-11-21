package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Homepage {

    private Driver driver;


    By sliderCarousel = By.xpath("(//div[@id=\"slider-carousel\"])[1]");
    By loginLink = By.xpath("//a[@href=\"/login\"]");
    By logoutLink = By.xpath("//a[@href=\"/logout\"]");
    By deleteAccountLink = By.xpath("//a[@href=\"/delete_account\"]");
    By productsLink = By.xpath("//a[@href=\"/products\"]");

    /***************************** Constructor ****************************/

    public Homepage(Driver driver) {
        this.driver = driver;
    }

    /****************************** Assertions ***************************/

    @Step("Check that Home Page should be loaded successfully")
    public Homepage checkThatHomePageShouldBeLoaded () {
        driver.element().waitForElementToBeVisible(sliderCarousel, Duration.ofSeconds(10));
        Assert.assertTrue(driver.browser().getPageTitle().equals("Automation Exercise"));
        // Assert.assertTrue(driver.element().isDisplayed(sliderCarousel));
        driver.expectThat().element(sliderCarousel).isDisplayed();
        return this;
    }

    @Step("Check that user should be logged in")
    public Homepage checkThatUserShouldBeLoggedIn(){
        driver.element().waitForElementToBeVisible(logoutLink, Duration.ofSeconds(10));
        driver.element().waitForElementToBeVisible(deleteAccountLink, Duration.ofSeconds(10));
        driver.expectThat().element(logoutLink).isDisplayed();
        driver.expectThat().element(deleteAccountLink).isDisplayed();
        driver.expectThat().element(loginLink).isHidden();
        return this;
    }

    @Step("Check that user should be logged out")
    public Homepage checkThatUserShouldBeLoggedOut(){
        driver.element().waitForElementToBeVisible(loginLink, Duration.ofSeconds(10));
        Boolean isHidden = driver.element().waitForElementToBeInvisible(deleteAccountLink, Duration.ofSeconds(10));
        Assert.assertTrue(isHidden);
        driver.expectThat().element(deleteAccountLink).isHidden();
        driver.expectThat().element(logoutLink).isHidden();
        driver.expectThat().element(loginLink).isDisplayed();
         return this;
    }

    /***************************** Actions ******************************/

    @Step("Click on Login link")
    public LoginSignUpPage clickOnLoginLink() {
        driver.element().click(loginLink);
        return new LoginSignUpPage(driver);
    }

    @Step("Click on Logout link")
    public LoginSignUpPage clickOnLogoutLink() {
        driver.element().click(logoutLink);
        return new LoginSignUpPage(driver);
    }

    @Step("Click on Delete Account link")
    public AccountDeletionPage clickOnDeleteAccountLink() {
        driver.element().click(deleteAccountLink);
        return new AccountDeletionPage(driver);
    }

    @Step("Click on Products link")
    public ProductsListPage clickOnProductsLink() {
        driver.element().click(productsLink);
        return new ProductsListPage(driver);
    }

}

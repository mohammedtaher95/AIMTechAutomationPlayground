package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;

public class AccountCreationPage {

    private Driver driver;


    By successMessage = By.xpath("//h2[@data-qa=\"account-created\"]");
    By continueButton = By.xpath("//a[@data-qa=\"continue-button\"]");

    public AccountCreationPage(Driver driver) {
        this.driver = driver;
    }

    /*********************************** Assertions ***********************************/

    @Step("Check that User should be navigated to Account Creation Success Page")
    public AccountCreationPage checkThatUserShouldBeNavigatedToCreationSuccessPage() {
        driver.element().waitForElementToBeVisible(successMessage, Duration.ofSeconds(10));
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/account_created"));
        System.out.println("Account Created, checking the success Message");
        Assert.assertEquals(driver.element().getTextOf(successMessage), "ACCOUNT CREATED!");
        return this;
    }

    /*********************************** Actions ************************************/

    @Step("Click on Continue button")
    public Homepage clickOnContinueButton() {
        driver.element().click(continueButton);
        return new Homepage(driver);
    }

}

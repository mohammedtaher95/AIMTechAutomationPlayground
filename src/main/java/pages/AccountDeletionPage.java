package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AccountDeletionPage {

    private Driver driver;


    By successMessage = By.xpath("//h2[@data-qa=\"account-deleted\"]");
    By continueButton = By.xpath("//a[@data-qa=\"continue-button\"]");

    public AccountDeletionPage(Driver driver) {
        this.driver = driver;
    }

    /*********************************** Assertions ***********************************/

    @Step("Check that User should be navigated to Account Deletion Page")
    public AccountDeletionPage checkThatUserShouldBeNavigatedToAccountDeletionPage() {
        driver.element().waitForElementToBeVisible(successMessage, Duration.ofSeconds(10));
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/delete_account"));
        Assert.assertEquals(driver.element().getTextOf(successMessage), "ACCOUNT DELETED!");
        return this;
    }

    /*********************************** Actions ************************************/

    @Step("Click on Continue button")
    public Homepage clickOnContinueButton() {
        driver.element().click(continueButton);
        return new Homepage(driver);
    }
}

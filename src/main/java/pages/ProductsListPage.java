package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductsListPage {

    private Driver driver;

    By allproductsHeaderText = By.cssSelector("h2.title.text-center");
    By productsList = By.cssSelector(".features_items");
    By firstProductName = By.xpath("(//div[@class=\"productinfo textcenter\"])[1]/p");
    By firstProductPrice = By.xpath("(//div[@class=\"productinfo text-center\"])[1]/h2");
    By viewProductButton = By.xpath("//a[@href=\"/product_details/1\"]");

    public ProductsListPage(Driver driver) {
        this.driver = driver;
    }

    /*********************************** Assertions ***********************************/

    @Step("Check that User should be navigated to Products List Page")
    public ProductsListPage checkThatUserShouldBeNavigatedToProductsListPage() {
        driver.element().waitForElementToBeVisible(allproductsHeaderText, Duration.ofSeconds(15));
        // Assert.assertTrue(driver.getCurrentUrl().contains("/products"));
        Assert.assertTrue(driver.element().isDisplayed(productsList));
        Assert.assertTrue(driver.element().isDisplayed(allproductsHeaderText));
        return this;
    }

    /*********************************** Actions ************************************/

    @Step("Click on View Product button of the first product")
    public ProductDetailsPage clickOnViewFirstProductButton() {
        driver.element().click(viewProductButton);
        return new ProductDetailsPage(driver);
    }

    /********************************* Getters ***********************************/

    @Step("Get the name of the first product")
    public String getFirstProductName() {
        return driver.element().getTextOf(firstProductName);
    }

    @Step("Get the price of the first product")
    public String getFirstProductPrice() {
        return driver.element().getTextOf(firstProductPrice);
    }
}

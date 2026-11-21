package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductDetailsPage {

    private Driver driver;

    // allproductsHeaderText = By.className("title text-center");
    By productInfo = By.cssSelector(".product-information");
    By productName = By.xpath("//div[@class=\"product-information\"]/h2");
    By productPrice = By.xpath("//div[@class=\"product-information\"]/span/span");
    By addToCartButton = By.xpath("//a[@href=\"/product_details/1\"]");

    public ProductDetailsPage(Driver driver) {
        this.driver = driver;
    }

    /*********************************** Assertions ***********************************/

    @Step("Check that User should be navigated to Product Details Page")
    public ProductDetailsPage checkThatUserShouldBeNavigatedToProductDetailsPage() {
        driver.element().waitForElementToBeVisible(productInfo, Duration.ofSeconds(15));
        Assert.assertTrue(driver.browser().getCurrentUrl().contains("/product_details/1"));
        return this;
    }

    @Step("Check that Product Name should be displayed as: {expectedProductName}")
    public ProductDetailsPage checkThatProductNameShouldBeDisplayed(String expectedProductName) {
        Assert.assertTrue(driver.element().isDisplayed(productName));
        Assert.assertEquals(driver.element().getTextOf(productName), expectedProductName);
        return this;
    }

    @Step("Check that Product Price should be displayed as: {expectedProductPrice}")
    public ProductDetailsPage checkThatProductPriceShouldBeDisplayed(String expectedProductPrice) {
        Assert.assertTrue(driver.element().isDisplayed(productPrice));
        Assert.assertEquals(driver.element().getTextOf(productPrice), expectedProductPrice);
        return this;
    }
}

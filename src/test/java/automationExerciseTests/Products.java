package automationExerciseTests;

import driverfactory.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.Homepage;
import pages.ProductDetailsPage;
import pages.ProductsListPage;
import java.time.Duration;

public class Products {

    public ThreadLocal<Driver> driverThreadLocal;
    // Driver driver;
    WebDriverWait wait;
    Homepage homepage;
    ProductsListPage products;
    ProductDetailsPage productDetailsPage;

    @BeforeClass
    public void setUp() {
        driverThreadLocal = new ThreadLocal<>();
        driverThreadLocal.set(new Driver());
        // driverThreadLocal.get().getDriver().navigate().to("https://automationexercise.com");
        wait = new WebDriverWait(driverThreadLocal.get().getDriver(), Duration.ofSeconds(10));
    }

    @Test
    public void userCanCheckFirstProduct() {
//        products = new ProductsListPage(driver);
//        productDetailsPage = new ProductDetailsPage(driver);
//
//        homepage.clickOnProductsLink();
//        products.checkThatUserShouldBeNavigatedToProductsListPage();
//        String productName = products.getFirstProductName();
//        String productPrice = products.getFirstProductPrice();
//
//        products.clickOnViewFirstProductButton();
//
//        productDetailsPage.checkThatUserShouldBeNavigatedToProductDetailsPage();
//        productDetailsPage.checkThatProductNameShouldBeDisplayed(productName);
//        productDetailsPage.checkThatProductPriceShouldBeDisplayed(productPrice);

        new Homepage(driverThreadLocal.get()).checkThatHomePageShouldBeLoaded()
                .clickOnProductsLink().checkThatUserShouldBeNavigatedToProductsListPage();

        String productName = new ProductsListPage(driverThreadLocal.get()).getFirstProductName();
        String productPrice = new ProductsListPage(driverThreadLocal.get()).getFirstProductPrice();

        new ProductsListPage(driverThreadLocal.get()).clickOnViewFirstProductButton()
                .checkThatUserShouldBeNavigatedToProductDetailsPage()
                .checkThatProductNameShouldBeDisplayed(productName)
                .checkThatProductPriceShouldBeDisplayed(productPrice);
    }

    @AfterClass
    public void tearDown() {
        driverThreadLocal.get().getDriver().quit();
    }
}

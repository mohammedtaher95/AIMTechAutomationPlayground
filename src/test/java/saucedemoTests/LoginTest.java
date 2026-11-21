package saucedemoTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        // driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void userCanLoginWithValidCredentials() {

        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("header_container"))));
        // Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User is not redirected to the inventory page after login.");
        // Assert.assertTrue(driver.findElement(By.id("header_container")).isDisplayed(), "Header container is not displayed.");
        Assert.assertEquals(driver.findElement(By.className("title")).getText(), "Products", "Page title is not 'PRODUCTS'.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

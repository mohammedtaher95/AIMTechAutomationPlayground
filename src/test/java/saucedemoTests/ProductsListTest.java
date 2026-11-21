package saucedemoTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductsListTest {

    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeMethod
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
    public void userCanCheckProductsList() {

    }

    @Test
    public void userCanAddToCart() {

    }

    @AfterMethod
    public void tearDown() {

    }

}

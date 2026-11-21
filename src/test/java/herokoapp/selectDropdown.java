package herokoapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class selectDropdown {

    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        // driver.manage().window().maximize();
        driver.navigate().to("https://the-internet.herokuapp.com/");
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 2)
    public void userCanTestDropDown() {
        driver.findElement(By.xpath("//a[@href=\"/dropdown\"]")).click();
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByValue("1");
        Assert.assertTrue(dropdown.getFirstSelectedOption().getAttribute("value").contains("1"));

        dropdown.selectByVisibleText("Option 2");
        Assert.assertTrue(dropdown.getFirstSelectedOption().getAttribute("value").contains("2"));

        dropdown.selectByIndex(1);
        Assert.assertTrue(dropdown.getFirstSelectedOption().getAttribute("value").contains("1"));
        Assert.assertEquals(dropdown.getFirstSelectedOption().getAttribute("value"), "1");

//        Actions actions = new Actions(driver);
//        actions.
//        driver.findElement(By.id("dropdown")).click();
//        driver.findElement(By.xpath("//option[@value=\"1\"]")).click();


    }

    @Test(priority = 1)
    public void userCanDragAndDrop() {
        driver.findElement(By.xpath("//a[@href=\"/drag_and_drop\"]")).click();
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        Assert.assertTrue(source.getText().contains("B"));

    }

    @AfterMethod
    public void tearDown() {
       driver.quit();
    }
}

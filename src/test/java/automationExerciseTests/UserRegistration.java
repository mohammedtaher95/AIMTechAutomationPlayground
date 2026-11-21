package automationExerciseTests;

import driverfactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;

public class UserRegistration {

    public ThreadLocal<Driver> driver;
    String email = "test" + Math.random() + "@gmail.com";

    @BeforeClass
    @Parameters({"browserName"})
    public void setUp(@Optional("chrome") String browser) {
        driver = new ThreadLocal<>();
        driver.set(new Driver(browser));
        driver.get().browser().navigateToUrl("https://automationexercise.com");
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void userCanCreateAccount() {
        new Homepage(driver.get()).checkThatHomePageShouldBeLoaded()
                .clickOnLoginLink()
                .checkThatLoginSignUpPageShouldBeLoaded()
                .fillSignUpform("Mohammed", email)
                .clickOnSignUpButton()
                .checkThatRegistrationPageShouldBeLoaded()
                .fillInRegistrationForm()
                .clickOnCreateAccountButton()
                .checkThatUserShouldBeNavigatedToCreationSuccessPage()
                .clickOnContinueButton()
                .checkThatHomePageShouldBeLoaded()
                .checkThatUserShouldBeLoggedIn();
    }

    @Test(priority = 2, dependsOnMethods = {"userCanCreateAccount"})
    public void userCanLogout() {
        new Homepage(driver.get()).clickOnLogoutLink()
            .checkThatLoginSignUpPageShouldBeLoaded();
    }

    @Test(priority = 3, dependsOnMethods = {"userCanLogout"})
    public void userCanLogin() {
        new LoginSignUpPage(driver.get())
                .fillLoginForm(email, "12345678")
                .clickOnLoginButton()
                .checkThatHomePageShouldBeLoaded()
                .checkThatUserShouldBeLoggedIn();
    }

    @Test(priority = 4, dependsOnMethods = {"userCanLogin"})
    public void userCanDeleteAccount() {
        new Homepage(driver.get()).clickOnDeleteAccountLink()
                .checkThatUserShouldBeNavigatedToAccountDeletionPage()
                .clickOnContinueButton()
                .checkThatHomePageShouldBeLoaded()
                .checkThatUserShouldBeLoggedOut();
    }


    @AfterClass
    public void tearDown() {
        driver.get().quit();
    }
//
//    @DataProvider(name = "registerData")
//    public Object[][] getData(){
//        return new Object[][]{
//                {"test7457383783@gmail.com", "12345678"},
//        };
//    }
}

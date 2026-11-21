package driverfactory;

import assertions.hardassertions.HardAssertions;
import browseractions.BrowserActions;
import elementactions.ElementActions;
import listeners.webdriver.DriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static utilities.PropertiesManager.frameworkConfig;

public class Driver {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public Driver(String browserType) {
        if(browserType == null || browserType.trim().isEmpty()) {
            browserType = "chrome";
        }
        // driver.set(getDriverFactory(browserType).startDriver());
        WebDriver undecoratedDriver = getDriverFactory(browserType).startDriver();
        assert undecoratedDriver != null;
        driver.set(new EventFiringDecorator<>(WebDriver.class, new DriverListener(undecoratedDriver)).decorate(undecoratedDriver));
        System.out.println("Starting the execution via " + browserType + " driver");

        if (!frameworkConfig.getProperty("BaseUrl").isEmpty()) {
            driver.get().navigate().to(frameworkConfig.getProperty("BaseUrl"));
        }
    }

    public Driver() {
        String browserType = frameworkConfig.getProperty("BrowserType");
        WebDriver undecoratedDriver = getDriverFactory(browserType).startDriver();
        assert undecoratedDriver != null;
        driver.set(new EventFiringDecorator<>(WebDriver.class, new DriverListener(undecoratedDriver)).decorate(undecoratedDriver));
        System.out.println("Starting the execution via " + browserType + " driver");

        if (!frameworkConfig.getProperty("BaseUrl").isEmpty()) {
            driver.get().navigate().to(frameworkConfig.getProperty("BaseUrl"));
        }
    }


    private DriverAbstract getDriverFactory(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return new ChromeDriverFactory();
            case "firefox":
                return new FirefoxDriverFactory();
            case "edge":
                return new EdgeDriverFactory();
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void quit() {
        if (driver != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public ElementActions element() {
        return new ElementActions(driver.get());
    }

    public BrowserActions browser() {
        return new BrowserActions(driver.get());
    }

    public HardAssertions expectThat() {
        return new HardAssertions(driver.get());
    }
}

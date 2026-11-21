package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utilities.PropertiesManager;

import java.time.Duration;

import static utilities.PropertiesManager.frameworkConfig;

public class FirefoxDriverFactory extends DriverAbstract{

    @Override
    public WebDriver startDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--incognito");
        if(frameworkConfig.getProperty("headlessMode").equalsIgnoreCase("true")) {
            options.addArguments("--headless");
        }
        // options.addArguments("--start-maximized");
        options.addArguments("--enable-logging");
        options.addArguments("--window-size=" + frameworkConfig.getProperty("windowSize"));
        options.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(PropertiesManager.frameworkConfig.getProperty("pageLoadTimeout"))));
        options.setScriptTimeout(Duration.ofSeconds(Long.parseLong(PropertiesManager.frameworkConfig.getProperty("scriptTimeout"))));


        driver = new FirefoxDriver(options);
        return driver;
    }
}

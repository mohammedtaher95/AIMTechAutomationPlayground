package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utilities.PropertiesManager;

import java.time.Duration;

import static utilities.PropertiesManager.frameworkConfig;

public class EdgeDriverFactory extends DriverAbstract {

    @Override
    public WebDriver startDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--incognito");
        if(frameworkConfig.getProperty("headlessMode").equalsIgnoreCase("true")) {
            options.addArguments("--headless");
        }
        // options.addArguments("--start-maximized");
        options.addArguments("--enable-logging");
        options.addArguments("--window-size=" + frameworkConfig.getProperty("windowSize"));
        options.setPageLoadTimeout(Duration.ofSeconds(Long.parseLong(PropertiesManager.frameworkConfig.getProperty("pageLoadTimeout"))));
        options.setScriptTimeout(Duration.ofSeconds(Long.parseLong(PropertiesManager.frameworkConfig.getProperty("scriptTimeout"))));


        driver = new EdgeDriver(options);
        return driver;
    }
}

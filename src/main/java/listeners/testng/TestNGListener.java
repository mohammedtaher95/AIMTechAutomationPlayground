package listeners.testng;

import driverfactory.Driver;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.testng.*;
import org.testng.xml.XmlSuite;
import utilities.AllureReportHelper;
import utilities.ScreenshotManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static utilities.PropertiesManager.frameworkConfig;
import static utilities.PropertiesManager.initializeProperties;

public class TestNGListener implements IExecutionListener, ITestListener, IInvokedMethodListener, IAlterSuiteListener {

    @Override
    public void onExecutionStart() {
        System.out.println("**************** Welcome to Selenium Framework *****************");
        initializeProperties();
        if(frameworkConfig.getProperty("cleanAllureReportBeforeExecution").equalsIgnoreCase("true")) {
            AllureReportHelper.cleanAllureReport();
        }
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("********************* End of Execution *********************");
        if(frameworkConfig.getProperty("openReportAfterExecution").equalsIgnoreCase("true")) {
            AllureReportHelper.openAllureReport();
        }
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // This method is called before each test method invocation
        // method.getTestMethod().setRetryAnalyzerClass(RetryAnalyzer.class);
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("****************  Starting Test  *****************");
        result.getMethod().setRetryAnalyzerClass(RetryAnalyzer.class);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("****************  Success of Test  *****************");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");
        System.out.println("Taking screen shot.....");

        Driver driver = null;
        ThreadLocal<Driver> driverThreadLocal;
        Field[] fields = result.getInstance().getClass().getDeclaredFields();

        try {
            for (Field field : fields) {
                if (field.getType() == Driver.class) {
                    driver = (Driver) field.get(result.getInstance());
                }

                if (field.getType() == ThreadLocal.class) {
                    driverThreadLocal = (ThreadLocal<Driver>) field.get(result.getInstance());
                    driver = driverThreadLocal.get();
                }
            }
        } catch (IllegalAccessException exception) {
            System.out.println("Unable to get field, Field Should be public");
        }

        ScreenshotManager.captureScreenshot(driver.getDriver(), result.getName());
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + result.getName() + ".jpg";
        AllureReportHelper.attachScreenshot(screenshotPath, result.getName());
    }

    @Override
    public void alter(List<XmlSuite> suites) {
        // This method is called before the test suite is executed
        suites.set(0, TestNGHelper.suiteGenerator(suites.get(0)));
    }
}

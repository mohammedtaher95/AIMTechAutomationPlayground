package listeners.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static utilities.PropertiesManager.frameworkConfig;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = frameworkConfig.getProperty("maxRetryAttempts") != null ?
            Integer.parseInt(frameworkConfig.getProperty("maxRetryAttempts")) : 0; // Set the maximum number of retries

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test " + result.getName() + " with status " + (result.getStatus()) + " for the " + retryCount + " time(s).");
            return true; // Retry the test
        }
        return false; // Do not retry the test
    }
}

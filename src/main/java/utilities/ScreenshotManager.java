package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ScreenshotManager {

    static String screenshotDirectoryPath = "screenshots/";
    public static final File screenshotDirectory = new File(screenshotDirectoryPath);

    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        if (!screenshotDirectory.exists()) {
            boolean created = screenshotDirectory.mkdirs();
            if (created) {
                System.out.println("Screenshot directory created at: " + screenshotDirectory.getAbsolutePath());
            } else {
                System.out.println("Failed to create screenshot directory at: " + screenshotDirectory.getAbsolutePath());
            }
        }

        Path destinationPath = Paths.get(screenshotDirectoryPath,screenshotName + ".jpg");
        byte[] byteArray = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        try {
            Files.write(destinationPath, byteArray, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Unable to save screenshot");
        }
    }
}

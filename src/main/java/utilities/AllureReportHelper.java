package utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureReportHelper {

    public static void cleanAllureReport() {
        try {
            FileUtils.deleteDirectory(new File("target/allure-results"));
            System.out.println("Allure Report cleaned successfully");
        } catch (IOException e) {
            System.out.println("Allure Report Already Cleaned");
        }
    }

    public static void openAllureReport() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "allure serve target/allure-results");
            processBuilder.start();
            System.out.println("Allure Report opened successfully");
        } catch (IOException e) {
            System.out.println("Unable to open Allure Report");
        }
    }

    public static void attachScreenshot(String screenshotPath, String screenshotName) {
        try {
            Allure.attachment(screenshotName, new FileInputStream(screenshotPath));
            System.out.println("Screenshot attached to Allure Report successfully");
        } catch (IOException e) {
            System.out.println("Unable to attach screenshot to Allure Report");
        }
    }
}

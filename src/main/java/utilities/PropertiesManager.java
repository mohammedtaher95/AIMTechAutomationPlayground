package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    public static Properties frameworkConfig;

    public static void initializeProperties() {

        FileInputStream propertiesFile = null;
        try {
            propertiesFile = new FileInputStream("src/main/resources/configurations.properties");

            frameworkConfig = new Properties();
            frameworkConfig.load(propertiesFile);
        } catch (FileNotFoundException e) {
            System.out.println("Configurations File is not found " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to load the configurations file " + e.getMessage());
        }
    }
}

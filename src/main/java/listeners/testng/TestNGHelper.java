package listeners.testng;

import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import static utilities.PropertiesManager.frameworkConfig;

public class TestNGHelper {

    static XmlTest test = null;

    public static XmlSuite suiteGenerator(XmlSuite suite) {

        test = suite.getTests().get(0);
        suite.setName("AIMTech Framework Test Suite");
//        suite.setParallel(XmlSuite.ParallelMode.CLASSES);
//        suite.setThreadCount(2);
//
        if (frameworkConfig.getProperty("parallelExecution").equalsIgnoreCase("Normal")) {
            initializeNormalExecution(suite);
        }

//        if (frameworkConfig.getProperty("parallelExecution").equalsIgnoreCase("Parallel")) {
//            suite.setParallel(XmlSuite.ParallelMode.CLASSES);
//            suite.setThreadCount(2);
//        }

        else  {
            initializeCrossBrowserExecution(suite);
        }

        return suite;
    }

    private static void initializeNormalExecution(XmlSuite suite) {

        test.setName("Normal Execution");
        test.addParameter("browserName", frameworkConfig.getProperty("BrowserType"));
        test.setThreadCount(1);
        test.setParallel(XmlSuite.ParallelMode.NONE);
        test.setXmlClasses(suite.getTests().get(0).getXmlClasses());
    }

    private static void initializeCrossBrowserExecution(XmlSuite suite) {

        if(frameworkConfig.getProperty("parallelExecution").equalsIgnoreCase("Parallel")) {
            suite.setParallel(XmlSuite.ParallelMode.TESTS);
        }

        if (frameworkConfig.getProperty("parallelExecution").equalsIgnoreCase("Sequential")) {
            suite.setParallel(XmlSuite.ParallelMode.NONE);
        }

        suite.setThreadCount(2);
        XmlTest chromeTest = test;
        chromeTest.setName("Chrome Test");
        chromeTest.addParameter("browserName", "chrome");
        chromeTest.setThreadCount(1);
        chromeTest.setParallel(XmlSuite.ParallelMode.NONE);
        chromeTest.setXmlClasses(test.getXmlClasses());

        XmlTest firefoxTest = new XmlTest(suite);
        firefoxTest.setName("Firefox Test");
        firefoxTest.setThreadCount(1);
        firefoxTest.setParallel(XmlSuite.ParallelMode.NONE);
        firefoxTest.addParameter("browserName", "firefox");
        firefoxTest.setXmlClasses(test.getXmlClasses());
    }
}

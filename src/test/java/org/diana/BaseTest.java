package org.diana;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    //CONSTANTS
    public static final String BASE_TEST_DIR = "src//test//resources//";
    public static final String SCREENSHOTS_DIR = BASE_TEST_DIR + "screenshots//";
    public static final String DOWNLOADS_DIR = BASE_TEST_DIR + "downloads//";
    public static final String UPLOADS_DIR = BASE_TEST_DIR + "upload//";
    public static final String REPORTS_DIR = BASE_TEST_DIR + "reports//";

    protected WebDriver driver;
    protected Logger log;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setUpTestSuite() throws IOException {
        cleanDirectory(REPORTS_DIR);
        cleanDirectory(SCREENSHOTS_DIR);
    }

    //Това казва на TestNG, че методът ще приема външен параметър с име browser
    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    //@Optional("chrome") → ако не е подаден параметър, се използва "chrome" по подразбиране
    public void setUp(@Optional("chrome") String browser, Method method) {
        String tafName = "TAF";
        log = LogManager.getLogger(tafName);
        log.info(" --- TEST CASE NAME: " + formatMethodName(method.getName()) + " --- ");
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String formatMethodName(String methodName) {
        return methodName
                .replaceAll("([a-z])([A-Z])", "$1 $2") // camelCase → с интервал
                .replaceAll("([A-Z])([A-Z][a-z])", "$1 $2") // за случаи с поредни главни букви
                .trim();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) throws IOException {
        takeScreenshotForFailedTests(testResult);

        log.info("Quitting driver");
        driver.quit();
    }

    @AfterSuite
    public void cleanFiles() throws IOException {
        cleanDirectory(DOWNLOADS_DIR);
    }

    private void takeScreenshotForFailedTests(ITestResult testResult) throws IOException {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            //cast our driver to interface called TakesScreenshot
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            //the method getScreenshotAs takes a perimeter of interface called OutputType - we need FILE
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();

            FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
        }
    }

    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);

        //If directory is not shown after git clone of the repo this code will build the path
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
            System.out.println("Created folder with path: "+ directoryPath);
        };

        System.out.println("Deleting not needed files from folder with path: "+ directoryPath);
        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All file are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }
    }
    }







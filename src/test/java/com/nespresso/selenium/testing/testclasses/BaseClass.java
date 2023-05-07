package com.nespresso.selenium.testing.testclasses;

import com.nespresso.selenium.testing.enums.Browser;
import com.nespresso.selenium.testing.utilities.PropertyConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseClass {

    PropertyConfiguration propertyConfig =  new PropertyConfiguration();
    public final String baseUrl = propertyConfig.getBaserUrl();
    public final String username = propertyConfig.getUsername();
    public final String password = propertyConfig.getPassword();
    public static WebDriver driver;

    public static Logger logger;

    private static final String CHROME_DRIVER_PATH = System.getProperty("user.dir").concat("/drivers/linux/chromedriver");

    @BeforeClass
    @Parameters(value = "browser")
    public void setup(String browser) {

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeOptions options = getChromeOptions();
        options.setBinary(CHROME_DRIVER_PATH);
        options.addArguments("--headless");

        // WebDriverManager.chromedriver().setup();
        if (browser.equals(Browser.CHROME.getLabel())) {
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.getLabel())) {
            driver = new FirefoxDriver();
        } else if (browser.equals(Browser.SAFARI.getLabel())) {
            driver = new SafariDriver();
        } else {
            driver = new ChromeDriver(options);
        }
        DesiredCapabilities capabilities = getDesiredCapabilities(options);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver.manage().window().maximize();
        logger = Logger.getLogger("Nespresso");
        PropertyConfigurator.configure(System.getProperty("user.dir").concat("/log4j.properties"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void captureScreenshot(WebDriver driver, String testName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destionationFile = new File(System.getProperty("user.dir").concat("/screenshots/").concat(testName).concat(".png"));
        FileUtils.copyFile(sourceFile, destionationFile);
        System.out.println("***** Screenshot taken *****");
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.setBinary(CHROME_DRIVER_PATH);
        options.addArguments("--headless");
        options.addArguments("--incognito");
        options.addArguments("disable-gpu");
        options.addArguments("start-maximized");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        return options;
    }

    public DesiredCapabilities getDesiredCapabilities(ChromeOptions options) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("UNHANDLED_PROMPT_BEHAVIOUR" ,true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, true);
        capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.IGNORE);
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
        capabilities.setCapability("nativeEvents", false);
        capabilities.setCapability("requireWindowFocus", false);
        capabilities.setCapability("javascriptEnabled", true);
        capabilities.setCapability("ignoreProtectedModeSettings", true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }

    public void waitForNSeconds(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

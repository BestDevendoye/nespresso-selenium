package com.nespresso.selenium.testing.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.s"));
        String repertoireName = "nespresso-test-report-".concat(timeStamp).concat(".html");
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir").concat("/test-output/").concat(repertoireName));
        htmlReporter.loadXMLConfig(System.getProperty("user.dir").concat("/extent-config.xml"));

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host name", "localhost");
        extentReports.setSystemInfo("Environnement", "QA");
        extentReports.setSystemInfo("User", "DIA MAMADOU");

        htmlReporter.config().setDocumentTitle("Nespresso Testing Projects");
        htmlReporter.config().setReportName("Nespresso Functional Test Reporting");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        extentTest = extentReports.createTest(testResult.getName());
        extentTest.log(Status.PASS, MarkupHelper.createLabel(testResult.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        extentTest = extentReports.createTest(testResult.getName());
        extentTest.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName(), ExtentColor.RED));
        String screenshotPath = System.getProperty("user.dir").concat("/screenshots/").concat(testResult.getName()).concat(".png");
        File screenshotsFile = new File(screenshotPath);

        if (screenshotsFile.exists()) {
            try {
                extentTest.fail("Screenshot is bellow: " + extentTest.addScreenCaptureFromPath(screenshotPath));
            } catch (IOException exception) {
                System.out.println("Error: An error occurred while generating the screenshot files".concat(exception.getMessage()));
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        extentTest = extentReports.createTest(testResult.getName());
        extentTest.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName(), ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extentReports.flush();
    }
}

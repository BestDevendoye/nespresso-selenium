package com.nespresso.selenium.testing.testclasses;

import com.nespresso.selenium.testing.pageobjects.HomePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class HomePageTest extends BaseClass {

    @Test
    public void bannerButtonAcceptTest() throws IOException {
        driver.get("https://www.nespresso.com/pro/fr/fr/");
        logger.info("Base url "+ baseUrl + " opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        HomePage homePage = new HomePage(driver);
        waitForNSeconds(1);

        homePage.clickAcceptButton();
        waitForNSeconds(1);

        //homePage.clickNespressoProfessionnelLink();
        //waitForNSeconds(1);

        homePage.clickFirstAcheterButtonTexts();
        waitForNSeconds(1);

        boolean isBannerDisplayed = driver.findElements(By.id("_evidon-banner-content")).size() < 1;
        captureScreenshot(driver, "Home Page - Banner button accept");
        assertTrue(isBannerDisplayed);
    }
}

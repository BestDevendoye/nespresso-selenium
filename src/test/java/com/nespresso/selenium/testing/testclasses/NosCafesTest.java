package com.nespresso.selenium.testing.testclasses;

import com.nespresso.selenium.testing.pageobjects.HomePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class NosCafesTest extends BaseClass {

    @Test
    public void validationPanierTest() throws IOException {
        driver.get(baseUrl);
        logger.info("Base url "+ baseUrl + " opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        HomePage homePage = new HomePage(driver);
        homePage.clickAcceptButton();
        waitForNSeconds(1);

        //homePage.clickNespressoProfessionnelLink();
        waitForNSeconds(1);

        homePage.clickFirstAcheterButtonTexts();
        waitForNSeconds(1);

        boolean isBannerDisplayed = driver.findElements(By.id("_evidon-banner-content")).size() < 1;
        captureScreenshot(driver, "Home Page - Banner button accept");
        assertTrue(isBannerDisplayed);

        driver.get("https://www.nespresso.com/pro/fr/fr/order/capsules/pro");
        waitForNSeconds(1);

        driver.get("https://www.nespresso.com/pro/fr/fr/order/capsules/pro/ristretto-intenso-boite-capsule-cafe");
        waitForNSeconds(1);

        captureScreenshot(driver, "validation Panier Test");
        driver.findElement(By.id("ta-product-details__add-to-bag-button")).click();
        waitForNSeconds(1);

        driver.findElements(By.xpath("//*[text()='AJOUTER AU PANIER']")).get(0).click();
        waitForNSeconds(1);

        driver.findElement(By.id("ta-quantity-selector__custom-field")).sendKeys("50");
        waitForNSeconds(1);

        driver.findElement(By.id("ta-quantity-selector__custom-ok")).click();
        waitForNSeconds(1);

        driver.findElement(By.xpath("//*[text()='VOTRE PANIER']")).click();
        waitForNSeconds(1);

        driver.findElement(By.xpath("//*[text()='Valider votre panier']")).click();
        waitForNSeconds(1);
    }
}

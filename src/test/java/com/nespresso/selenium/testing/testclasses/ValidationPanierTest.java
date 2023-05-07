package com.nespresso.selenium.testing.testclasses;

import com.nespresso.selenium.testing.pageobjects.HomePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.*;

public class ValidationPanierTest extends BaseClass {

    @Test
    public void validationPanierTest() throws IOException {
        driver.get("https://www.nespresso.com/pro/fr/fr/order/capsules/pro");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        HomePage homePage = new HomePage(driver);
        waitForNSeconds(1);

        homePage.clickAcceptButton();
        waitForNSeconds(1);

        homePage.addToBagButton();
        waitForNSeconds(1);

        homePage.clickFirstAcheterButtonTexts();
        waitForNSeconds(1);

        boolean isBannerDisplayed = driver.findElements(By.className("QuantitySelector__container")).size() > 0;
        captureScreenshot(driver, "Home Page - Banner button accept");
        assertTrue(isBannerDisplayed);

        homePage.clickAddToBagQuantity();
        waitForNSeconds(1);
        boolean addToBagQuantityIsPresent = driver.findElements(By.className("MiniBasketButton__quantity")).size() > 0;
        //System.out.println("***** Size Validation *****"+ driver.findElement(By.className("heading")).getSize());
        assertFalse(addToBagQuantityIsPresent);

        driver.get("https://www.nespresso.com/pro/fr/fr/order/machines/pro");
        waitForNSeconds(4);
        driver.get("https://www.nespresso.com/pro/fr/fr/order/machines/pro/machine-cafe-professionnelle-pack-zenius");
        waitForNSeconds(1);

        captureScreenshot(driver, "Validation Panier Test");
        //driver.findElement(By.id("ta-product-details__add-to-bag-button")).click();
      //  waitForNSeconds(1);

        assertTrue(driver.findElement(By.xpath("//*[text()='Pack Zenius']")).isDisplayed());
    }
}

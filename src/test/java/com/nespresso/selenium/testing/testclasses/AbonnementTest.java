package com.nespresso.selenium.testing.testclasses;


import com.nespresso.selenium.testing.pageobjects.AbonnementPage;
import com.nespresso.selenium.testing.pageobjects.HomePage;
import com.nespresso.selenium.testing.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class AbonnementTest extends BaseClass {

    @Test
    public void AbonnementOnlineTest() throws InterruptedException {
        driver.get("https://www.nespresso.com/pro/fr/fr/abonnement-professionnel");
        logger.info("Base url "+ baseUrl + " opened");
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        waitForNSeconds(1);

        homePage.clickAcceptButton();
        waitForNSeconds(5);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        AbonnementPage abonnementPage = new AbonnementPage(driver);
        boolean isBannerDisplayed = driver.findElements(By.className("g_h3")).size() > 0;
        //captureScreenshot(driver, "Abonnement Page - Machine is present");
        assertTrue(isBannerDisplayed);
        WebElement elementAddMachineZenius = driver.findElements(By.className("g_btnBuy")).get(1);
        Thread.sleep(4000);
        elementAddMachineZenius.click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextStepButton =  driver.findElement(By.cssSelector(".proceed .g_btnBuy")) ;
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(nextStepButton));
        nextStepButton.click();

        //add capsules
        WebElement elementAddCaps = driver.findElements(By.className("g_btnBuy")).get(4);
        elementAddCaps.click();
        waitForNSeconds(10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[starts-with(@placeholder,'Indiquer')]")));
        WebElement add300Caps = driver.findElements(By.className("quantity")).get(6);
        add300Caps.click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(nextStepButton));
        nextStepButton.click();

        //add goblet
        WebElement addGoblet = driver.findElements(By.className("g_btnBuy")).get(3);
        wait.until(ExpectedConditions.elementToBeClickable(addGoblet));
        addGoblet.click();
        WebElement add3Gobelet = driver.findElements(By.className("quantity")).get(6);
        add3Gobelet.click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(nextStepButton));
        nextStepButton.click();

        //checkout
        WebElement checkout = driver.findElement(By.cssSelector(".summary .g_wysiwyg "));
        wait.until(ExpectedConditions.textToBePresentInElement(checkout, "Votre récapitulatif"));
        WebElement packSav = driver.findElements(By.cssSelector(".label .g_wysiwyg")).get(1);
        assertEquals(packSav.getText().toLowerCase(), "pack sav inclus (1)");
        WebElement remise = driver.findElements(By.cssSelector(".body .row .g_wysiwyg ")).get(2);
        assertEquals(remise.getText().toLowerCase(), "remise");
        Thread.sleep(5000);

        WebElement paymentButton = driver.findElement(By.cssSelector(".actions .checkout"));
        wait.until(ExpectedConditions.elementToBeClickable(paymentButton));
        paymentButton.click();
        wait.until(ExpectedConditions.urlContains("https://www.nespresso.com/pro/fr/fr/secure/login?destination-redirect=/pro/fr/fr/subscription-online/checkout"));
        LoginPage loginPage = new LoginPage(driver);
        waitForNSeconds(1);

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickSubmit();
        Thread.sleep(6000);
        waitForNSeconds(5);
        WebElement detailsCommande = driver.findElement(By.cssSelector(".SubscriptionOnlineSummary .SubscriptionOnlineSummary__title"));
        assertEquals(detailsCommande.getText(), "DÉTAILS DE LA COMMANDE");
        Thread.sleep(10000);
       // WebElement classSlider = driver.findElement(By.xpath("//*[text()='Capsules requises ']"));
       // wait.until(ExpectedConditions.textToBePresentInElement(classSlider, "Capsules requises"));

        /*WebElement elementAddCaps = driver.findElements(By.className("g_btnBuy")).get(4);
        elementAddCaps.click();
        waitForNSeconds(10);
       // WebElement isCardCapsDisplayed = driver.findElement(By.className("popover")) ;
        //assertTrue(isCardCapsDisplayed.isDisplayed());

        WebElement add300Caps = driver.findElements(By.className("quantity")).get(6);
        add300Caps.click();
        wait.until(ExpectedConditions.elementToBeClickable(elementNextStep));

        //elementAddCaps.click();
        WebElement addGoblet = driver.findElements(By.className("g_h3")).get(3);
        wait.until(ExpectedConditions.elementToBeClickable(addGoblet));
        addGoblet.click();
        wait.until(ExpectedConditions.elementToBeClickable(elementNextStep));

        WebElement gotToPayment = driver.findElements(By.className("g_btnBuy")).get(0);
        waitForNSeconds(10);
        wait.until(ExpectedConditions.elementToBeClickable(gotToPayment));


        WebElement checkout = driver.findElement(By.className("checkout"));
        wait.until(ExpectedConditions.elementToBeClickable(checkout));
        checkout.click();*/
        // homePage.addToBagButton();
        //waitForNSeconds(1);

       /* homePage.clickFirstAcheterButtonTexts();
        waitForNSeconds(1);

        boolean isBannerDisplayed = driver.findElements(By.id("_evidon-banner-content")).size() < 1;
        captureScreenshot(driver, "Home Page - Banner button accept");
        assertTrue(isBannerDisplayed);

        driver.get("https://www.nespresso.com/pro/fr/fr/abonnement-professionnel");
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
        waitForNSeconds(1);*/
    }
}

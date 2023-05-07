package com.nespresso.selenium.testing.testclasses;

import com.nespresso.selenium.testing.pageobjects.HomePage;
import com.nespresso.selenium.testing.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest extends BaseClass {

    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://www.nespresso.com/pro/fr/fr/secure/login");
        driver.manage().window().maximize();
        logger.info("url https://www.nespresso.com/pro/fr/fr/secure/login opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        HomePage homePage = new HomePage(driver);
        waitForNSeconds(1);

        homePage.clickAcceptButton();
        waitForNSeconds(1);

        LoginPage loginPage = new LoginPage(driver);
        waitForNSeconds(1);

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickSubmit();
        Thread.sleep(1000);
        waitForNSeconds(5);
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("https://www.nespresso.com/pro/fr/fr/"));
        By elem_dynamic = By.className("VisuallyHidden");
        String elementText = driver.findElement(elem_dynamic).getText();
        wait.until(ExpectedConditions.textToBePresentInElement( driver.findElement(elem_dynamic) , elementText));
        waitForNSeconds(10);
        System.out.println("***** Size *****"+ driver.findElement(By.className("heading")).getSize());

    }
}

package com.nespresso.selenium.testing.testclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SendEmailTest extends BaseClass{

    @Test
    public void SendRapportOnEmailTest() throws InterruptedException {
        logger.info("Base url "+ baseUrl + " opened");
        driver.manage().window().maximize();
        String url = "https://accounts.google.com/v3/signin/identifier?dsh=S687905510%3A1674741656000146&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHdGBMv6_8Ve7yHoTXzgYxWkH9dHwDMK-J0FI6U1YVYEV5zsKfWvrMSz-lRwcVI4PeAVOSzQ";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email_phone.sendKeys("jaatafa@gmail.com");
        driver.findElement(By.id("identifierNext")).click();
        Thread.sleep(3000);
        WebElement password = driver.findElement(By.xpath("//input[@name='Passwd']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(password));
        System.out.println("\ntest  password");
        password.sendKeys("jaatafa");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        password.sendKeys(Keys.ENTER);
//    driver.findElement(By.className("ZFr60d")).click();
        driver.findElement(By.className("WaidBe")).click();
        System.out.println("Mail page opened");
        Thread.sleep(3000);
        driver.findElement(By.className("z0")).click();
        driver.findElement(By.className("vO")).sendKeys("****************.com");
        driver.findElement(By.className("aoT")).sendKeys("Test email from selenium");
        driver.findElement(By.className("Am")).sendKeys("Hi");
        driver.findElement(By.className("aoO")).click();
    }
}

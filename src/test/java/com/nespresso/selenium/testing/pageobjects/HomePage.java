package com.nespresso.selenium.testing.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "_evidon-banner-acceptbutton")
    @CacheLookup
    WebElement bannerAcceptButton;

    @FindBy(className = "AddToBagButton")
    @CacheLookup
    WebElement addToBagElement;

    @FindBy(id = "ta-quantity-selector__predefined-7")
    @CacheLookup
    WebElement quantityAddToBag;

    @FindBy(className = "CheckoutActionButton")
    @CacheLookup
    List<WebElement> acheterButtonTexts;

    @FindBy(id = "ta-mini-basket__open")
    @CacheLookup
    WebElement miniBasketOpen;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptButton() {
        this.bannerAcceptButton.click();
    }

    public void addToBagButton() {
        this.addToBagElement.click();
    }

    public void clickFirstAcheterButtonTexts() {
        System.out.println("#######   " + acheterButtonTexts.size());
    }

    public void clickAddToBagQuantity(){
        this.quantityAddToBag.click();
    }

    public void openMiniBasket() {
        this.miniBasketOpen.click();
    }
}

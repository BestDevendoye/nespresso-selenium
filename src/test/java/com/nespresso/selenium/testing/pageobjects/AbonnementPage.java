package com.nespresso.selenium.testing.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class AbonnementPage {
    WebDriver driver;

    @FindBy(className = "g_h3")
    @CacheLookup
    WebElement addMachineButton;

    @FindBy(className = "card")
    @CacheLookup
    WebElement cardMachine;


    public AbonnementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddMachineButton() {
        this.addMachineButton.click();
    }

}

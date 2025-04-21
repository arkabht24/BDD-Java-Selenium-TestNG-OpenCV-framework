package com.orangehrm.utils;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class Operation {
    WebDriver driver = null;
    public Operation(WebDriver driver){
        this.driver = driver;
    }
    public void click(WebElement element){
        if(element.isDisplayed()){
            scrollToCenter(element);
            try{
                element.click();
            }
            catch (Exception e){
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click().build().perform();
            }
        }
    }

    public void scrollToCenter(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})",element);
    }

    public void takeScreenshot(String logMessage){

    }
}

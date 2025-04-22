package com.orangehrm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.orangehrm.listeners.TestNGParameterStore;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.orangehrm.listeners.WebDriverListener.dirForScreenshotsCapture;

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
        String dir = dirForScreenshotsCapture.get();
        System.out.println("Dir path ---> "+dir);
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(dir+logMessage+".png");
            Files.copy(screenshot.toPath(), destinationFile.toPath());

            System.out.println("Screenshot saved as "+logMessage+".png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

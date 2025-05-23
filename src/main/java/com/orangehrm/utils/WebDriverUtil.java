package com.orangehrm.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtil {
    public static WebDriver getDriver(String browser) {
        WebDriver driver = null;

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/resources/Drivers/chromedriver");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Unsupported browser: " + browser);
                break;
        }

        return driver;
    }
}

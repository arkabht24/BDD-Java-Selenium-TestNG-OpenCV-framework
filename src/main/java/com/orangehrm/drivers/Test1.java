package com.orangehrm.drivers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        // Set the path to chromedriver inside the "drivers" folder in your project
        System.setProperty("webdriver.chrome.driver", "src/resources/Drivers/chromedriver");

        // Create an instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to a website
        driver.get("https://www.google.com");

        // Perform actions (like searching or clicking)
        Thread.sleep(5000);
        // Close the browser
        driver.quit();
    }
}


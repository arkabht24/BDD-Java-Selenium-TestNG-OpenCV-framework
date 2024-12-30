package com.orangehrm.listeners;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.orangehrm.utils.WebDriverUtil;

public class WebDriverListener implements ITestListener {

    private WebDriver driver;
    private String browser;

    @Override
    public void onStart(ITestContext context) {
        browser = context.getCurrentXmlTest().getParameter("browser");
    }

    @Override
    public void onFinish(ITestContext context) {
        tearDown();
    }

    @Override
    public void onTestStart(ITestResult result) {
        driver = WebDriverUtil.getDriver(browser);
        System.out.println("Browser initialized: " + browser);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        tearDown();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
        tearDown();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
        tearDown();
    }

    public void tearDown(){
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed.");
            }
        }
        catch (Exception e){
            System.out.println("Browser closed already.");
        }
    }
}

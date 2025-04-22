package com.orangehrm.listeners;


import com.orangehrm.utils.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.orangehrm.utils.WebDriverUtil;
import java.util.ArrayList;

import java.util.List;

public class WebDriverListener implements ITestListener {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ThreadLocal<String> testName = new ThreadLocal<>();
    public static ThreadLocal <String> dirForScreenshotsCapture = new ThreadLocal<>();
    public static ThreadLocal <List<String>> listOfImagesNames = ThreadLocal.withInitial(ArrayList::new);

    private String browser;

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    @Override
    public void onStart(ITestContext context) {
        //browser = context.getCurrentXmlTest().getParameter("browser");
    }

    @Override
    public void onFinish(ITestContext context) {
        tearDown();
    }

    @Override
    public void onTestStart(ITestResult result) {
        result.getTestContext().getCurrentXmlTest().getAllParameters().forEach(TestNGParameterStore::setParameter);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getName());
    }

    public void tearDown(){
        try {
            WebDriver webDriver = getDriver();
            if (webDriver != null) {
                webDriver.quit();
                System.out.println("Browser closed.");
            }
        } catch (Exception e) {
            System.out.println("Browser already closed or error occurred: " + e.getMessage());
        } finally {
            driver.remove();
            testName.remove();
        }
    }
}

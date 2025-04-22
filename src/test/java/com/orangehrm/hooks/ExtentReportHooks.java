package com.orangehrm.hooks;

import com.aventstack.extentreports.Status;
import com.orangehrm.listeners.TestNGParameterStore;
import com.orangehrm.utils.FileUtils;
import com.orangehrm.utils.WebDriverUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static com.orangehrm.listeners.WebDriverListener.*;


public class ExtentReportHooks {


    @Before
    public void beforeScenario(Scenario scenario) {

        ExtentReportManager.setTest(
                ExtentReportManager.getReporter().createTest(scenario.getName())
        );
        ExtentReportManager.getTest().log(Status.INFO, "Starting Scenario: " + scenario.getName());
        String browser = TestNGParameterStore.getParameter("browser");
        setDriver(WebDriverUtil.getDriver(browser));
        System.out.println("Browser initialized: " + browser);
        testName.set(scenario.getName());
        if(TestNGParameterStore.getParameter("screenshot-comparison").equalsIgnoreCase("false")){
            new FileUtils().deleteDirectory("resources/baselineSS/"+testName.get());
            new FileUtils().createDirectoryIfNotExists("resources/baselineSS/"+testName.get());
            dirForScreenshotsCapture.set("resources/baselineSS/"+testName.get()+"/");
        }
        else {
            new FileUtils().deleteDirectory("resources/temporarySS/"+testName.get());
            new FileUtils().createDirectoryIfNotExists("resources/temporarySS/"+testName.get());
            dirForScreenshotsCapture.set("resources/temporarySS/"+testName.get()+"/");
        }

    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportManager.getTest().fail("Scenario Failed: " + scenario.getName());
        } else {
            ExtentReportManager.getTest().pass("Scenario Passed: " + scenario.getName());
        }
        // Flush the report to save changes
        ExtentReportManager.getReporter().flush();
    }
}

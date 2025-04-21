package com.orangehrm.hooks;

import com.aventstack.extentreports.Status;
import com.orangehrm.listeners.TestNGParameterStore;
import com.orangehrm.utils.FileUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static com.orangehrm.listeners.WebDriverListener.testName;


public class ExtentReportHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        // Start a new test for each scenario
        ExtentReportManager.setTest(
                ExtentReportManager.getReporter().createTest(scenario.getName())
        );
        ExtentReportManager.getTest().log(Status.INFO, "Starting Scenario: " + scenario.getName());
        testName.set(scenario.getName());
        if(TestNGParameterStore.getParameter("screenshot-comparison").equalsIgnoreCase("false")){
            new FileUtils().deleteDirectory("resources/baselineSS/"+testName.get());
            new FileUtils().createDirectoryIfNotExists("resources/baselineSS/"+testName.get());
        }
        else {
            new FileUtils().deleteDirectory("resources/temporarySS/"+testName.get());
            new FileUtils().createDirectoryIfNotExists("resources/temporarySS"+testName.get());
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

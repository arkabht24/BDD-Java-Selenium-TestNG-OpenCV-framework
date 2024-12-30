package com.orangehrm.hooks;

import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ExtentReportHooks {
    @Before
    public void beforeScenario(Scenario scenario) {
        // Start a new test for each scenario
        ExtentReportManager.setTest(
                ExtentReportManager.getReporter().createTest(scenario.getName())
        );
        ExtentReportManager.getTest().log(Status.INFO, "Starting Scenario: " + scenario.getName());
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

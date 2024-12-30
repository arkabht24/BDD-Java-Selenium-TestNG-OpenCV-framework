package com.orangehrm.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@CucumberOptions(
        monochrome = true,
        features = {"src/test/resources/features/test-one-try.feature"},
        glue = {"com.orangehrm.hooks","com.orangehrm.stepdefinations"},
        plugin = {"pretty"},
        tags = "@PrintScenario2 or @PrintScenario"
)
public class RunnerTestFeatureOne extends AbstractTestNGCucumberTests {

   /* @Parameters("cucumberTags")
    @Test  // Accept tags parameter from TestNG XML
    public void runCucumberTests(String tags) {
        System.setProperty("cucumber.options", "--tags " + tags);  // Set the tag dynamically
    }*/
}

package com.orangehrm.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        monochrome = true,
        features = {"src/test/resources/features"},
        glue = {"com.orangehrm.hooks","com.orangehrm.stepdefinations"},
        plugin = {"pretty"},
        tags = "@scenario1"
)
public class OrangeHRMScenario1 extends AbstractTestNGCucumberTests {

}

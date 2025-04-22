package com.orangehrm.hooks;

import com.aventstack.extentreports.Status;
import com.orangehrm.image_handling.PythonRunner;
import com.orangehrm.listeners.TestNGParameterStore;
import com.orangehrm.utils.ExtentScreenshotHelper;
import com.orangehrm.utils.FileUtils;
import com.orangehrm.utils.WebDriverUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

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

            new FileUtils().deleteDirectory("resources/comparedSS/"+testName.get());
            new FileUtils().createDirectoryIfNotExists("resources/comparedSS/"+testName.get());
            comparedScreenshotDir.set("resources/comparedSS/"+testName.get()+"/");
        }

    }

    @After
    public void afterScenario(Scenario scenario) {
        for(String imageName : listOfImagesNames.get()){
            new PythonRunner().runPythonImageComparison("src/main/python/image_compare.py",
                    imageName);
        }
        try {
            new ExtentScreenshotHelper().addThreeScreenshotsRow(ExtentReportManager.getTest(),listOfImagesNames.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (scenario.isFailed()) {
            ExtentReportManager.getTest().fail("Scenario Failed: " + scenario.getName());
        } else {
            ExtentReportManager.getTest().pass("Scenario Passed: " + scenario.getName());
        }
        ExtentReportManager.getReporter().flush();
    }
}

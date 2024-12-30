package com.orangehrm.stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


public class PrintOperationSteps {

    private String message;

    @Given("I have a simple print operation")
    public void i_have_a_simple_print_operation() {
        // Setup step
        message = "Hello, Cucumber!";
    }

    @When("I execute the print operation")
    public void i_execute_the_print_operation() {
        // Action step - simply print the message to the console
        System.out.println(message);
    }

    @Then("I should see {string} printed on the console")
    public void i_should_see_printed_on_the_console(String expectedMessage) {
        // Verify the output
        assertTrue("Message not printed correctly", message.equals(expectedMessage));
    }
}

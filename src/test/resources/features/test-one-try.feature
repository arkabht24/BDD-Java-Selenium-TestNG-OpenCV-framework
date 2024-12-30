Feature: Print operation test

  @PrintScenario
  Scenario: Print a simple message to verify setup
    Given I have a simple print operation
    When I execute the print operation
    Then I should see "Hello, Cucumber!" printed on the console


  @PrintScenario2
  Scenario: Print a simple message to verify setup 1
    Given I have a simple print operation
    When I execute the print operation
    Then I should see "Hello, Cucumber!" printed on the console

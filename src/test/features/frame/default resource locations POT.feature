@smoke @pot
Feature: Default Resource Locations - Proof of Tests
  As a test engineer,
  So that I have confidence in the specification of default resource location,
  I want to be able to see failures reported when the inherent expectations are not met

  Note that this is not really an exhaustive test, you may want to make your own, but it is OK for a smoke as it will fall over
  if the defaults have not been correctly defined.

  Scenario: Demonstrate that the :exact title of a page: test detects an error
    When the browser is showing the page ""
    Then the page title is "Not Going To Be This Title At All"
    And I write to the report "Expect this scenario to fail"


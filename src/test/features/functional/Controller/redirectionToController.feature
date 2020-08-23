@controller
Feature: Redirection To Controller
  As a developer
  So that I can ensure that all page requests are correctly handled
  I want all requests for any page, or chapter to be handled by the controller
  This comes about from the design-decision to use this pattern
  If processing of the PHP fails at any point, then subsequent sections of the page are likely to be missing, so this
  gives confidence that no catastrophic failure has occurred

  Scenario Outline: Visit known pages
    Given I am on the "<pagePath>" page
    Then The page title is "<ExpectedTitle>"
    And I see the banner section
    And I see the body section
    And I see the footer section
    Examples:
      | pagePath | ExpectedTitle  | comment                          |
      |          | Rose Goldthorp | home page                        |
      | anywhere | Rose Goldthorp | will fail until this is coded-up |
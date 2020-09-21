@standards
Feature: Standard Compliance
  Pages should all comply with relevant standards
  Some of these give false negatives, or the 'transgressions' could be acceptable.

  Scenario: CSS Compliance
    Given the w3C CSS tester reviews the file "http://rosegoldthorp.com/styles.css"
    Then the w3c CSS tester reports compliance

  Scenario Outline: HTML Syntax Check
    Given the w3C HTML tester reviews the file "<page>"
    Then the w3c HTML tester reports compliance
    Examples:
      | page                                              | comment                                                   |
      | https://stage.rosegoldthorp.com/                  |                                                           |
      | https://stage.rosegoldthorp.com/contentmarketing/ |                                                           |
      | https://stage.rosegoldthorp.com/socialmedia/      |                                                           |
      | https://stage.rosegoldthorp.com/pricing/          |                                                           |
      | https://stage.rosegoldthorp.com/about/            |                                                           |
      | https://stage.rosegoldthorp.com/about#filmmaker/  | ensure that operation of the tabs does not break the html |
      | https://stage.rosegoldthorp.com/about#marketer/   |                                                           |
      | https://stage.rosegoldthorp.com/contact/          |                                                           |

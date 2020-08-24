@standards @functional @wip
Feature: Standards Compliance

  Scenario Outline: Check Browser Console Errors
    Given I am on the "<page>" page
    Then the browser produces no messages
    Examples:
      | page     |
      |          |
      | /stories |

  Scenario Outline: Check page syntax guidelines
    Given I am on the "<page>" page
    Then the HTML syntax of that page is valid
    And the syntax of CSS linked into in that page is valid
    Examples:
      | page     |
      |          |
      | /stories |

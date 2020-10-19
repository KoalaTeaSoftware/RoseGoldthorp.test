@smoke
Feature: SUT Page titles and delivery
  If these pages do not draw fully, then further testing is very likely to give false failures.

  Scenario Outline: Visit a page
    When I navigate to the page "<address>"
    Then the page is fully drawn
    And the page title is "<title>"
    Examples:
      | address          | title                  |
      | http://          | Home                   |
      | /                | Home                   |
      | /home            | Home                   |
      | /about           | About Rose Goldthorp   |
      | contentmarketing | Content Marketing      |
      | socialmedia      | Social Media Marketing |
      | pricing          | Pricing                |
      | contact          | Contact Me             |

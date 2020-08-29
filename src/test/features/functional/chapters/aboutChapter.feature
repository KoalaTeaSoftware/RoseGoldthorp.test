@functional @about
Feature: About Chapter

  Scenario: visit the about chapter
    Given I am on the "about" page
    Then the about page shows the following roles
      | Marketer   |
      | Film Maker |
    And the about page shows only 2 roles

  Scenario: Look at the film maker role description
  The list of films on release is data drive, so we need to see if that operates as expected
    Given I am on the "about" page
    When I select the "Film Maker" role
    Then the about page shows more than 4 films on release
    And all links to film details arrive at a good page

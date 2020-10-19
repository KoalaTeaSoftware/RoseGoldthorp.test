@functional
Feature: Film Details Page
  These pages are built from data that is in PHP, and relies of files being available to provide targets for that data

  Scenario Outline: Examine various film details pages
    Given I navigate to the page "/about/films/<url>"
    And the page is fully drawn
    Then the page title is "<expectedTitle>"
    And the first heading is "<expectedTitle>"
    And the poster is populated
    And the description is populated
    And the dia "<is>" populated
    And there are <num> links
    Examples:
      | url                       | expectedTitle             | is | num |
      | the-rocky-road-to-freedom | The Rocky Road To Freedom | is | 1   |
      | fleurs-secret             | Fleur's Secret            | is | 3   |
      | a-ghost-in-corsets        | A Ghost In Corsets        | is | 4   |
      | silverville               | Silverville               | is | 3   |
      | watcher                   | Watcher                   | is | 3   |

  Scenario Outline: Check the syntax of the film details pages
    Given the w3C HTML tester reviews the file "/about/films/<url>"
    Then the w3c HTML tester reports compliance
    Examples:
      | url                       |
      | the-rocky-road-to-freedom |
      | fleurs-secret             |
      | a-ghost-in-corsets        |
      | silverville               |
      | watcher                   |

  Scenario Outline: Check links on the film details pages
  Expect 405 errors from Amazon,
    Given the w3c link checker reviews the file "/about/films/<url>"
    Then the w3c link checker reports compliance
    Examples:
      | url                         |
      | the-rocky-road-to-freedom   |
      | fleurs-secret               |
      | a-ghost-in-corsets          |
      | silverville                 |
      | watcher                     |
      # check that the unknown film default page is not a dead end
      | the-rocky-road-to-fred-home |

  Scenario: Ask for an unknown film
    Given I navigate to the page "about/films/the-rocky-road-to-fred-home"
    And the page is fully drawn
    Then the page title is "Unknown Film"
    And the first heading is "Unknown Film"

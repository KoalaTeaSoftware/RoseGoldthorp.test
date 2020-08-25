@functional @nav
Feature: Site Navigation
  As any user
  So that I can get around the site
  I want the navigation bar to work correctly

  Scenario Outline: Exercise the menu
    Given I am on the "<startingAt>" page
    Then I follow nav links and see the correct page
      | linkText          | expectedPageTitle      |
      | Home              | Rose Goldthorp         |
      | Content Marketing | Content Marketing      |
      | Social Media      | Social Media Marketing |
      | Pricing           | Pricing                |
      | About             | About Me               |
      | Contact           | Contact                |
    # This gives additional confidence that the previous step was sufficient
#    And there are 6 items visible on the menu
    Examples:
      | startingAt       |
      |                  |
      | home             |
      | contentmarketing |
      | socialmedia      |
      | pricing          |
      | about            |
      | contact          |
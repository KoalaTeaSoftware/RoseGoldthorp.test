@functional @nav
Feature: Site Navbar
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
      | Contact           | Contact Me             |
    # This gives additional confidence that the previously listed data was sufficient
    And there are 6 items visible on the menu
    # just to double-check that pages get fully drawn
    And I see the footer section
    Examples:
      | startingAt       |
      |                  |
      | home             |
      | contentmarketing |
      | socialmedia      |
      | pricing          |
      | about            |
      | contact          |
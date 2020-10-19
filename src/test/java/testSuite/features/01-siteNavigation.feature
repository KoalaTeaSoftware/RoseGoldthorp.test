@functional
Feature: Friendly URLs & Site Navigation
  The site allows the use of (and only really responds to) friendly URLs.

  Scenario Outline: Try silly addresses (in the address bar)
    Given I navigate to the page "<url>"
    Then the page title is "<expectedTitle>"
    Examples:
      | url             | expectedTitle |
      | /engine-trouble | Home          |
      | /pigs/are/great | Home          |

  Scenario Outline: Follow nav links on all pages
  This differs from the 'broken links' testing in that it demonstrates that the links in the nav bar actually take you
  to where you want to be taken.
  It is worth looking at a few pages, because relative links (if they are used) may work in some pages and not others.
  This may not be the fastest, or most elegant way of doing this, but it is simple.
    Given I navigate to the page "<url>"
    When I click on the nav link with text "<linkText>"
    Then the page title is "<expectedTitle>"
    Examples:
      | url             | linkText          | expectedTitle          |
      | /               | Content Marketing | Content Marketing      |
      | /               | Social Media      | Social Media Marketing |
      | /               | Pricing           | Pricing                |
      | /               | About             | About Rose Goldthorp   |
      | /               | Contact           | Contact Me             |

      | /socialmedia    | Content Marketing | Content Marketing      |
      | /socialmedia    | Social Media      | Social Media Marketing |
      | /socialmedia    | Pricing           | Pricing                |
      | /socialmedia    | About             | About Rose Goldthorp   |
      | /socialmedia    | Contact           | Contact Me             |

      # see that the main nav continues to work if a silly URL is used
      | /engine-trouble | Content Marketing | Content Marketing      |
      | /pigs/are/great | Content Marketing | Content Marketing      |
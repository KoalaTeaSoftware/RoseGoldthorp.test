Feature: Site Navigation
  The site allows the use of (and only really responds to) friendly URLs.

  @smoke @pot
  Scenario: Demonstrate that >Try silly addresses (in the address bar)< fails when it should
    Given the browser is showing the page "<url>"
    Then the page title is "Never going to be this"

  Rule: if the user enters silly stuff in the address bar, they end up on a friendly page
    Scenario Outline: Try silly addresses (in the address bar)
    Note that, because of the framework's tendency to build the location based on defaults, this is adding a path onto the end of the site's URL
      Given the browser is showing the page "<locationTail>"
    # i.e. the default title
      Then the first heading contains "Welcome"
      Examples:
        | locationTail    |
        | /engine-trouble |
        | /pigs/are/great |

  Rule: The site navigation takes you to pages in the site
    Scenario Outline: Follow nav links on some pages - inferring nav is OK on all pages
    This differs from the W3C 'broken links' testing in that it demonstrates that the links in the nav bar actually take you
    to where you want to be taken.
    It is worth looking at a few pages, because relative links (if they are used) may work in some pages and not others.
    This may not be the fastest, or most elegant way of doing this, but it is simple.
      Given the browser is showing the page "<startinPoint>"
      When I click on the nav link with text "<linkText>"
      Then the page title is "<expectedTitle>"
      Examples:
        | startinPoint    | linkText          | expectedTitle     |
        | /               | The Greenlands    | The greenlands    |
        | /               | Period Brit. Lit. | Period brit lit   |
        | /               | Released Features | Released features |
        | /               | About             | About             |
        | /               | Contact           | Contact           |

        | /the-greenlands | The Greenlands    | The greenlands    |
        | /the-greenlands | Period Brit. Lit. | Period brit lit   |
        | /the-greenlands | Released Features | Released features |
        | /the-greenlands | About             | About             |
        | /the-greenlands | Contact           | Contact           |
      # see that the main nav continues to work if a malicious browser location is used to start from
        | /engine-trouble | The Greenlands    | The greenlands    |
        | /pigs/are/great | About             | About             |
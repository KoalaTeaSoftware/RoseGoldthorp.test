Feature: SUT Page titles, delivery, and friendly URLs
  If these pages are not draw well, then further testing is very likely to give false failures.
  However, do not omit these from a 'real' test of the site

  @smoke @pot
  Scenario Outline: Demonstrate the the page title and first heading tests detect errors when they are expected to
    When the browser is showing the page "<address>"
    Then the page title is "<title>"
    And the first heading contains "<expected heading>"
    Examples:
      | address | title | expected heading |
      |         | Home  | Not this         |
      |         | wtf   | Welcome          |

  @smoke
  Scenario Outline: Smoke out all pages on the site
    When the browser is showing the page "<address>"
    Then the page title is "<title>"
    And the first heading contains "<expected heading>"
    And the page is fully drawn
    And all images are well formed
    Examples:
      | address           | title             | expected heading                  |
      | http://           | Home              | Welcome                           |
      | /home             | Home              | Welcome                           |
      | /about            | About             | About Rose Goldthorp              |
      | the-greenlands    | The greenlands    | The Greenlands transmedia project |
      | period-brit-lit   | Period brit lit   | The Period Brit. Lit. Project     |
      | released-features | Released features | Released Films                    |
      | contact           | Contact           | Contact Me                        |

  Rule: The landing page is the welcome page
    Scenario: Just enter the bare url of the site
      When the browser is showing the page ""
      Then the page title is "Home"
      And the first heading contains "Welcome"
      And the page is fully drawn
      And all images are well formed

  Rule: the site is available on secure and insectivore protocols
    Scenario Outline: Smoke out all pages on the site
      When the browser is showing the page "<address>"
      Then the page title is "<title>"
      And the first heading contains "<expected heading>"
      And the page is fully drawn
      And all images are well formed
      Examples:
        | address  | title | expected heading     |
        | http://  | Home  | Welcome              |
        | https:// | Home  | Welcome              |
        | /about   | About | About Rose Goldthorp |

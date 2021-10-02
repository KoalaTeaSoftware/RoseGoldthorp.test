Feature: Chapter - Welcome
  There is not a lot here, but code builds the carousel

  Rule: Site pages should be well formed
    Scenario Outline: Examine the contents of the page
      When the browser is showing the page "<location>"
      Then the page title is "Home"
      And the first heading contains "Welcome"
      And the page is fully drawn
      And all images are well formed
      Examples:
        | location |
        |          |
        | home     |

  Rule: Site components should abide by W3C syntax standards as far as possible
    Scenario: HTML Syntax Compliance with W3C standards
      When the w3C HTML tester reviews the file ""
      Then the w3c HTML tester reports compliance

    @wip
    Scenario: Check the instagram image becomes populated
      When the browser is showing the page ""
      Then the instagram feed becomes well formed

    @wip
    Scenario: Follow in instagram
      When the browser is showing the page ""
      Then the "Follow me on Instagram" link opens a second tab
      And tab 2 title contains "Rose Goldthorp (@rosegoldthorpfilms) • Instagram photos and videos"

    # Assume that the mailchimp stuff works properly, just check the getting there
    @wip
    Scenario: The newsletter links goes to a subscription page
      When the browser is showing the page ""
      Then the "Get my newsletter" link opens a second tab
      And tab 2 title contains "The Greenlands"

    @wip
    Scenario Outline: whether you enter your address, or not you get there
      Given the browser is showing the page ""
      And the newsletter field contain "<emailAddress>"
      Then the "Get my newsletter" link opens a second tab
      And tab 2 title contains "The Greenlands"
      # The title may have different text and contains stuff like <br>, so make it more robust
      And the subscription pages shows a title "subscribe"
      And the subscription pages shows a title "Greenlands"
      Examples:
        | emailAddress       |
        |                    |
        | gonzo@theGreat.com |

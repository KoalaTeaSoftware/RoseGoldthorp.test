Feature: Chapter - About
  There is not a lot here, but there are a couple of links in the text

  Rule: Site pages should be well formed
    Scenario: Examine the contents of the film maker section of this chapter
      When the browser is showing the page "about"
      Then the page title is "About"
      And the first heading contains "About Rose Goldthorp"
      And the page is fully drawn
      And all images are well formed

    Scenario: Check links on the about page
      When the w3c link checker reviews the file "about"
      Then the w3c link checker reports compliance

  Rule: Site components should abide by W3C syntax standards as far as possible
    Scenario: HTML Syntax Compliance with W3C standards
      When the w3C HTML tester reviews the file "about"
      Then the w3c HTML tester reports compliance



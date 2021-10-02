Feature: Chapter - Brit Lit
  There is not a lot here, but there are a couple of links in the text

  Rule: Site pages should be well formed
    Scenario: Examine the contents of the page
      When the browser is showing the page "period-brit-lit"
      Then the page title is "Period brit lit"
      And the first heading contains "Brit. Lit."
      And the page is fully drawn
      And all images are well formed

    Scenario: Check links on the page
      When the w3c link checker reviews the file "period-brit-lit"
      Then the w3c link checker reports compliance

  Rule: Site components should abide by W3C syntax standards as far as possible
    Scenario: HTML Syntax Compliance with W3C standards
      When the w3C HTML tester reviews the file "period-brit-lit"
      Then the w3c HTML tester reports compliance



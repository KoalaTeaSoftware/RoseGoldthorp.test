Feature: Standards Compliance
  Syntax errors could be a reason for functional failure, so it is worth regarding this as a smoke test as well.

  Rule: Site components should abide by W3C syntax standards as far as possible
    Scenario Outline: CSS Compliance with W3C standards
    This is intended for testing directly created components of the SUT
    For example, Bootstrap 4' css will generate a load of error messages from this tester
      Given the w3C CSS tester reviews the file "<file>"
      Then the w3c CSS tester reports compliance
      Examples:
        | file          |
        | /stylesV2.css |

  # Each chapter should have this in its own feature file
    @smoke
    Scenario Outline: HTML Compliance with W3C standards
      Given the w3C HTML tester reviews the file "<location>"
      Then the w3c HTML tester reports compliance
      Examples:
        | location          |
        |                   |
        | the-greenlands    |
        | about             |
        | period-brit-lit   |
        | released-features |
        | contact           |

  # Each chapter should have this in its own feature file
    @smoke
    Scenario Outline: Check links on a page
      Given the w3c link checker reviews the file "<location>"
      Then the w3c link checker reports compliance
      Examples:
        | location          |
        | the-greenlands    |
        | about             |
        | period-brit-lit   |
        | released-features |
        | contact           |

  #noinspection CucumberTableInspection
    @smoke @pot
    Scenario Outline: Check links on pages that will have an error, just to see that there are no more
    This is marked fragile as some link on the page will give an error when clicked by the automation framework
    Or time-out, or something - see the comments, so do it by hand
    Therefore, if you add it into an automated run, it will act as a Proof of Tests
      Given the w3c link checker reviews the file "<location>"
      Then the w3c link checker reports compliance
      Examples:
        | location | comment                                                                                |
        | /        | instagram gives "405 Method Not Allowed", or "429 Too Many Requests", maybe others too |


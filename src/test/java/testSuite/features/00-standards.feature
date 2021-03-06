@standards
Feature: Standards Compliance
  Syntax errors could be a reason for functional failure, so it is worth regarding this as a smoke test as well.

  Scenario Outline: CSS Compliance with W3C standards
  This is intended for testing directly created components of the SUT
  For example, Bootstrap 4' css will generate a load of error messages from this tester
    Given the w3C CSS tester reviews the file "<file>"
    Then the w3c CSS tester reports compliance
    Examples:
      | file        |
      | /styles.css |

  Scenario Outline: HTML Compliance with W3C standards
    Given the w3C HTML tester reviews the file "<url>"
    Then the w3c HTML tester reports compliance
    Examples:
      | url               |
      | /                 |
      | /contentmarketing |
      | /socialmedia      |
      | /pricing          |
      | /contact          |

  Scenario Outline: Check links on a page
  Expect 429 errors from Instagram
  405 from Amazon
    Given the w3c link checker reviews the file "<url>"
    Then the w3c link checker reports compliance
    Examples:
      | url               |
      | /                 |
      | /contentmarketing |
      | /socialmedia      |
      | /pricing          |
      | /contact          |


@controller
Feature: Redirection To Controller
  As a developer
  So that I can ensure that all page requests are correctly handled
  I want all requests for any page, or chapter to be handled by the controller
  This comes about from the design-decision to use this pattern
  If processing of the PHP fails at any point, then subsequent sections of the page are likely to be missing, so this
  gives confidence that no catastrophic failure has occurred

  Scenario Outline: Visit known chapters
  Notice that the chapter names are case insensitive as inputs, but will be capitalised on display
    Given I am on the "<chapter>" page
    Then The page title is "<ExpectedTitle>"
    And I see the banner section
    And I see the body section
    And I see the footer section
    Examples:
      | chapter | ExpectedTitle  | comment                |
      |         | Rose Goldthorp | home page              |
      | home    | Home           | specifically home      |
      | HOME    | Home           | case insensitive input |
      | HoME    | Home           | case insensitive input |

  Scenario Outline: Visit unknown chapters
    Given I am on the "<chapter>" page
    Then The page title is "<ExpectedTitle>"
    And I see the banner section
    And I see the body section
    And I see the footer section
    Examples:
      | chapter    | ExpectedTitle  | comment                                        |
      | qwertyuiop | Rose Goldthorp | can't imagine wanting a chapter with this name |
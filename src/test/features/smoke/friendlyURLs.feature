@smoke @site
Feature: Friendly URLs
  The controller will accept requests in the form /chapter/section/sub-section.
  This is a test just a smoke test for the site framework (i.e. chapter names, etc. depend on the site),
  but the functionality is common to all users of this framework.
  Full testing of the site navigation must be supplied elsewhere.

  Scenario Outline: Visit known chapters
  Notice that the chapter names are case insensitive as inputs, but will be capitalised on display
  The 'I am on...' test supplies the initial slash
    Given I am on the "<chapter>" page
    Then The page title is "<ExpectedTitle>"
    # if processing of the request fails, then it is likely that the page appears incompletely
    And I see the banner section
    And I see the body section
    And I see the footer section
    Examples:
      | chapter          | ExpectedTitle     | comment                                           |
      |                  | Rose Goldthorp    | home page inferred from the lack of specification |
      | HoME             | Home              | 'home' is a special case                          |
      | contentMarketing | Content Marketing | happens to be a known chapter                     |

  Scenario Outline: Visit unknown chapters
    Given I am on the "<chapter>" page
    Then The page title is "<ExpectedTitle>"
    And I see the banner section
    And I see the body section
    And I see the footer section
    Examples:
      | chapter    | ExpectedTitle  | comment                                        |
      | qwertyuiop | Rose Goldthorp | can't imagine wanting a chapter with this name |
      | /contact   | Rose Goldthorp | This should be treated as 'no chapter'         |
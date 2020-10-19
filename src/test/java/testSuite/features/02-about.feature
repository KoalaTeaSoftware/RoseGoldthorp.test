@functional
Feature: On Release list of films
  Pages in this chapter are data-driven. So the risks are things like Images and other content are not correctly specified, or drawn.
  The chapter has a 'list page' with links to details, which open a page from another site (in another tab)
  The stds checks are very useful for this page, so just in case @standards are not in this run, these tests a repeated here
  There are not enough scenarios to demand a background, we might as well add the compliance here.

  Scenario: HTML Syntax Compliance with W3C standards
  The HTML for both the sections of this chapter (marketer and film maker) will be present on the page, visibility is controlled by the tabs
    When the w3C HTML tester reviews the file "about"
    Then the w3c HTML tester reports compliance

  Scenario: Check links on the about page
  Expect false errors from You Tube
    When the w3c link checker reviews the file "about"
    Then the w3c link checker reports compliance

  Scenario: Examine the contents of the film maker section of this chapter
    When I navigate to the page "about#filmmaker"
    Then the page is fully drawn
    And the page title is "About Rose Goldthorp"
    And the numbers of thumbnails, titles and puffs are consistent
    And there is more than 4 listed film
    And all thumbnails are populated
    And all titles are populated
    And all puff paragraphs are populated

  Scenario: Follow links to film details
    Given I navigate to the page "about#filmmaker"
    And the page is fully drawn
    When I follow each of the links to film details the page has the expected title

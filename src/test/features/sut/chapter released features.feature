Feature: Chapter - Released Features list of films
  Pages in this chapter are data-driven. So the risks are things like Images and other content are not correctly specified, or drawn.
  The chapter has a 'list page' with links to details, which open a page from another site (in another tab)
  The stds checks are very useful for this page, so just in case @standards are not in this run, these tests a repeated here
  There are not enough scenarios to demand a background, we might as well add the compliance here.

  Rule: Site components should abide by W3C syntax standards as far as possible
    Scenario: HTML Syntax Compliance with W3C standards
    The HTML for both the sections of this chapter (marketer and film maker) will be present on the page, visibility is controlled by the tabs
      When the w3C HTML tester reviews the file "released-features"
      Then the w3c HTML tester reports compliance

    Scenario: Check links on the about page
    Expect false errors from You Tube
      When the w3c link checker reviews the file "released-features"
      Then the w3c link checker reports compliance

  Rule: Site pages should be well formed
    Scenario: Examine the contents of the film maker section of this chapter
      When the browser is showing the page "released-features"
      Then the page is fully drawn
      And the page title is "Released features"
      And the numbers of thumbnails, titles and puffs are consistent
      And there is more than 4 listed film
      And all thumbnails are populated
      And all titles are populated
      And all puff paragraphs are populated

  Rule: Following links on a page should result in the expected page being shown
    Scenario: Follow links to film details
      Given the browser is showing the page "about#filmmaker"
      And the page is fully drawn
      When following all of the links to film details produce a page with the expected title

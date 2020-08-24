@functional @wip
Feature: The Bestiary
  The bestiary is an ever-growing list of mildly comic articles on fantastic creatures
  It is built from a set of posts in the blog

  Scenario: The order of display
  A recent change, it was decided to have the most recently published creature at the top of the list
  (whitebox notes)
  - the publish date is not extracted from the feed and does not find its way into the HTML
  - so an 'almost as good' test is used
    Given I am on the "bestiary" page
    Then the first beast is not the entitled "The Elf"
    
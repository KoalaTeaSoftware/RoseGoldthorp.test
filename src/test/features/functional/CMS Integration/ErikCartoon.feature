@functional @wip
Feature: Erik Cartoon
  As a user
  So that I can enjoy the Erik cartoon
  I want to see the Erik cartoon

  Scenario: See the Erik cartoon
    Given I am on the "/stories" page
    Then the Erik cartoon shows 4 images
    # if there aren't 4 images, then it has gone so wrong that it is probably not worth carrying on
    And each Erik image is well formed
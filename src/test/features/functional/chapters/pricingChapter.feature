@functional @chapter
Feature: Pricing
  This page is very simple, the only real risk comes from the fact that the page is mostly data-driven.
  The other risk (the looks of the page) has to be assessed by a human

  Scenario: Visit the page
    Given I am on the "pricing" page
    Then There are more than 1 packages visible
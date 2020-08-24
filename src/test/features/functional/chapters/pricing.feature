@functional @chapter
Feature: Pricing
  This page is very simple, the only major risk comes from the fact that the page is mostly data-driven.
  The looks of the page have to be assessed by a human

  Scenario: Visit the page
    Given I am on the "pricing" page
    Then There are more than 1 packages visible
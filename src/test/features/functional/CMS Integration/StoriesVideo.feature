@functional
Feature: Stories Video
  As any user
  So that I can enjoy the video on this page
  I want to see the video

  Scenario: See the video
    Given I am on the "/stories" page
    Then the stories video is is well formed

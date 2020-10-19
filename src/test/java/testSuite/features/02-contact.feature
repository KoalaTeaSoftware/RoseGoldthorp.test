@functional
Feature: Contact Form operation & client-side verification
  The contact form should ensure that:
  * data is provided in all of the fields
  * the data is useful / not harmful
  * provide the user with feedback when:
  ** It does not like the user's entries
  ** The email is sent
  ** When the email is not sent

#  $nameRegex = "[A-Za-z0-9 .\-]+";
#  $maxNameLength = 30;
#  $maxEmailLength = 100; // the real maximum is about 320 chars, but it is unlikely that any more than this will be a real address
#  $maxSubjectLength = 100;
#  $msgMaxLen = 5000;
#  $msgMinLen = 10;

  Background: get onto the contact page
    Given I navigate to the page "contact"
    And the page is fully drawn

  Scenario: Prove that a message can (appear to be) sent
  As this is the first scenario to be run, if is succeeds, it give confidence that the following failures are not false negatives
    When I enter the following data
      | name     | Donald Duck                                                                      |
      | address1 | pi@staker.com                                                                    |
      | address2 | pi@staker.com                                                                    |
      | subject  | This is a test message, please delete it                                         |
      | message  | If you don't delete it, it will just clutter up you inbox and you won't be happy |
    And I send the message
    And an attempt to send is made
    Then the form is not visible
    And confirmation of sending is shown
    And the sending was successful

  Scenario Outline: Partially fill the form
    When I enter the following data
      | name     | <name>     |
      | address1 | <address1> |
      | address2 | <address2> |
      | subject  | <subject>  |
      | message  | <message>  |
    And I send the message
    Then the message is not sent
    Examples:
      | name        | address1      | address2      | subject                                  | message                                                                          |
      |             | pi@staker.com | pi@staker.com | This is a test message, please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck |               | pi@staker.com | This is a test message, please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck | pi@staker.com |               | This is a test message, please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck | pi@staker.com | pi@staker.com |                                          | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck | pi@staker.com | pi@staker.com | This is a test message, please delete it |                                                                                  |

  Scenario Outline: Provide an illegal name
  The form allows the entry of illegal chars but will not submit
    When I enter the following data
      | name     | <name>                                                                           |
      | address1 | pi@staker.com                                                                    |
      | address2 | pi@staker.com                                                                    |
      | subject  | This is a test message, please delete it                                         |
      | message  | If you don't delete it, it will just clutter up you inbox and you won't be happy |
    And I send the message
    Then the message is not sent
    Examples:
      | name         |
      | Donald Duck! |

  Scenario Outline: Provide an over-long name
  The field will truncate at the allowed length
    When I enter the following data
      | name     | <name> |
      | address1 |        |
      | address2 |        |
      | subject  |        |
      | message  |        |
    Then the name field contains 30 characters
    Examples:
      | name                            |
      | Donald DuckDonald DuckDonald Do |

  Scenario Outline: Provide non-matching email addresses
  These should be rejected when the send button is clicked
    When I enter the following data
      | name     | Donald Duck                                                                      |
      | address1 | <address1>                                                                       |
      | address2 | <address2>                                                                       |
      | subject  | This is a test message, please delete it                                         |
      | message  | If you don't delete it, it will just clutter up you inbox and you won't be happy |
    And I send the message
    Then the message is not sent
    Examples:
      | address1       | address2       |
      | pi@staker.com  | pi@skater.com  |
      | alpha@beta.com | beta@alpha.com |

  Scenario: Provide over-long email addresses
  The will be truncated at the max length. Of course this could mean that the address is also syntactically incorrect
    When I enter the following data
      | name     |                                                                                                                |
      | address1 | alpha@bet1asdfghbet2asdfghbet3asdfghbet4asdfghibet5asdfghbet6asdfghbet7asdfghbet8asdfghi9asdfghbet1asdfghi.com |
      | address2 | alpha@bet1asdfghbet2asdfghbet3asdfghbet4asdfghibet5asdfghbet6asdfghbet7asdfghbet8asdfghi9asdfghbet1asdfghi.com |
      | subject  |                                                                                                                |
      | message  |                                                                                                                |
    Then the email1 field contains 100 characters
    Then the email2 field contains 100 characters

  Scenario: Provide overlong subject
  This is verifying that the form will not submit if the offered values do not suit the parameters
    When I enter the following data
      | name     |                                                                                                         |
      | address1 |                                                                                                         |
      | address2 |                                                                                                         |
      | subject  | Lorem ipsum dolor sit amet, consectetur erat curae. Lorem ipsum dolor sit amet, consectetur erat curae. |
      | message  |                                                                                                         |
    Then the subject field contains 100 characters

  Scenario: Provide overlong message
  The form will truncate
    When I enter a message 5001 chars long
    Then the message field contains 5000 characters

  Scenario Outline: Provide too-short message
  These should be rejected when the send button is clicked
    When I enter the following data
      | name     | Duckal Dond        |
      | address1 | ducky@ducksrus.com |
      | address2 | ducky@ducksrus.    |
      | subject  | Now is the time    |
      | message  | <message>          |
    And I send the message
    Then the message is not sent
    Examples:
      | message   |
      | woof woof |
      | alphabets |

  Scenario: watch the message field's character counter
  Slightly unusual when/then, but you can see what is going on
    Then the message counter field contains 5000
    When I enter a message 30 chars long
    Then the message counter field contains 4970
    When I enter a message 1 chars long
    Then the message counter field contains 4969
    When I enter a message 4000 chars long
    Then the message counter field contains 969
    When I enter a message 1000 chars long
    Then the message counter field contains 0
    And the message field contains 5000 characters
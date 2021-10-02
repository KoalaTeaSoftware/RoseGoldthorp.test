Feature: Film Details Page
  These pages are built from data that is in PHP, and relies of files being available to provide targets for that data

  Rule: Site components should abide by W3C syntax standards as far as possible
    Scenario Outline: Check the syntax of the film details pages
      Given the w3C HTML tester reviews the file "released-features/films/<url>"
      Then the w3c HTML tester reports compliance
      Examples:
        | url                       |
        | the-rocky-road-to-freedom |
        | fleurs-secret             |
        | a-ghost-in-corsets        |
        | silverville               |
        | watcher                   |

    #noinspection CucumberTableInspection
    Scenario Outline: Check links on the film details pages
      Given the w3c link checker reviews the file "released-features/films/<pathTail>"
      Then the w3c link checker reports compliance
      Examples:
        | pathTail      | comment |
#        | the-rocky-road-to-freedom | YouTube: (N/A) Forbidden by robots.txt |
        | fleurs-secret |         |
#        | a-ghost-in-corsets        | Amazon: 405 Method Not Allowed        |
        | silverville   |         |
        | watcher       |         |

  Rule: if the user enters silly stuff in the address bar, they end up on a friendly page
    Scenario: Ask for an unknown film
      Given the browser is showing the page "released-features/films/the-rocky-road-to-freedo"
      And the page is fully drawn
      Then the page title is "Unknown Film"
      And the first heading is "Unknown Film"

  Rule: Site pages should be well formed
    Scenario Outline: Examine various film details pages
      Given the browser is showing the page "released-features/films/<pathTail>"
      And the page is fully drawn
      Then the page title is "<expectedTitle>"
      And the first heading is "<expectedTitle>"
      And the poster is populated
      And the description is populated
      And the dia "<is>" populated
      And there are <num> links
      Examples:
        | pathTail                  | expectedTitle             | is | num |
        | the-rocky-road-to-freedom | The Rocky Road To Freedom | is | 2   |
        | fleurs-secret             | Fleur's Secret            | is | 3   |
        | a-ghost-in-corsets        | A Ghost In Corsets        | is | 4   |
        | silverville               | Silverville               | is | 3   |
        | watcher                   | Watcher                   | is | 3   |

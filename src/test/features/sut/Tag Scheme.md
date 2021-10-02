Read this in conjunction with the TestRunner.

* If it hasn't got a tag, then it should be part of the full acceptance suite
* 
* @smoke
  * These are of use, as a one-off, if a big change has been made.
  * They are so simple, that it is quite fast
  * The things tested are (effectively) tested many times in the more extensive functional tests. So it is as well to exclude @smoke from the main testing effort.

* @pot
  * part of a smoke test, these are 'proof of test'
  * They are expected to fail, with the sort of message that you'd expect
Feature: Login

  Background:
    Given database is clean

  Scenario: Login User for the first time
    When the login endpoint is called with:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
    Then the user is created in the db with:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
      | username   | ManuelSR                             |

  Scenario: Login already existing user
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
      | username   | ManuelSR                             |
    When the login endpoint is called with:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
    Then the user is not created

Feature: Login

  Scenario: Login User for the first time
    When the login endpoint is called with:
      | externalId                           | fullName               |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 | Manuel Sanchez Ramirez |
    Then the user is created in the db with:
      | externalId                           | fullName               | username |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 | Manuel Sanchez Ramirez | ManuelSR |

  Scenario: Login already existing user
    Given the following user exists:
      | externalId                           | fullName               | username |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 | Manuel Sanchez Ramirez | ManuelSR |
    When the login endpoint is called with:
      | externalId                           | fullName               |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 | Manuel Sanchez Ramirez |
    Then the user is not created

Feature: Get teams

  Scenario: Teams for a user are fetched from the database
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
      | username   | ManuelSR                             |
    And the user is part of two teams
      | externalId        | ba222e1f-85a2-463a-8e7d-53d3d8a16408  |
      | teamName1         | Cow                                   |
      | teamName2         | Boy                                   |
      | teamDescription1  | Yeehaw!!                              |
      | teamDescription2  | We love cake                          |

    When the teams endpoint is called with current logged user:
    Then the teams are returned from the database:

  Scenario: Teams for a user are fetched from the database
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
      | username   | ManuelSR                             |
    When the teams endpoint is called with current logged user:
    Then no teams are returned from the database:

  Scenario: Teams for a user are fetched from the database
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | John Doe                             |
      | username   | JohnDoe                              |
    And the user is part of one team
      | externalId       | ba222e1f-85a2-463a-8e7d-53d3d8a16408  |
      | teamName         | Cow                                   |
      | teamDescription  | Yeehaw!!                              |
    When the teams endpoint is called with current logged user:
    Then the team are returned from the database:

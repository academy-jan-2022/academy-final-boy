Feature: Get teams

  Background:
    Given database is clean

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

    When the teams endpoint is called with:
      | externalId      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |

    Then the teams are returned from the database:


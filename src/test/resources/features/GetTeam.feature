Feature: Get team

  Scenario: A team is fetched from db by id with populated users
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
      | username   | ManuelSR                             |

    And the user is part of a team
      | externalId        | ba222e1f-85a2-463a-8e7d-53d3d8a16408  |
      | teamName          | Cow                                   |
      | teamDescription   | We love cake                          |

    When the get team endpoint is called with the team id
    Then the team is returned from the db with the members included

   Scenario: No team is fetched from the db because it does not exist
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
      | username   | ManuelSR                             |
    When the team endpoint is called with a non existing team id
    Then an exception is thrown


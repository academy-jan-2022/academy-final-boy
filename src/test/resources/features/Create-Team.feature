Feature: Create team

  Background:
    Given database is clean

  Scenario: Team is created
    Given the user is created in the db with:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | fullName   | Manuel Sanchez Ramirez               |
      | username   | ManuelSR                             |
    When the create team endpoint is called with:
      | externalId      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | teamName        | extreme cowboy association           |
      | teamDescription | yeehaw                               |
    Then the team is created in the db with:
      | members         | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | teamName        | extreme cowboy association           |
      | teamDescription | yeehaw                               |
    And the team is added on the user
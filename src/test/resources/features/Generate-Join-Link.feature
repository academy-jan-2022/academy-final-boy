Feature: Generate join link

  Background: Team is created
    Given the following user exists:
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
      | externalId      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
    And the team is added on the user

  Scenario: Generate join link
    When the generate join link endpoint is called
    Then a link is generated
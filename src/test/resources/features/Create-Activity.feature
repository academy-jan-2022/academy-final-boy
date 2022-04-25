Feature: Create activity

  Scenario:
    Given the following users exist:
      | externalId                           | fullName               | username |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 | Manuel Sanchez Ramirez | ManuelSR |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16409 | Jose Alvarez           | JoseA    |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16407 | John Smith             | JohnS    |
      | ba222e1f-85a2-463a-8e7d-53d3d8a16406 | John Doe               | JohnD    |
    And the create team endpoint is called with:
      | externalId      | ba222e1f-85a2-463a-8e7d-53d3d8a16408 |
      | teamName        | extreme cowboy association           |
      | teamDescription | yeehaw                               |
    When the activity is created with the users and the following information:
      | activityName   | fizzBuzz |
      | numberOfGroups | 2        |
    Then the activity gets added to the team
Feature: Remove user

  Background:
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408    |
      | fullName   | Manuel Sanchez Ramirez                  |
      | username   | ManuelSR                                |

    And the following team exists:
      | teamName          | Cow                              |
      | teamDescription   | We love cake                     |

    And the token exists:
      | joinId        | ce222e1f-85a2-463a-8e7d-53d3d8a16428 |

    When the join team endpoint is called with the join token id
    Then the user should be part of the team


  Scenario: A joined user request to leave team
    When the user requests to be removed from the team
    Then the user is no longer part of the team

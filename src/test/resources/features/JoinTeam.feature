Feature: Join Team

  Scenario: An existing user requests to join a team
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
    And the team id is returned

  Scenario: A non-existing user requests to join a team
    Given the following team exists:
      | externalId        | ba222e1f-85a2-463a-8e7d-53d3d8a16408  |
      | teamName          | Cow                                   |
      | teamDescription   | We love cake                          |

    When the join team endpoint is called with the join token id
    Then an exception is thrown with message
      | message        | User does not exist  |

  Scenario: An expired token is used
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408    |
      | fullName   | Manuel Sanchez Ramirez                  |
      | username   | ManuelSR                                |

    And the following team exists:
      | teamName          | Cow                              |
      | teamDescription   | We love cake                     |

    And the token is expired:
      | joinId        | ce222e1f-85a2-463a-8e7d-53d3d8a16428 |

    When the join team endpoint is called with the join token id
    Then an exception is thrown with message
      | message        | Token is expired  |

  Scenario: Token does not exist
    Given the following user exists:
      | externalId | ba222e1f-85a2-463a-8e7d-53d3d8a16408    |
      | fullName   | Manuel Sanchez Ramirez                  |
      | username   | ManuelSR                                |

    And the following team exists:
      | teamName          | Cow                              |
      | teamDescription   | We love cake                     |

    When the join team endpoint is called with the join token id
    Then an exception is thrown with message
      | message        | Invalid token  |
Feature: Check heartbeat

  Scenario: Check heartbeat
    When Heartbeat api is called
    Then the status is up

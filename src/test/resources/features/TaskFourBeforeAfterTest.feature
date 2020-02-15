Feature: Sql tests

  @Test
Scenario: Find agent with name Lucida and assert all his data

  When I select agent by name Lucida
  Then data is correct
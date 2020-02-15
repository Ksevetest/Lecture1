Feature: Sql tests

  Background:
    Given database table is created

#@Test
  Scenario: Find agent with name Lucida and assert all his data

    When you select agent by name Lucida
    Then selected data is correct
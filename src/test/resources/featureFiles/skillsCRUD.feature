@skill
Feature: Skill Features

  @JIRA-12345 @docString
  Scenario: Create Skill
    Given user generate token for profile "admin"
    And user create skill
    """
    {"name":"skillName","description":"skillDescription"}
    """
    And user update skill
    """
    {"name":"skillName","description":"skillDescription"}
    """

  @random
  Scenario: Create Skill
    Given user generate token for profile "admin"
    And user create random skill
    And user update skill with random data

  Scenario: Create Skill
    Given user generate token for profile "admin"
    And user create entity for "createSkillPayload"
    And user update entity for "updateSkillPayload"


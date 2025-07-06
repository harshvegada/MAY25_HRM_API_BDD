@widget @regression
Feature: Dashboard Feature

  Background: Token Generation
    Given user generate token for profile "admin"

  @JIRA-1234
  Scenario: Verify expected widget
    Then verity below widget list are coming
      | My Actions                                        |
      | Quick Access                                      |
      | Time At Work                                      |
      | Employees on Leave Today                          |
      | Latest News                                       |
      | Latest Documents                                  |
      | Performance Quick Feedback                        |
      | Current Year's Leave Taken by Department          |
      | Buzz Latest Posts                                 |
      | Leave Taken on Each Day of the Week Over Time     |
      | Leave Scheduled in Each Month                     |
      | Leave Taken on Each Calendar Month Over the Years |
      | Headcount by Location                             |
      | Annual Basic Payment by Location                  |
      | Annual Basic Payment by Location                  |


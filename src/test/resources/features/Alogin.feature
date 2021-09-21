@Login
Feature:Login feature
  Scenario: Login
    Given I am on the website
    When I enter the credentials
    Then I should be able to login main website page

  @DeliveryConfig

  Scenario: Delivery Configuration
    Given I am on the main page
    When I clik the "Delivery Configuration" Module
    Then I should navigate to the Module page
    And I should be able to search "canada" data on search column
    Then I should be able to check Run Daemon first box
    Then I should be able to check Run Daemon Second box
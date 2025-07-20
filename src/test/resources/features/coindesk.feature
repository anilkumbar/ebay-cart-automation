Feature: Coindesk API

  Scenario: Validate BPIs in Coindesk current price response
    Given I send a GET request to Coindesk current price endpoint
    Then the response should contain 3 BPIs: USD, GBP, EUR
    And the GBP description should be "British Pound Sterling"
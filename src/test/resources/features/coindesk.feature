Feature: Coindesk API

  Scenario: Validate BPIs in Coindesk current price response
    Given I send a GET request to Coindesk current price endpoint
    Then the response status code should be 200
    And the response should contain prices for USD, GBP, and EUR
    And each currency should have a market cap and total volume
    And the price change percentage over the last 24 hours should be present
    And the homepage URL should not be empty
Feature: eBay Shopping Cart

  Scenario: Add a book to cart and verify cart count
    Given I open the eBay website
    When I search for "book"
    And I click on the first book in the list
    And I add the book to the cart
    Then I should see the cart updated with the correct number of items

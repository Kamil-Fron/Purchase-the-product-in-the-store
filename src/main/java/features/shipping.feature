Feature: Purchase a Hummingbird Printed Sweater

  Scenario: Purchase and verify the order details
    Given I am logged in to the same user account from task_1: login "nvmbxlntxynutmcgfa@tcwlx.com" with password "mybestpassword"
    When I choose the Hummingbird Printed Sweater to purchase
    And select size "M"
    And add "5" items to the cart
    And proceed to checkout
    And confirm the address
    And choose delivery option
    And select payment method Pay by Check
    And click on PLACE ORDER and take screenshot
    Then I should see a confirmation of the order with the correct total amount
    And I should see the order in the order history with status "Awaiting check payment" and the same total amount
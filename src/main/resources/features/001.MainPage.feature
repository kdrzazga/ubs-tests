Feature: 1.Main page

  Scenario: 1.1 Check main menu availability
    When I navigate to index site
    Then I expect the side menu contains the following positions: Wealth Management, Asset Management, Careers, About us

  Scenario: 1.2 Navigate to 'Your life goals' thru main menu
    When Through main menu I go to Your life goals and then to Get in touch site
    And I submit an incomplete Wealth Management Req
    Then I cannot submit

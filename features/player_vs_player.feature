Feature: Game is set up properly 
  This feature is to make sure a new human vs human tic tac toe game is set up
  properly.

  Scenario: Instructions and entering a valid name
    Given the game has started
    Then I should see instructions on how to play the game
    And I should be asked for my name
    When I enter a valid name
    Then I should be asked for my player piece

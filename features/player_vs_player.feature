Feature: Game is set up properly 
  This feature is to make sure a new human vs human tic tac toe game is set up
  properly.

  Scenario: Instructions and valid prompts for player 1
    Given I enter a valid name
    And I enter a valid piece
    And the game has started
    Then I should see instructions on how to play the game
    And I should be asked for my name
    And I should be asked for my player piece

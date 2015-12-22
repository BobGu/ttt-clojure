Feature: Instructions
  I start a game and expect to see detailed instructions including an example board

  Scenario: Starting a new tic tac toe game
    Given I start a new tic tac toe game
    Then I should see instructions on how to play the game

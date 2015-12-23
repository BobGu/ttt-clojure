Feature: Starting a game
  This is to make sure the game flow of tic tac toe is correct
  To make sure the user has a good user experience and the order for the
  tic tac toe game makes sense.

  Scenario: Instruction for the game
    Given the game has started
    Then I should see instructions on how to play the game

  Scenario: A user gets asked for his name
    Given the game has started
    And I've already seen the instructions
    Then I should be asked for my name

  Scenario: A user gets asked what piece he wants to be
    Given the game has started
    And I've already seen the instructions
    And I've been asked for my name
    Then I should be asked what piece I want to be

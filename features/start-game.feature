Feature: Starting a game
  This is to make sure the game flow of tic tac toe is correct
  To make sure the user has a good user experience and the order for the
  tic tac toe game makes sense.

  Background:
    Given the game has started

  Scenario: Instruction for the game
    Then I should see instructions on how to play the game

  Scenario: A user gets asked for his name
    Given I've already seen the instructions
    Then I should be asked for my name
  

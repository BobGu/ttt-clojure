Feature: Game is set up properly 
  This feature is to make sure a new human vs human tic tac toe game is set up
  properly.

  Scenario: Instructions and valid prompts for player 1
    Given I enter a valid name
    And I enter a valid piece
    And I enter a valid name
    And the game has started
    Then I should see instructions on how to play the game
    And I should be asked for my name
    And I should be asked for my player piece

  Scenario: A player gets asked for their piece again if entered incorrectly
    Given I enter a valid name
    And I enter a piece that is not a X or O
    And I enter a valid piece
    And I enter a valid name
    And the game has started
    Then I should have been told the piece I entered was invalid 
    And I should have been asked for my piece again

  Scenario: A second player gets asked for their name
    Given player 1 has entered all their info correctly
    And I enter a valid name
    And the game has started
    Then each player should have been asked for their name

  Scenario: Choosing who goes first
    Given the game is setup with players information
    And I enter which player goes first
    Then I expect to be asked which player should go first

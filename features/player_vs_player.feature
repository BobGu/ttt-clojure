Feature: Game is set up properly
  This feature is to make sure a new human vs human tic tac toe game is set up
  properly.

  Scenario: Instructions and valid prompts for player 1
    Given I enter a valid name
    And I enter a valid piece
    And I enter a valid name
    And I enter which player goes first
    And I enter valid moves for the game
    And the game has started
    Then I should see instructions on how to play the game
    And I should be asked for my name
    And I should be asked for my player piece

  Scenario: A player gets asked for their piece again if entered incorrectly
    Given I enter a valid name
    And I enter a piece that is not a X or O
    And I enter a valid piece
    And I enter a valid name
    And I enter which player goes first
    And I enter valid moves for the game
    And the game has started
    Then I should have been told the piece I entered was invalid
    And I should have been asked for my piece again

  Scenario: A second player gets asked for their name
    Given player 1 has entered all their info correctly
    And I enter a valid name
    And I enter which player goes first
    And I enter valid moves for the game
    And the game has started
    Then each player should have been asked for their name

  Scenario: Choosing who goes first
    Given the game is setup with players information
    And I enter which player goes first
    And I enter valid moves for the game
    And the game has started
    Then I expect to be asked which player should go first

  Scenario: Seeing an empty board to start the game
    Given the players have entered names and turn order
    And I enter valid moves for the game
    And the game has started
    Then I expect to see an empty board

  Scenario: Player gets asked to place a piece when it is their turn
    Given the players have entered names and turn order
    And I enter valid moves for the game
    And the game has started
    Then I expect the first player is asked to choose a spot on the board

  Scenario: When first player to enter their name chooses to go second, second
            player who enters their name gets asked first to pick a piece on the
            board.
   Given the game is setup with players information
   And I pick the first player to enter their name to go second
   And I enter valid moves for the game
   And the game has started
   Then I expect the second player to be asked to choose a spot

  Scenario: Placing a piece on an empty board
    Given the players have entered names and turn order
    And the first player has picked a space on the board
    And the second player has picked a space on the board
    And the game has started
    Then I expect to see a board with that space filled

  Scenario: Second player placing a piece on the board
    Given the players have entered names and turn order
    And the first player has picked a space on the board
    And the second player has picked a space on the board
    And the game has started
    Then I expect to see a board with that space filled with the second players piece


Feature: Player vs Player game 
  This is to make sure the game flow of tic tac toe is correct
  To make sure the user has a good user experience and the order for the
  tic tac toe game makes sense.

  Scenario: Instructions and entering a valid name
    Given the game has started
    Then I should see instructions on how to play the game
    And I should be asked for my name
    When I enter a valid name
    Then I should be asked for my player piece

    #  Scenario: A user gets asked what piece they want to be
    #    Given the game has started
    #    And I've already seen the instructions
    #    And I've been asked for my name
    #    Then I should be asked what piece I want to be
    #
    #  Scenario: A user gets asked what piece they want to be again if piece in not X or an O
    #    Given the game has started
    #    And I've already seen the instructions
    #    And I've been asked for my name
    #    And I've been asked what piece I want to be
    #    When I've entered the letter Y
    #    Then I should be asked again what piece I want to be

(use 'ttt-clojure.game)
(use 'speclj.core)

(def world (atom {:outputs []
                  :input ""  }))

(Given #"^I enter a valid name$" []
  (swap! world update-in [:input]
    str"Robert\n"))

(Given #"^I enter a valid piece$" []
  (swap! world update-in [:input]
    str"x\n"))

(Given #"^the game has started$" []
  (swap! world update-in [:outputs]
    conj (str (with-out-str (with-in-str (@world :input)(start-game)))))
  (reset! world {:outputs (@world :outputs)
                 :input ""}))

(Then #"^I should see instructions on how to play the game$" []
  (should-contain #"Welcome to tic tac toe"
    (last (@world :outputs))))

(Then #"^I should be asked for my name$" []
  (should-contain #"What is your name*"
    (last (@world :outputs))))


(Then #"^I should be asked for my player piece$" []
  (should-contain #"What piece would you like"
    (last (@world :outputs))))

(Given #"^I enter a piece that is not a X or O$" []
  (swap! world update-in [:input]
    str"pooooopy\n"))


(Then #"^I should have been told the piece I entered was invalid" []
  (should-contain #"is not a valid input"
    (last (@world :outputs))))

(Then #"^I should have been asked for my piece again" []
  (should= 2
    (count (re-seq  #"What piece would you like"
      (last (@world :outputs))))))

(Given #"^player 1 has entered all their info correctly" []
  (swap! world update-in [:input]
    str "Billy\nx\n"))

(Then #"^each player should have been asked for their name" []
  (should= 2
    (count (re-seq #"What is your name"
      (last (@world :outputs))))))

(Given #"^the game is setup with players information$" []
  (swap! world update-in [:input]
    str "john\nx\nbobby\n"))

(Given #"^I enter which player goes first" []
  (swap! world update-in[:input]
    str "1\n"))

(Then #"^I expect to be asked which player should go first$" []
  (should-contain #"you would like john*"
    (last (@world :outputs))))

(Given #"^the players have entered names and turn order$" []
  (swap! world update-in [:input]
    str "john\nx\nbobby\n1\n"))

(Then #"^I expect the first player is asked to choose a spot on the board$" []
  (should-contain #"Where would you like to move john"
    (last (@world :outputs))))

(Given #"^I pick the first player to enter their name to go second$" []
  (swap! world update-in [:input]
    str "2\n"))

(Then #"^I expect the second player to be asked to choose a spot$" []
  (should-contain #"Where would you like to move bobby"
    (last (@world :outputs))))

(Then #"^I expect to see an empty board$" []
  (should-contain "     |     |     |\n 0   |  1  |  2  |    \n_____|_____|_____|\n     |     |     |\n 3   |  4  |  5  |    \n_____|_____|_____|\n     |     |     |\n 6   |  7  |  8  |    \n_____|_____|_____|"
    (last (@world :outputs))))

(Given #"^the first player has picked the top left space on the board$" []
  (swap! world update-in [:input]
    str "0\n"))

(Then #"^I expect to see a board with that space filled$" []
  (should-contain "     |     |     |\n 0   |  1  |  2  |    \n_____|_____|_____|\n     |     |     |\n X   |  4  |  O  |    \n_____|_____|_____|\n     |     |     |\n 6   |  7  |  8  |    \n_____|_____|_____|"
    (last (@world :outputs))))

(Given #"^I enter valid moves for the game$" []
  (swap! world update-in [:input]
    str "0\n3\n1\n4\n2\n"))

(Given #"^the second player has picked the center space on the board$" []
  (swap! world update-in [:input]
    str "4\n"))

(Given #"^the players continue to move until someone has won the game$" []
  (swap! world update-in [:input]
    str "1\n3\n2\n"))

(Then #"^I expect to see the center square filled with the second players piece$" []
  (should-contain"     |     |     |\n X   |  X  |  X  |    \n_____|_____|_____|\n     |     |     |\n O   |  O  |  5  |    \n_____|_____|_____|\n     |     |     |\n 6   |  7  |  8  |    \n_____|_____|_____|"
    (last (@world :outputs))))

(Then #"^I expect to see the top left space filled by the first players piece$" []
  (should-contain "     |     |     |\n X   |  X  |  X  |    \n_____|_____|_____|\n     |     |     |\n O   |  O  |  5  |    \n_____|_____|_____|\n     |     |     |\n 6   |  7  |  8  |    \n_____|_____|_____|"
    (last (@world :outputs))))

(Given #"^the player that enters their name first chooses to be the letter X$" []
  (swap! world update-in [:input]
    str "Robert\nx\nJohn\n"))

(Given #"^that player chooses to go 2nd$" []
  (swap! world update-in [:input]
    str "2\n"))

(Given #"^the other player chooses a space on the board$" []
  (swap! world update-in [:input]
    str "4\n"))

(Given #"^they have moved a few times each$" []
  (swap! world update-in [:input]
    str "0\n3\n1\n4\n"))

(Given #"^the first player makes a winning move" []
  (swap! world update-in [:input]
    str "2\n"))

(Then #"^I expect to see a message congratulating that player on winning the game$" []
  (should-contain #"Congratulations john you have won the game*"
    (last (@world :outputs))))

(Then #"^I expect the top left space to be filled by the letter O$" []
  (should-contain "     |     |     |\n O   |  O  |  O  |    \n_____|_____|_____|\n     |     |     |\n X   |  X  |  5  |    \n_____|_____|_____|\n     |     |     |\n 6   |  7  |  8  |    \n_____|_____|_____|"
    (last (@world :outputs))))

(Given #"^they each play pieces until the board is filled$" []
  (swap! world update-in [:input]
    str "1\n0\n3\n2\n5\n4\n6\n7\n8\n"))

(Then #"^I expect to see a message telling me the game ended in a tie$" []
  (should-contain #"The game is a tie*"
    (last (@world :outputs))))


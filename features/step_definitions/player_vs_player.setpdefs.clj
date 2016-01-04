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

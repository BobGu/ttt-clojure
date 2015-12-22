(use 'speclj.core)
(use 'ttt-clojure.game)

(Given #"^I start a new tic tac toe game$" []
       (if (boolean (resolve 'start-game))
         "I start a new tic tac toe game"))

(Then #"^I should see instructions on how to play the game" []
      (should-contain #"tic tac toe*" (with-out-str (start-game))))

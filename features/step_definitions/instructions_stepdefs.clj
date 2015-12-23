(use 'speclj.core)
(use 'ttt-clojure.game)

(Then #"^I should see instructions on how to play the game" []
      (should-contain #"tic tac toe*" (with-out-str (with-in-str "Robert" (start-game)))))

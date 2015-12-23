(use 'speclj.core)
(use 'ttt-clojure.game)

(Then #"^I should see instructions on how to play the game" []
      (should-contain #"tic tac toe*"
        (with-in-str "Robert" (with-out-str (start-game)))))

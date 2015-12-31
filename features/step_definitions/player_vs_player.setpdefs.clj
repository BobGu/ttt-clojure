(use 'ttt-clojure.game)
(use 'speclj.core)

(def world (atom {:game []
                  :output []
                  :input ""}))


(Given #"^I enter a valid name$" []
  (swap! world update-in [:input]
    str (@world :input) "Robert\n"))

(Given #"^I enter a valid piece$" []
  (swap! world update-in [:input]
    str (@world :input) "x\n"))

(Given #"^the game has started$" []
  (swap! world update-in [:output]
     conj (with-out-str (with-in-str (@world :input)(start-game)))))

(Then #"^I should see instructions on how to play the game$" []
  (should-contain #"Welcome to tic tac toe"
   (first (@world :output))))

(Then #"^I should be asked for my name$" []
  (should-contain #"What is your name*" 
    (first (@world :output))))


(Then #"^I should be asked for my player piece$" []
    (should-contain #"What piece would you like"
      (first (@world :output))))














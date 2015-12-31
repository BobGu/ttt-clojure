(use 'ttt-clojure.game)
(use 'speclj.core)

(def world (atom {:game []
                  :output []
                  :input "Robert\nX"}))


(Given #"^the game has started$" []
  (swap! world update-in [:output]
     conj (with-out-str (with-in-str (@world :input)(start-game)))))

(Then #"^I should see instructions on how to play the game$" []
  (should-contain #"Welcome to tic tac toe"
   (first (@world :output))))

(Then #"^I should be asked for my name$" []
  (should-contain #"What is your name*" 
    (first (@world :output))))

(When #"^I enter a valid name$" []
  (print "something happens here"))

(Then #"^I should be asked for my player piece$" []
    (should-contain #"What piece would you like"
      (first (@world :output))))














;(Then #"^I should be asked again what piece I want to be$" []
;  (should= 2 
;    (count (re-seq #"What piece" (with-out-str (with-in-str "Robert\nPooop\nO" (start-game)))))))

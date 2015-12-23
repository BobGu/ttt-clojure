(use 'ttt-clojure.game)
(use 'speclj.core)

(Given #"^I've been asked what piece I want to be$" [])

(When #"^I've entered the letter Y$" []
      "I've entered the letter Y")

(Then #"^I should be asked again what piece I want to be$" []
  (should= 2 
    (count (re-seq #"What piece" (with-out-str (with-in-str "Robert\nPooop\nO" (start-game)))))))

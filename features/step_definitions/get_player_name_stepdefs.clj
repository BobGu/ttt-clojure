(use 'speclj.core)
(use 'ttt-clojure.game)

(Given #"^I've already seen the instructions"[]
       "I've already seen the instructions")

(Then #"^I should be asked for my name" []
      (should-contain #"What is your name*"
        (with-out-str (with-in-str "Robert" (start-game)))))



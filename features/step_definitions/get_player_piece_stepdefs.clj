(use 'speclj.core)
(use 'ttt-clojure.game)

(Given #"^I've been asked for my name" []
       "I've been asked for my name")

(Then #"^I should be asked what piece I want to be" []
      (should-contain #"What piece*"
        (with-out-str (with-in-str "Robert\nX" (start-game)))))

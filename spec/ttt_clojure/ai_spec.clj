(ns ttt-clojure.ai-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.ai :refer :all]))

(describe "bot-can-win?"
  (it "return true if the bot can win the game"
    (should (bot-can-win? "O" ["O" "O" 2
                              "X" "X" 5
                               6   7  8]))))

(describe "three-in-a-row-possible?"
  (it "returns true if it possible to get three of a particual piece in a row"
    (should (three-in-a-row-possible? "O" ["O" "O" 2])))

  (it "returns false if it is not possible to get three in a row"
    (should-not (three-in-a-row-possible? "X" ["X" 1 2])))

  (it "returns false if it is not possible to get three in a row piece"
    (should-not (three-in-a-row-possible? "O" ["O" "X" "O"]))))


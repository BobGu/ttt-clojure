(ns ttt-clojure.negamax-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.negamax :refer :all]))
  (describe "get-move"
    (context "computer player acts second"
      (it "returns the best move for the computer"
        (should= 8
          (get-move "X"   ["X" "X" "O"
                          "O" "X" "O"
                           6  "O"  8]))))

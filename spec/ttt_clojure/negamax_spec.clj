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

  (context "counter opposite corner strategy"
    (it "returns the best move for the computer"
      (let [result (get-move "O" ["X" 1 2 3 "O" 5 6 7 "X"])]
        (some #{result} '(1 3 5 7)))))

  (context "makes two unblocked rows of two when they cant win"
    (it "returns the best move for the computer"
      (let [result (get-move "X" [0  "X" 2
                                 "X" "O" 5
                                  6  "O" 8])]
        (should= 0 result))))

  (context "blocks oppponents best move"
    (it "returns the best move for the computer"
      (let [result (get-move "O" [ 0  "X" 2
                                  "X" "O" 5
                                   6   7  8])]
        (should= 0 result)))

   (it "returns the best move for the computer"
     (let [result (get-move "O" [ 0 "X"  2
                                  3 "O" "X"
                                  6  7   8])]
       (should= 0 result)))))



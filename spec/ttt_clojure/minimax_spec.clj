(ns ttt-clojure.minimax-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.spec-helper :refer [clock-speed find-milliseconds]]
            [ttt-clojure.minimax :refer :all]))

  (describe "better-move"
    (context "computer player acts second"
      (it "returns the best move for the computer"
        (should= 8
          (better-move "X"   ["X" "X" "O"
                            "O" "X" "O"
                             6  "O"  8]))))

  (context "counter opposite corner strategy"
    (it "returns the best move for the computer"
      (let [result (better-move "O" ["X" 1 2 3 "O" 5 6 7 "X"])]
        (some #{result} '(1 3 5 7)))))

  (context "makes two unblocked rows of two when they cant win"
    (it "returns the best move for the computer"
      (let [result (better-move "X" [0  "X" 2
                                  "X" "O" 5
                                   6  "O" 8])]
        (should= 0 result))))

  (context "blocks oppponents best move"
    (it "returns the best move for the computer"
      (let [result (better-move "O" [ 0  "X" 2
                                   "X" "O" 5
                                    6   7  8])]
        (should= 0 result)))

   (it "returns the best move for the computer"
     (let [result (better-move "O" [ 0 "X"  2
                                   3 "O" "X"
                                   6  7   8])]
       (should= 0 result)))

  (it "returns the best move for the computer"
    (let [result (better-move "X" ["X" "O" "X"
                                  3  "O" "O"
                                  6  "X"  8])]
      (should= 3 result))))

  (context "benchmarking"
    (around [it]
      (with-out-str (it)))

    (it "should find the best move quickly"
      (let [best-move-function #(better-move "X" ["O" 1 2 3 4 5 6 7 8])
            milliseconds (find-milliseconds (clock-speed best-move-function))]
        (should= 3000 milliseconds)))))

(describe "minimax"
  (it "returns a score based on the move"
    (should= 10
      (minimax "X" ["X" "O" "X"
                    "O" "O" "X"
                    "X" "X"  8] 8 true 1)))

  (it "returns a score based on the move"
    (should= 50
      (minimax "X" ["X" "X" 2
                    "O" "O" 5
                     6   7  8] 2 true 5)))

  (it "returns negative score if maximizing player is about to lose"
    (should= -40
      (minimax "X" ["X" "X" 2
                    "O" "O" 5
                     6   7  8] 6 true 5))))




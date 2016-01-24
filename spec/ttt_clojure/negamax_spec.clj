(ns ttt-clojure.negamax-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.player :refer :all]
            [ttt-clojure.negamax :refer :all]))

  (describe "best-move"
    (context "computer player acts second"
      (it "returns the best move for the computer"
        (should= 8
          (best-move "X"   ["X" "X" "O"
                           "O" "X" "O"
                            6  "O"  8]))))

  (context "counter opposite corner strategy"
    (it "returns the best move for the computer"
      (let [result (best-move "O" ["X" 1 2 3 "O" 5 6 7 "X"])]
        (some #{result} '(1 3 5 7)))))

  (context "makes two unblocked rows of two when they cant win"
    (it "returns the best move for the computer"
      (let [result (best-move "X" [0  "X" 2
                                 "X" "O" 5
                                  6  "O" 8])]
        (should= 0 result))))

  (context "blocks oppponents best move"
    (it "returns the best move for the computer"
      (let [result (best-move "O" [ 0  "X" 2
                                  "X" "O" 5
                                   6   7  8])]
        (should= 0 result)))

   (it "returns the best move for the computer"
     (let [result (best-move "O" [ 0 "X"  2
                                  3 "O" "X"
                                  6  7   8])]
       (should= 0 result)))

  (it "returns the best move for the computer"
    (let [result (best-move "X" ["X" "O" "X"
                                 3  "O" "O"
                                 6  "X"  8])]
      (should= 3 result)))))

  (describe "get-name"
    (it "returns a name"
      (let [computer (new-computer "X")]
      (should= "Johnny-5" (.get-name computer)))))

  (describe "get-piece"
    (it "returns a piece"
      (let [computer (new-computer "O")]
        (should= "O" (.get-piece computer)))))

  (describe "get-move"
    (around [it]
      (with-out-str (it))))

    (it "returns a move"
      (should= 3
        (let [board ["X" "O" "X"
                      3 "O" "O"
                      6 "X" 8]
              computer (new-computer "X")]
        (.get-move computer "Where would you like to move Johnny-5" board)))))

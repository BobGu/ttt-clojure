(ns ttt-clojure.ai-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.ai :refer :all]))

(describe "can-win?"
  (it "return true if a certain piece can win the game"
    (should (can-win? "O" ["O" "O" 2
                              "X" "X" 5
                               6   7  8])))

  (it "returns false if a certain piece can not win the game"
    (should-not (can-win? "O" ["O" "O" "X"
                               "X" "X" "5"
                               "O" "O" "X"]))))

(describe "three-in-a-row-possible?"
  (it "returns true if it possible to get three of a particual piece in a row"
    (should (three-in-a-row-possible? "O" ["O" "O" 2])))

  (it "returns false if it is not possible to get three in a row"
    (should-not (three-in-a-row-possible? "X" ["X" 1 2])))

  (it "returns false if it is not possible to get three in a row piece"
    (should-not (three-in-a-row-possible? "O" ["O" "X" "O"]))))

(describe "find-winning-set"
  (it "returns the winning set"
    (should= ["O" "O" 2]
      (find-winning-set "O" ["O" "O" 2
                             "X" "X" 5
                             "O" "X" 8])))

  (it "returns the winning set"
    (should= ["X" "X" 8]
      (find-winning-set "X" ["X"  1 "O"
                             "O" "X" 5
                             "O"  7  8]))))



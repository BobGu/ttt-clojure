(ns ttt-clojure.dumb-bot-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.dumb-bot :refer :all]))

(describe "three-in-a-row-possible?"
  (it "reutrns true if three in a row possible"
    (should (three-in-a-row-possible? "X" ["X" "X" 2])))

  (it "returns false if three in a row is not possible"
    (should-not (three-in-a-row-possible? "X" ["X" "X" "O"])))

  (it "returns false if three in a row is not possibe"
    (should-not (three-in-a-row-possible? "X" ["X" 1 2]))))

(describe "two-pieces-in-row?"
  (it "returns true if two piece in a row?"
    (should (two-pieces-in-a-row? "X" ["X" "X" 2]))))

(describe "one-open-space?"
  (it "returns true if one open space"
    (should (one-open-space? [0 "X" "X"])))

  (it "returns false if no open spaces"
    (should-not (one-open-space? ["X" "X" "X"])))

  (it "returns true if one open space"
    (should (one-open-space? ["X" 1 2]))))

(describe "can-win?"
  (it "returns true if the piece can win"
    (should (can-win? "X" ["X" "X" 2 3 4 5 6 7 8])))

  (it "return false if it can not win"
    (should-not (can-win? "X" ["O" "O" 2 3 4 5 6 7 8]))))

(describe "find-winning-set"
  (it "returns the winning set"
   (should= ["X" "X" 2]
      (find-winning-set "X" ["X" "X" 2 3 4 5 6 7 8])))

  (it "returns the winning set"
    (find-winning-set "X" ["X" 1 2 3 "X" 5 6 7 8])))

(describe "find-winning-move"
  (it "returns the winning move from a board"
    (should= 2 (find-winning-move "X" ["X" "X" 2 3 4 5 6 7 8])))

  (it "returns the winning move from a board"
    (should= 5 (find-winning-move "O" [0 1 2 "O" "O" 5 6 7 8])))

  (it "returns the winning move from a board"
    (should= 8 (find-winning-move "X" ["X" 1 2 3 "X" 5 6 7 8]))))

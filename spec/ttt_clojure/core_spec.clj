(ns ttt-clojure.core-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.core :refer :all]))

(describe "empty board"
  (it "creates an empty 3x3 board"
    (should= [0 1 2 3 4 5 6 7 8]
      empty-board)))

(describe "update-board"
  (it "returns correctly updated board given an X and position 0"
    (should= ["X" 1 2 3 4 5 6 7 8]
    (update-board [0 1 2 3 4 5 6 7 8] "X" 0)))

  (it "returns correctly updated board given an O and posiion 8"
    (should= [0 1 2 3 4 5 6 7 "O"]
    (update-board [0 1 2 3 4 5 6 7 "O"] "O" 8))))

(describe "space-available?"
  (it "should return false if space is not available"
  (should-not (space-available? ["X" 1 2 3 4 5 6 7 8] 0)))

  (it "should return true if space is available"
  (should (space-available? ["X" "X" 2 3 4 5 "O" "O" 8] 2))))

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

(describe "game-won?"
  (it "should return true three of same pieces in top row"
  (should (game-won? ["X", "X", "X", 3 4 5 6 7 8])))

  (it "should return false if two of same pieces in a row"
  (should-not (game-won? ["X" "X" 2 3 4 5 6 7 8])))

  (it "should return true if three of same piece in middle row"
  (should (game-won? [0 1 2 "X" "X" "X" 6 7 8])))

  (it "should return true if three of same piece in bottom row"
  (should (game-won? [0 1 2 3 4 5 "X" "X" "X"]))))

(describe "rows"
  (it "should return [0 1 2 3 4 5 6 7 8]"
  (should= [[0 1 2] [3 4 5] [6 7 8]] (rows [0 1 2 3 4 5 6 7 8])))

  (it "should return [[\"X\" \"X\" \"X\"] [3 4 5] [6 7 8]"
  (should= [["X" "X" "X"] [3 4 5] [6 7 8]] (rows ["X" "X" "X" 3 4 5 6 7 8])))

  (it "should return [[\"O\" 1 2] [3 \"O\" 5] [6 7 8]]"
  (should= [["O" 1 2] [3 "O" 5] [6 7 8]] (rows ["O" 1 2 3 "O" 5 6 7 8]))))

(describe "three-in-a-row?"
  (it "should return true given [\"X\" \"X\" \"X\"]"
  (should (three-in-a-row? ["X" "X" "X"])))

  (it "should return true given [\"O\" \"O\" \"O\"]"
  (should (three-in-a-row? ["O" "O" "O"])))

  (it "should return false given [\"O\" 1 \"O\"]"
  (should-not (three-in-a-row? ["O" 1 "O"])))

  (it "should return false given [\"X\" \"O\" \"X\"]"
  (should-not (three-in-a-row? ["X" "O" "X"]))))

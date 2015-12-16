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
  (it "should return true if all pieces in the top row are the same"
  (should (game-won? ["X", "X", "X", 3 4 5 6 7 8])))

  (it "should return true if all pieces in the middle row are the same"
  (should (game-won? [0 1 2 "X" "X" "X" 6 7 8])))

  (it "should return true if all pieces in bottom row are the same"
  (should (game-won? [0 1 2 3 4 5 "X" "X" "X"])))

  (it "should return true if all pieces in left column are the same"
  (should (game-won? ["X" 1 2 "X" 4 5 "X" 7 8])))

  (it "should return true if all pieces in middle column are the same"
  (should (game-won? [0 "X" 2 3 "X" 5 6 "X" 8])))

  (it "should return true if all pieces in right column are the same"
  (should (game-won? [0 1 "X" 3 4 "X" 6 7 "X"]))))

(describe "rows"
  (it "should return the rows given an empty board"
  (should= [[0 1 2] [3 4 5] [6 7 8]] (rows [0 1 2 3 4 5 6 7 8])))

  (it "should return correct rows given a board with some spaces taken"
  (should= [["X" "X" "X"] [3 4 5] [6 7 8]] (rows ["X" "X" "X" 3 4 5 6 7 8])))

  (it "should return correct rows given a board with some spaces taken"
  (should= [["O" 1 2] [3 "O" 5] [6 7 8]] (rows ["O" 1 2 3 "O" 5 6 7 8]))))

(describe "columns"
  (it "should return the columns given an empty board"
  (should= [[0 3 6] [1 4 7] [2 5 8]] (columns [0 1 2 3 4 5 6 7 8]))))

(describe "possible-wins"
  (it "should return the rows and columns from a board"
  (should= [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8]]
  (possible-wins [0 1 2 3 4 5 6 7 8]))))

(describe "three-in-a-row?"
  (it "should return true given three of same piece in a row"
  (should (three-in-a-row? ["X" "X" "X"])
  (should (three-in-a-row? ["O" "O" "O"]))))

  (it "should return false given an available space and the rest the same piece"
  (should-not (three-in-a-row? ["O" 1 "O"])))

  (it "should return false given mostly one piece and one of the opposing piece"
  (should-not (three-in-a-row? ["X" "O" "X"]))))

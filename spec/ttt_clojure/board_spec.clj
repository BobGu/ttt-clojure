(ns ttt-clojure.board-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.board :refer :all]))

(describe "empty board"
  (it "creates an empty 3x3 board"
    (should= [0 1 2 3 4 5 6 7 8]
      empty-board)))

(describe "update-board"
  (it "returns correctly updated board given an X and position 0"
    (should= ["X" 1 2 3 4 5 6 7 8]
    (update-board empty-board "X" 0)))

  (it "returns correctly updated board given an O and posiion 8"
    (should= [0 1 2 3 4 5 6 7 "O"]
    (update-board [0 1 2 3 4 5 6 7 "O"] "O" 8))))

(describe "space-available?"
  (it "should return false if space is not available"
  (should-not (space-available? ["X" 1 2 3 4 5 6 7 8] 0)))

  (it "should return true if space is available"
  (should (space-available? ["X" "X" 2 3 4 5 "O" "O" 8] 2))))


(describe "rows"
  (it "should return the rows given an empty board"
  (should= [[0 1 2] [3 4 5] [6 7 8]] (rows empty-board)))

  (it "should return correct rows given a board with some spaces taken"
  (should= [["X" "X" "X"] [3 4 5] [6 7 8]] (rows ["X" "X" "X" 3 4 5 6 7 8])))

  (it "should return correct rows given a board with some spaces taken"
  (should= [["O" 1 2] [3 "O" 5] [6 7 8]] (rows ["O" 1 2 3 "O" 5 6 7 8]))))

(describe "columns"
  (it "should return the columns given an empty board"
  (should= [[0 3 6] [1 4 7] [2 5 8]] (columns empty-board))))

(describe "diagonals"
  (it "should return the diagonals given and empty board"
  (let [diagonals (diagonals empty-board)]
  (should (every? (fn [x] (some #(= x %) diagonals)) ['(2 4 6) '(0 4 8)])))))

(describe "possible-wins"
  (it "should return the rows and columns and digaonals from a board"
  (let [expected-results [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6] [0 4 8] [2 4 6]]
   possible-wins (possible-wins empty-board)]
   (should (every? (fn [x] (some #(= x %) possible-wins)) expected-results)))))

(describe "all spaces the same?"
  (it "returns true if all spaces are the same"
  (should (all-spaces-the-same? ["X" "X" "X"]))
  (should (all-spaces-the-same? ["O" "O" "O"])))

  (it "should return false given an available space and the rest the same piece"
  (should-not (all-spaces-the-same? ["O" 1 "O"])))

  (it "should return false given mostly one piece and one of the opposing piece"
  (should-not (all-spaces-the-same? ["X" "O" "X"]))))

(ns ttt-clojure.rules-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.rules :refer :all]))

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
  (should (game-won? [0 1 "X" 3 4 "X" 6 7 "X"])))

  (it "should return true if all pieces in a diagonal from left to right are the same"
  (should (game-won? ["X" 1 2 3 "X" 5 6 7 "X"])))

  (it "should return true if all pieces in a diagonal from right to left are the same"
  (should (game-won? [0 1 "X" 3 "X" 5 "X" 7 8]))))

(describe "game-tied?"
  (it "returns true if the game is tied and there are no empty spaces left"
   (should (game-tied? ["X" "O" "X"
                        "O" "O" "X"
                        "X" "X" "O"])))

  (it "returns false if the game is not tied and there are no empty spaces left"
    (should-not (game-tied? ["X" "X" "X"
                             "O" "O" "X"
                             "O" "X" "O"])))

  (it "returns false if the game is not won and all the spaces are not filled"
    (should-not (game-tied? ["X" "O" "X"
                             "O" "X" "O"
                             "X" "O" 8]))))

(describe "game-over?"
  (it "returns true when game is won"
    (should (game-over? ["X" "X" "X" 3 4 5 6 7 8])))

  (it "returns false when game is not won and there are empty spaces on the board"
    (should-not (game-over? ["X" "X" 2 "O" "O" 5 6 7 8])))

  (it "returns true when game it tied"
    (should (game-over? ["X" "X" "O"
                         "O" "O" "X"
                         "X" "O" "X"]))))


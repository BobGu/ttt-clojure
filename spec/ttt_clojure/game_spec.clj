(ns ttt-clojure.game-spec
  (:require [speclj.core :refer :all]
    [ttt-clojure.game :refer :all]
    [ttt-clojure.board :refer :all]
    [ttt-clojure.message-factory :refer :all]
    [ttt-clojure.validate-input :refer :all]))

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


(describe "get-player-input"
  (around [it]
    (with-out-str (it)))

  (it "should return a players players piece if valid"
    (should= "X"
      (with-in-str "X" 
        (get-player-input ask-player-for-piece valid-piece? invalid-piece))))

  (it "should return an error message if a piece is invalid"
    (should-contain #"valid piece*"
      (with-out-str
        (with-in-str "pooop\nX"
          (get-player-input ask-player-for-piece valid-piece? invalid-piece)))))

  (it "should return a players name when it is valid"
    (should= "Robert"
      (with-in-str "Robert"
        (get-player-input ask-player-for-name valid-name? invalid-name))))

  (it "should not return a players name if it is blank"
    (should-not= ""
      (with-in-str "\nRobert"
        (get-player-input ask-player-for-name valid-name? invalid-name))))

  (it "should output an error message if a players name is invalid"
    (should-contain #"is not a valid name*"
      (with-out-str
      (with-in-str "\nRobert"
      (get-player-input ask-player-for-name valid-name? invalid-name))))))

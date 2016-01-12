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
        (get-player-input ask-player-for-piece valid-piece?  ))))

  (it "should return an error message if a piece is invalid"
    (should-contain #"is not a valid input"
      (with-out-str
        (with-in-str "pooop\nX"
          (get-player-input ask-player-for-piece valid-piece?)))))

 (it "should return a players name when it is valid"
    (should= "Robert"
      (with-in-str "Robert"
        (get-player-input ask-player-for-name valid-name?))))

  (it "should not return a players name if it is blank"
    (should-not= ""
      (with-in-str "\nRobert"
        (get-player-input ask-player-for-name valid-name?))))

  (it "should output an error message if a players name is invalid"
    (should-contain #"is not a valid input*"
      (with-out-str
      (with-in-str "\nRobert"
      (get-player-input ask-player-for-name valid-name?)))))

  (it "should return the turn order if it is valid"
    (should= "1"
      (with-in-str "1"
        (get-player-input ask-player-for-turn-order valid-turn-order?))))

  (it "should return the player move if it is valid"
    (should= "4"
      (with-in-str "4"
        (let [message (ask-player-for-move "Robert")]
        (get-player-input message (validate-move [0 1 2 3 4 5 6 7 8])))))))

(describe "validate-move"
  (around [it]
    (with-out-str (it)))

  (it "should return true if the space is available"
    (should
      (let [valid-move (validate-move [0 1 2 3 4 5 6 7 8])]
      valid-move 4))))

(describe "assign-turn-order"
  (it "should reutrn the players info in the correct order"
    (should= [{:name "Robert" :piece "X"} {:name "John" :piece "O"}]
      (assign-turn-order "1" [{:name "Robert" :piece "X"} {:name "John" :piece "O"}])))

  (it "should return the players info in the correct order"
    (should= [{:name "Robert" :piece "X"} {:name "John" :piece "O"}]
      (assign-turn-order "2" [{:name "John" :piece "O"} {:name "Robert" :piece "X"}]))))

(describe "opposite-piece"
  (it "returns the opposite piece"
    (should= "O"
      (opposite-piece "X"))))

(describe "moves"
  (around [it]
    (with-out-str (it)))

  (it "returns the winner of the game if game is won"
    (should-contain #"Turtle"
      (with-in-str "2\n"
        (moves
          ["X" "X" 2 "O" "O" 5 6 7 8]
          [{:name "Turtle" :piece "X"} {:name "Hare" :piece "O"}])))))

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

(describe "get-player-one-info"
  (around [it]
    (with-out-str (it)))

  (it "returns a players name and piece"
    (should= {:name "Jimmy" :piece "O"}
      (with-in-str "Jimmy\no\n" (get-player-one-info)))))

(describe "players-info"
  (around [it]
    (with-out-str (it)))

  (it "returns both players information"
    (should= [{:name "Robert" :piece "O"} {:name "Zach" :piece "X"}]
      (with-in-str "Zach"
        (players-info {:name "Robert" :piece "O"})))))

(describe "game-over?"
  (it "returns true when game is won"
    (should (game-over? ["X" "X" "X" 3 4 5 6 7 8])))

  (it "returns false when game is not won and there are empty spaces on the board"
    (should-not (game-over? ["X" "X" 2 "O" "O" 5 6 7 8])))

  (it "returns true when game it tied"
    (should (game-over? ["X" "X" "O"
                         "O" "O" "X"
                         "X" "O" "X"]))))


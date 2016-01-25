(ns ttt-clojure.game-spec
  (:require [speclj.core :refer :all]
    [ttt-clojure.game :refer :all]
    [ttt-clojure.board :refer :all]
    [ttt-clojure.message-factory :refer :all]
    [ttt-clojure.validate-input :refer :all]
    [ttt-clojure.human :refer :all]))

(describe "assign-turn-order"
  (it "should reutrn the players info in the correct order"
    (should= [{:name "Robert" :piece "X"} {:name "John" :piece "O"}]
      (assign-turn-order "1" [{:name "Robert" :piece "X"} {:name "John" :piece "O"}])))

  (it "should return the players info in the correct order"
    (should= [{:name "Robert" :piece "X"} {:name "John" :piece "O"}]
      (assign-turn-order "2" [{:name "John" :piece "O"} {:name "Robert" :piece "X"}]))))

(describe "moves"
  (around [it]
    (with-out-str (it)))

  (it "returns the winner of the game if game is won"
    (should-contain #"Turtle"
      (with-in-str "2\n"
        (moves
          ["X" "X" 2 "O" "O" 5 6 7 8]
          [(new-human "Turtle" "X") (new-human "Hare" "O")])))))


(ns ttt-clojure.ai-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.ai :refer :all]))

(describe "winning-set"
  (it "returns the winning set"
    (should= ["O" "O" 2]
      (winning-set "O" ["O" "O" 2
                             "X" "X" 5
                             "O" "X" 8])))

  (it "returns the winning set"
    (should= ["X" "X" 8]
      (winning-set "X" ["X"  1 "O"
                             "O" "X" 5
                             "O"  7  8]))))

(describe "winning-move"
  (it "returns the winning-move"
    (should= 8
      (winning-move "X" ["X"  1 "O"
                         "O" "X" 5
                         "O"  7  8])))

  (it "returns the winning move"
    (should= 0
      (winning-move "O" [0  "O" "O"
                         3  "X" "X"
                         6   7   8]))))

(describe "three-in-a-row-possible?"
  (it "returns true if it possible to get three of a particual piece in a row"
    (should (three-in-a-row-possible? "O" ["O" "O" 2])))

  (it "returns false if it is not possible to get three in a row"
    (should-not (three-in-a-row-possible? "X" ["X" 1 2])))

  (it "returns false if it is not possible to get three in a row piece"
    (should-not (three-in-a-row-possible? "O" ["O" "X" "O"]))))

(describe "won?"
  (it "returns true if that piece has won the game"
    (should (won? "X" ["X" "X" "X" "O" "O" 5 6 7 8])))

  (it "returns false if no one has won"
    (should-not (won? "X" [0 1 2 3 4 5 6 7 8])))

  (it "returns false if the opposite person has won"
    (should-not (won? "X" ["O" "O" "O" "X" "X" 5 6 7 8]))))



(describe "score-a-board"
  (it "returns a postive value if the game is won"
    (should= 1
      (score-a-board 1  "X" ["X" "X" "X"
                             "O" "O" "X"
                             "X" "O" "O"])))

  (it "returns zero if the game is a tie"
    (should= 0
      (score-a-board  1 "X" ["X"  "O" "X"
                             "O"  "O" "X"
                             "O"  "X" "O"])))

  (it "returns a negative value if the opposition wins"
    (should= -1
      (score-a-board 1 "O" ["X" "X" "X"
                            "O" "O" "X"
                            "X" "O" "O"])))
  (it "returns a large score"
     (should= 5
       (score-a-board 5 "O" ["O" "O" "O"
                             "X" "X"  5
                              6   7   8]))))

(describe "scores"
  (context "end game scenarios with one move left for the computer player "
    (it "it chooses the best move"
      (should= [1]
        (scores "X" ["X" "X"  2
                      "O" "O" "X"
                      "X" "O" "O"] "X")))

    (it "returns a move and zero score if it is the only move"
      (should= [0]
        (scores "O" ["O" "O" "X"
                      "X" "X"  5
                      "O" "O" "X"] "O")))

    (it "returns a neagtive score if game is won by opponent"
      (should= [0]
        (scores "X" ["O" "O" "X"
                      "X" "O" "O"
                      "O" "X"  8] "X"))))

  (context "end game scenarios with two moves left and the ai acts next"
    (it "returns a higher score and move if game can be won earlier"
      (should= [2 -1]
        (scores "X"  ["X" "X"  2
                      "O" "O" "X"
                      "O" "O"  8] "X")))

    (it "returns the best move and its score"
      (should= [0 2]
        (scores "X" ["X" "O"  2
                      "O" "X" "O"
                      "O" "X"  8] "X")))))

(describe "future-boards"
  (it "returns all boards where player moves into every available space"
    (should= [["X" "O" "X"
               "O" "X" "O"
               "O" "X"  8]
              ["X" "O"  2
               "O" "X" "O"
               "O" "X" "X"]]
      (future-boards "X" ["X" "O"  2
                          "O" "X" "O"
                          "O" "X"  8])))

  (it "returns all the future boards"
    (should= [["X" "O" "X" "O" "X" "O" "X" 7 8]
              ["X" "O" "X" "O" "X" "O" 6 "X" 8]
              ["X" "O" "X" "O" "X" "O" 6 7 "X"]]
     (future-boards "X" ["X" "O" "X"
                         "O" "X" "O"
                          6   7   8]))))


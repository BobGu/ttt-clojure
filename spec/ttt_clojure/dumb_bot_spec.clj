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

(ns ttt-clojure.validate-input-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.validate-input :refer :all]))

(describe "valid-name?"
  (it "should return false if nothing is entered"
    (should-not (valid-name? "")))

  (it "should return false if name is too long"
    (should-not (valid-name? "abcdefghijklmnopqrstuvwxyz")))

  (it "should return true if name is of a valid length"
    (should (valid-name? "Robert Gu"))))

(describe "valid-piece?"
  (it "should return true if it is a valid piece"
    (should (valid-piece? "X")))

  (it "should return true if it is a valid piece"
    (should (valid-piece? "O")))

  (it "should return true regardless of case"
    (should (valid-piece? "x")))

  (it "should return true regardless of whitespace"
    (should (valid-piece? "O ")))

  (it "should return false if piece is not valid"
    (should-not (valid-piece? "poop"))))

(describe "valid-turn-order?"
  (it "should return true if it is a valid turn order"
    (should (valid-turn-order? "1")))

  (it "should return false if turn order is invalid"
    (should-not (valid-turn-order? "19000000")))

  (it "should return true if turn order is valid"
    (should (valid-turn-order? "2"))))

(describe "valid-move?"
  (it "should return true if that space is available"
    (should (valid-move? "2" [0 1 2 3 4 5 6 7 8])))

  (it "should return false if that space is not available"
    (should-not (valid-move? "9" [0 1 2 3 4 5 6 7 8])))

  (it "should return false if that space is already occupied by a piece"
    (should-not (valid-move? "0" ["X" 1 2 3 4 5 6 7 8]))))

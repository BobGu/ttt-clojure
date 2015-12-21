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

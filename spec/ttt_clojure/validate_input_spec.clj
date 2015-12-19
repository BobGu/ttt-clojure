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

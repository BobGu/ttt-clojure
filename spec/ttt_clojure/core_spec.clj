(ns ttt-clojure.core-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.core :refer :all]))

(describe "empty board"
  (it "creates an empty 3x3 board"
    (should= [
        [0 1 2]
        [3 4 5]
        [6 7 8]
      ]
      empty-board)))

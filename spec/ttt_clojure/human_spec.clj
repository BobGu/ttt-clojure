(ns ttt-clojure.human-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.player :refer :all]
            [ttt-clojure.human :refer :all]))

(describe "fetch-player-move"
  (around [it]
    (with-out-str (it)))

  (it "should return the move for a human player"
    (should= "2"
      (with-in-str "2"
        (fetch-player-move human "rob" [0 1 2 3 4 5 6 7 8])))))

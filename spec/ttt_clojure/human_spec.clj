(ns ttt-clojure.human-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.player :refer :all]
            [ttt-clojure.human :refer :all]))

(describe "get-move"
  (around [it]
    (with-out-str (it)))

  (it "should return the move for a human player"
    (should= 2
      (with-in-str "2"
        (let [player (new-human "rob" "X")
              input #(fn [] (read-line))
              output #(fn [string] (println string))]
          (get-move player "Bob" [0 1 2 3 4 5 6 7 8] input output))))))


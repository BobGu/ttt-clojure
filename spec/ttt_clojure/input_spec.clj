(ns ttt-clojure.input-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.input :refer :all]))

(describe "prompt"
  (around [it]
    (with-out-str (it)))

  (it "tests the input of prompt"
    (should= "8"
      (with-in-str "8"
      (prompt "Where would you like to move?"))))

  (it "tests the output of prompt"
    (should= "Where would you like to move?\n"
    (with-out-str (with-in-str "4"
    (prompt "Where would you like to move?"))))))

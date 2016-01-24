(ns ttt-clojure.computer-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.computer :refer :all]))

(describe "get-name"
  (it "returns a name"
    (let [computer (new-computer "X")]
    (should= "Johnny-5" (.get-name computer)))))

(describe "get-piece"
  (it "returns a piece"
    (let [computer (new-computer "O")]
      (should= "O" (.get-piece computer)))))

(describe "get-move"
  (around [it]
    (with-out-str (it))))

  (it "returns a move"
    (should= 3
      (let [board ["X" "O" "X"
                    3 "O" "O"
                    6 "X" 8]
            computer (new-computer "X")]
      (.get-move computer "Where would you like to move Johnny-5" board))))

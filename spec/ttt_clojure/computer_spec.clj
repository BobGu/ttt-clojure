(ns ttt-clojure.computer-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.computer :refer :all]
            [ttt-clojure.game :refer :only [negamax-strategy]))

(describe "get-name"
  (it "returns a name"
    (let [computer (new-computer "X" "a stupid strategy")]
    (should= "Johnny-5" (.get-name computer)))))

(describe "get-piece"
  (it "returns a piece"
    (let [computer (new-computer "O" "a fun strategy")]
      (should= "O" (.get-piece computer)))))

(describe "get-strategy"
  (it "returns a strategy"
    (let [computer (new-computer "O" "A silly strategy")]
      (should= "A silly strategy" (.get-strategy computer)))))

(describe "get-move"
  (around [it]
    (with-out-str (it)))

  (it "returns a move"
    (let [board ["X" "O" "X"
                  3 "O" "O"
                  6 "X" 8]
          strategy (negamax-strategy)
          computer (new-computer "X" strategy)]
    (should= 3
      (.get-move computer "Where would you like to move Johnny-5" board)))))

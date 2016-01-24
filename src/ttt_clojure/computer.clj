(ns ttt-clojure.computer
  (:require [ttt-clojure.negamax :refer :all]
            [ttt-clojure.player :refer :all]))

(deftype Computer [piece]
  Player
  (get-name [this] "Johnny-5")
  (get-piece [this] piece)
  (get-move [this message board]
    (print message)
    (best-move (.get-piece this) board)))

(defn new-computer [piece] (Computer. piece))


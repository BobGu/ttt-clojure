(ns ttt-clojure.computer
  (:require [ttt-clojure.negamax :refer :all]
            [ttt-clojure.player :refer :all]))

(deftype Computer [piece strategy]
  Player
  (get-name [this] "Johnny-5")
  (get-piece [this] piece)
  (get-strategy [this] strategy)
  (get-move [this message board input output]
    ((output)message)
    ((.get-strategy this) (.get-piece this) board)))

(defn new-computer [piece strategy] (Computer. piece strategy))


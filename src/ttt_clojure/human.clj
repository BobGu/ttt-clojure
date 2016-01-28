(ns ttt-clojure.human
  (:require [ttt-clojure.player :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.input :refer :all]))


(deftype Human [name piece]
  Player
  (get-name [this] name)
  (get-piece [this] piece)
  (get-move [this message board input output]
    (get-player-move message board input output)))

(defn new-human [name piece]
  (Human. name piece))


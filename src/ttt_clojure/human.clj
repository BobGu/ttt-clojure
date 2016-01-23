(ns ttt-clojure.human
  (:require [ttt-clojure.player :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.input :refer :all]))


(deftype Human [name piece]
  Player
  (get-name [this] name)
  (get-piece [this] piece)
  (get-move [this name board]
    (get-player-move name board)))

(defn new-human [name piece]
  (Human. name piece))


(ns ttt-clojure.human
  (:require [ttt-clojure.player :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.input :refer :all]))

(defn get-player-input [message validator]
  (let [input (prompt message)]
    (if (validator input)
     input
     (do
       (print (invalid-input input))
       (get-player-input message validator)))))

(defn validate-move [board]
  (fn [move]
    (valid-move? move board)))

(deftype Human [name piece]
  Player
  (get-name [this] name)
  (get-piece [this] piece)
  (get-move [this message board]
    (get-player-input message (validate-move board))))

(defn new-human [name piece]
  (Human. name piece))


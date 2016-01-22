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

(deftype Human []
  Player
  (fetch-player-name [this] (get-player-input ask-player-for-name valid-name?))
  (fetch-player-piece [this]
    (clojure.string/upper-case
      (get-player-input ask-player-for-piece valid-piece?)))
  (fetch-player-move [this message board]
    (get-player-input message (validate-move board))))

(defn new-human []
  (Human.))

(def human (new-human))

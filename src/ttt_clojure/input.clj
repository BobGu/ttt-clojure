(ns ttt-clojure.input
  (:require [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.message-factory :refer :all]))

(defn prompt [message]
  (println message)
  (read-line))

(defn get-player-input [message validator]
  (let [input (prompt message)]
    (if (validator input)
     input
     (do
       (print (invalid-input input))
       (get-player-input message validator)))))

(defn get-player-name []
  (get-player-input ask-player-for-name valid-name?))

(defn get-player-piece []
  (clojure.string/upper-case (get-player-input ask-player-for-piece valid-piece?)))

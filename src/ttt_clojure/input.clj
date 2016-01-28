(ns ttt-clojure.input
  (:require [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.message-factory :refer :all]))

(defn prompt [message input output]
  ((output) message)
  ((input)))

(defn get-player-input [message validator input output]
  (let [player-input (prompt message input output)]
    (if (validator player-input)
     player-input
     (do
       (print (invalid-input player-input))
       (get-player-input message validator input output)))))

(defn get-player-name [input output]
  (get-player-input ask-player-for-name valid-name? input output))

(defn get-player-piece [input output]
  (clojure.string/upper-case (get-player-input ask-player-for-piece valid-piece? input output)))

(defn get-player-move [name board input output]
  (read-string (get-player-input (ask-player-for-move name) (validate-move board) input output)))

(defn get-game-mode [input output]
  (clojure.string/upper-case (get-player-input game-mode valid-game-mode? input output)))

(defn get-turn-order [message input output]
  (get-player-input message valid-turn-order? input output))

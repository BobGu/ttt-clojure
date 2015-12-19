(ns ttt-clojure.message-factory)

(defn ask-player-for-move [name]
  (str "Where would you like to move " name "?"))

(def ask-player-for-name
  "What is your name?")

(def ask-player-for-piece
  "What piece would you like to be?  Please type in X or O")

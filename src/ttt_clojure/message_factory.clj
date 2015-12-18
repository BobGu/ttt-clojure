(ns ttt-clojure.message-factory)

(defn ask-player-for-move [name]
  (str "Where would you like to move " name "?"))

(def ask-player-for-name
  "What is your name?")

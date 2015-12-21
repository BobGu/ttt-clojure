(ns ttt-clojure.message-factory)

(defn ask-player-for-move [name]
  (str "Where would you like to move " name "?"))

(def ask-player-for-name
  "What is your name?")

(def ask-player-for-piece
  "What piece would you like to be?  Please type in X or O")

(defn board-formatter [board]
  (str "     |     |     |\n "
  (first board)"   |  "(nth board 1)"  |  "(nth board 2)"  |    \n"
  "_____|_____|_____|\n"
  "     |     |     |\n "
  (nth board 3)"   |  "(nth board 4)"  |  "(nth board 5)"  |    \n"
  "_____|_____|_____|\n"
  "     |     |     |\n "
  (nth board 6)"   |  "(nth board 7)"  |  "(nth board 8)"  |    \n"
  "_____|_____|_____|\n"))

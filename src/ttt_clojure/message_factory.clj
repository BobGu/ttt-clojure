(ns ttt-clojure.message-factory)

(defn ask-player-for-move [name]
  (str "Where would you like to move " name "?"))

(def ask-player-for-name
  "What is your name?")

(def ask-player-for-piece
  "What piece would you like to be?  Please type in X or O")

(defn ask-player-for-turn-order [name]
  (str "Enter 1 if you would like " name " to go first and enter 2 if you would
    like " name " to go second"))

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

(def instructions
  (let [board [0 1 2 3 "X" 5 6 7 8]]
  (str "Welcome to tic tac toe!  When it is your turn choose a number from 0-8 as repersented on the board.
 For example if I were the letter X and i chose the center space by typing in the number 4 then
  the board would look like this.\n"
  (board-formatter board))))

(defn invalid-input [input]
  (str input " is not a valid input" ))

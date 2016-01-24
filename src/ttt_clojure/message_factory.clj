(ns ttt-clojure.message-factory
  (:require [ttt-clojure.color :refer :all]))

(defn ask-player-for-move [name]
  (green (str "Where would you like to move " name "?")))

(def ask-player-for-name
  (yellow "What is your name?"))

(def ask-player-for-piece
  (yellow "What piece would you like to be?  Please type in X or O"))

(defn ask-player-for-turn-order [name]
  (yellow (str "Enter 1 if you would like " name  " to go first and enter 2 if you would
    like " name  " to go second")))

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

(def welcome
  (green "Welcome to tic tac toe!  When it is your turn choose a number from 0-8 as repersented on the board.
  For example if I were the letter X and i chose the center space by typing in the number 4 then
  the board would look like this.\n"))

(def instructions
  (let [board [0 1 2 3 "X" 5 6 7 8]]
  (str welcome
  (board-formatter board))))

(defn invalid-input [input]
  (red (str input " is not a valid input\n" )))

(defn winner-message [name]
  (green (str "Congratulations " name " you have won the game!!")))

(def tie-message
  (yellow "The game is a tie.  A strange game.  The only winning move is not to play."))

(defn player-confirmation [name piece]
  (str "Welcome " name " you are the " piece "s"))

(def game-mode
  (yellow (str "Welcome to Tic Tac Toe!  Please choose a game mode.  Type in hh
                to player human vs human or hc to play human vs the comoputer")))

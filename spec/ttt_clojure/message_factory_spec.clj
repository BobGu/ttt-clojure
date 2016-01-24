(ns ttt-clojure.message-factory-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.message-factory :refer :all]))

(describe "ask-player-for-move"
  (it "asks player for move given a name"
    (should-contain #"Where would you like to move Robert?"
    (ask-player-for-move "Robert"))))

(describe "ask-player-for-name"
  (it "should ask player for thier name"
  (should-contain #"What is your name?" ask-player-for-name)))

(describe "ask-player-for-turn-order"
  (it "should ask user if a player should go first or second"
    (should-contain #"you would like John*"
      (ask-player-for-turn-order "John"))))

(describe "ask-player-for-piece"
  (it "should ask player what piece they would like to be"
  (should-contain #"What piece would you like to be" ask-player-for-piece)))

(describe "instructions"
  (it "should give a list of game instructions and an example board"
  (should-contain #"Welcome to tic tac toe!*"
  instructions)))

(describe "invalid-input"
  (it "gives a generic message about input being invalid"
    (should-contain #"superdooperinvalidmessage is not a valid input*" (invalid-input "superdooperinvalidmessage"))))

(describe "winner-message"
  (it "tells a player that they've won the game"
    (should-contain #"Congratulations Robert you have won the game*"
      (winner-message "Robert"))))

(describe "tie-message"
  (it "tells players that the game has ended in a tie"
    (should-contain #"The game is a tie*"
      tie-message)))

(describe "player-confirmation"
  (it "confirms a players name and their piece"
    (should-contain #"Welcome Robert you are the Xs*"
      (player-confirmation "Robert" "X"))))

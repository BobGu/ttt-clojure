(ns ttt-clojure.message-factory-spec
  (:require [speclj.core :refer :all]
            [ttt-clojure.message-factory :refer :all]))

(describe "ask-player-for-move"
  (it "asks player for move given a name"
    (should= "Where would you like to move Robert?"
    (ask-player-for-move "Robert"))))

(describe "ask-player-for-name"
  (it "should ask player for thier name"
  (should= "What is your name?" ask-player-for-name)))

(describe "ask-player-for-piece"
  (it "should ask player what piece they would like to be"
  (should= "What piece would you like to be?  Please type in X or O" ask-player-for-piece)))

(describe "instructions"
  (it "should give a list of game instructions and an example board"
  (should-contain #"Welcome to tic tac toe!*"
  instructions)))

(describe "invalid-name"
  (it "should give a detailed error about why a name is invalid"
  (should-contain #"is not a valid name*" (invalid-name ""))))

(describe "invalid-piece"
  (it "should give a deatiled error about why a piece is invalid"
    (should-contain #"is not a valid piece*" (invalid-piece "p"))))

(describe "turn-order"
  (it "should ask the players for turn order"
    (should-contain #"Type 1 if you would like*" (turn-order "Bobby"))))

(describe "invalid-turn-order"
  (it "give a message telling them their turn order was invalid"
    (should-contain #"9 is not a valid turn order*" (invalid-turn-order "9"))))

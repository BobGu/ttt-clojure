(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))


(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

(defn get-player-input [message validator]
  (let [input (prompt message) valid-input validator]
    (if (valid-input input)
     input
     (do
       (print (invalid-input input))
       (get-player-input message validator)))))

(defn first-player [turn-order player1-name player2-name]
  (if (= "1" turn-order)
    player1-name
    player2-name))


(defn start-game []
  (print instructions)
  (let [player1 (get-player-input ask-player-for-name valid-name?)
        turn-order-message (ask-player-for-turn-order player1)]
  (get-player-input ask-player-for-piece valid-piece?)
  (let [player2 (get-player-input ask-player-for-name valid-name?)]
  (let
    [first-player-name
    (first-player
      (get-player-input turn-order-message valid-turn-order?)
      player1
      player2)]
  (print (board-formatter empty-board)) 
  (print (ask-player-for-move first-player-name))))))

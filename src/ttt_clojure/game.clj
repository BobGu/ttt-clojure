(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))


(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

(defn get-player-input [message validator invalid-message]
  (let [input (prompt message) valid-input validator invalid-message invalid-message]
    (if (valid-input input)
     input
     (do
       (print (invalid-message input))
       (get-player-input message validator invalid-message)))))

(defn start-game []
  (print instructions)
  (get-player-input ask-player-for-name valid-name? invalid-name)
  (get-player-input ask-player-for-piece valid-piece? invalid-piece)
  (get-player-input ask-player-for-name valid-name? invalid-name))
;if not valid asks again
;Game asks for second players name
  ;First player is prompted to go, board is shown
  ;Get players input
  ;Check if that spot is available
  ;updates board with that spot and piece
  ;if game is won or tied game is over, exit out
  ;second player is shown board
  ;moves in a spot, if spot is available
  ;updates board with that spot and piece
  ;if game is won or tied game is over, exit out

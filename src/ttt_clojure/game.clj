(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))


(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

(defn get-player-name []
  (let [name (prompt ask-player-for-name)]
  (if (valid-name? name)
    name
    (do
      (print (invalid-name name))
      (get-player-name)))))


(defn start-game []
  (print instructions)
  (get-player-name)
  (print ask-player-for-piece))
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

(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]))


(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

;Game flow
;Game asks for first players name
;asks for first players piece "Either X or O"
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

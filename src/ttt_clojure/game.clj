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
    (get-player-name))))


(defn start-game []
  (print instructions))
  ; (if (valid-name? (prompt ask-player-for-name)))
    ;first player variable will have this name
    ;keep asking for a name
  ; )
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

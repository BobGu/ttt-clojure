(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]
            [ttt-clojure.human :refer :all]
            [ttt-clojure.player :refer :all]))


(defn game-won? [board]
  (some true? (map #(all-spaces-the-same? %) (possible-wins board))))

(defn game-tied? [board]
  (and (not (game-won? board)) (not (spaces-available? board))))

(defn game-over? [board]
  (or (game-tied? board) (game-won? board)))

(defn assign-turn-order [turn-order players-info]
  (if (= turn-order "1")
    players-info
    (reverse players-info)))

(defn opposite-piece [piece]
  (if (= "X" piece) "O" "X"))

(defn moves [board players-info]
  (loop [board board
         players-info players-info]
  (print (board-formatter board))
  (if (game-over? board)
    (if (game-won? board)
      (winner-message ((last players-info) :name))
      tie-message)
    (recur (update-board
             board
             ((first players-info) :piece)
             (read-string
               (fetch-player-move
                 human
                 (ask-player-for-move ((first players-info) :name))
                 board)))
           (reverse players-info)))))

(defn start-game []
  (get-player-input game-mode valid-game-mode?)
  (print instructions)
  (let  [player1-info {:name (fetch-player-name human) :piece (fetch-player-piece human)}
         player2-info {:name (fetch-player-name human) :piece (opposite-piece (player1-info :piece))}
         players-info [player1-info player2-info]
         turn-order-message (ask-player-for-turn-order players-info)
         turn-order (get-player-input turn-order-message valid-turn-order?)]
  (print (moves empty-board (assign-turn-order turn-order players-info)))))

(defn -main []
  (start-game))

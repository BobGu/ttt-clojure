(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))

(defn printer [message]
  (print message))

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

(defn get-player-input [message validator]
  (let [input (prompt message)
        valid-input validator]
    (if (valid-input input)
     input
     (do
       (printer (invalid-input input))
       (get-player-input message validator)))))

(defn get-player-name []
  (get-player-input ask-player-for-name valid-name?))

(defn get-player-piece []
  (clojure.string/upper-case (get-player-input ask-player-for-piece valid-piece?)))


(defn get-player-move [player-name board]
  (let [move (read-string (prompt (ask-player-for-move player-name)))]
    (if (valid-move? move board)
      move
     (do
       (printer (invalid-input move))
       (get-player-move player-name board)))))

(defn opposite-piece [piece]
  (if (= "X" piece) "O" "X"))

(defn moves [board players-info]
  (loop [board board
         players-info players-info]
  (printer (board-formatter board))
  (if (game-over? board)
    (if (game-won? board)
      (winner-message ((last players-info) :name))
      tie-message)
    (recur (update-board
             board
             ((first players-info) :piece)
             (get-player-move ((first players-info) :name) board))
           (reverse players-info)))))

(defn get-player-one-info []
  {:name (get-player-name) :piece (get-player-piece)})

(defn get-player-two-info [opponents-piece]
  {:name (get-player-name) :piece (opposite-piece opponents-piece)})

(defn start-game []
  (printer instructions)
  (let [player1-info (get-player-one-info)
        players-info [player1-info (get-player-two-info (player1-info :piece))]
        turn-order-message (ask-player-for-turn-order (player1-info :name))
        turn-order (get-player-input turn-order-message valid-turn-order?)
        players-info (assign-turn-order turn-order players-info)]
  (printer (moves empty-board players-info))))

(defn -main []
  (start-game))

(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))

(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

(defn game-tied? [board]
  (= (not (game-won? board)) (not (spaces-available? board))))

(defn get-player-input [message validator ]
  (let [input (prompt message)
        valid-input validator
        ]
    (if (valid-input input)
     input
     (do
       (print (invalid-input input))
       (get-player-input message validator)))))

(defn first-player [turn-order player1-name player2-name]
  (if (= "1" turn-order)
    player1-name
    player2-name))

(defn second-player [turn-order player1-name player2-name]
  (if (= "2" turn-order)
    player1-name
    player2-name))

(defn get-player-name[]
  (get-player-input ask-player-for-name valid-name?))

(defn get-player-piece []
  (clojure.string/upper-case (get-player-input ask-player-for-piece valid-piece?)))

(defn get-player-move [player-name board]
  (let [move (prompt (ask-player-for-move player-name))]
    (if (valid-move? move board )
      (read-string move)
     (do
       (print (invalid-input move))
       (get-player-move player-name board)))))

(defn opposite-piece [piece]
  (if (= "X" piece)
    "O"
    "X"))

(defn moves [board player-piece player-name other-player-name]
  (print (board-formatter board))
  (if-not (game-won? board)
    (do
      (let [board (update-board board player-piece (get-player-move player-name board))
            player-piece (opposite-piece player-piece)
            name other-player-name
            other-player-name player-name]
      (moves board player-piece name other-player-name)))
    other-player-name))

(defn start-game []
  (print instructions)
  (let [player1 (get-player-name)
        player1-piece (get-player-piece)
        player2 (get-player-name)
        player2-piece (opposite-piece player1-piece)
        turn-order-message (ask-player-for-turn-order player1)
        turn-order (get-player-input turn-order-message valid-turn-order?)
        first-player-name (first-player turn-order player1 player2)
        second-player-name (second-player turn-order player1 player2)
        first-player-piece (first-player turn-order player1-piece player2-piece)
        second-player-piece(second-player turn-order player1-piece player2-piece)
        board empty-board]
  (print (winner-message (moves board first-player-piece first-player-name second-player-name)))))

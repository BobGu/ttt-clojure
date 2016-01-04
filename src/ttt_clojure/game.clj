(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))

(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

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

(defn start-game []
  (print instructions)
  (let [player1 (get-player-name)
        turn-order-message (ask-player-for-turn-order player1)
        player1-piece (get-player-piece)
        turn-order (get-player-input turn-order-message valid-turn-order?)
        player2 (get-player-name)
        first-player-name (first-player turn-order player1 player2)
        board empty-board]
  (print (board-formatter board))
  (print (board-formatter (update-board board player1-piece (get-player-move first-player-name board))))))

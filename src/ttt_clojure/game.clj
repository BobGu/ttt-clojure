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
  (print (board-formatter board))
  (let [updated-board (update-board board first-player-piece (get-player-move first-player-name board))]
  (print (board-formatter updated-board))
  (let [updated-board1 (update-board updated-board second-player-piece (get-player-move second-player-name updated-board))]
  (print (board-formatter updated-board1))))))

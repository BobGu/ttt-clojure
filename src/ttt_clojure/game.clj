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

(defn get-player-input [message validator]
  (let [input (prompt message)
        valid-input validator]
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

(defn moves [board player-piece next-player-to-move last-player-to-move]
  (print (board-formatter board))
  (if (or (game-won? board) (game-tied? board))
    (if (game-won? board)
      (winner-message last-player-to-move)
      tie-message)
    (do
      (let [board (update-board board player-piece (get-player-move next-player-to-move board))
            player-piece (opposite-piece player-piece)
            next-player last-player-to-move
            last-player next-player-to-move]
      (moves board player-piece next-player last-player)))))


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
        board empty-board]
  (print (moves board first-player-piece first-player-name second-player-name))))

(defn -main []
  (start-game))

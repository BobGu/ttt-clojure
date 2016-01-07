(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))

(defn game-won? [board]
  (some true? (map #(all-spaces-the-same? %) (possible-wins board))))

(defn game-tied? [board]
  (and (not (game-won? board)) (not (spaces-available? board))))

(defn assign-turn-order [turn-order players-info]
  (if (= turn-order "1")
    players-info
    (reverse players-info)))

(defn first-player [turn-order player1-info player2-info]
  (if (= "1" turn-order)
    player1-info
    player2-info))

(defn second-player [turn-order player1-name player2-name]
  (if (= "2" turn-order)
    player1-name
    player2-name))

(defn get-player-input [message validator]
  (let [input (prompt message)
        valid-input validator]
    (if (valid-input input)
     input
     (do
       (print (invalid-input input))
       (get-player-input message validator)))))

(defn get-player-name []
  (get-player-input ask-player-for-name valid-name?))

(defn get-player-piece []
  (clojure.string/upper-case (get-player-input ask-player-for-piece valid-piece?)))

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))

(defn get-player-move [player-name board]
  (let [move (parse-int (prompt (ask-player-for-move player-name)))]
    (if (valid-move? move board)
      move
     (do
       (print (invalid-input move))
       (get-player-move player-name board)))))

(defn opposite-piece [piece]
  (if (= "X" piece) "O" "X"))

(defn moves [board players-info]
  (loop [board board
         players-info players-info]
  (print (board-formatter board))
  (if (or (game-won? board) (game-tied? board))
    (if (game-won? board)
      (winner-message ((last players-info) :name))
      tie-message)
    (recur (update-board
             board
             ((first players-info) :piece)
             (get-player-move ((first players-info) :piece) board))
           (reverse players-info)))))

(defn start-game []
  (print instructions)
  (let [player1-name (get-player-name)
        player1-piece (get-player-piece)
        player2-name (get-player-name)
        player2-piece (opposite-piece player1-piece)
        players-info [{:name player1-name :piece player1-piece} {:name player2-name :piece player2-piece}]
        turn-order-message (ask-player-for-turn-order player1-name)
        turn-order (get-player-input turn-order-message valid-turn-order?)
        players-info (assign-turn-order turn-order players-info)]
  (print (moves empty-board players-info))))

(defn -main []
  (start-game))

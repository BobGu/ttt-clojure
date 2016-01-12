(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]))

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
  (print validator)
  (let [input (prompt message)
        valid-input validator]
    (if (valid-input input)
     input
     (do
       (print (invalid-input input))
       (get-player-input message valid-input)))))

(defn get-player-name []
  (get-player-input ask-player-for-name valid-name?))

(defn get-player-piece []
  (clojure.string/upper-case (get-player-input ask-player-for-piece valid-piece?)))

(defn validate-move [board]
  (fn [move]
    (valid-move? move board)))

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
             (read-string (get-player-input ((first players-info) :name) (validate-move board))))
           (reverse players-info)))))

(defn get-player-one-info []
  {:name (get-player-name) :piece (get-player-piece)})

(defn get-player-two-info [player1-info]
  {:name (get-player-name) :piece (opposite-piece (player1-info :piece))})

(defn players-info [player1-info player2-info]
  [player1-info player2-info])

(defn start-game []
  (print instructions)
  (let [players-info (-> (-> (get-player-one-info)(get-player-two-info))(players-info))
        turn-order-message (ask-player-for-turn-order (first players-info :name))
        turn-order (get-player-input turn-order-message valid-turn-order?)
        players-info (assign-turn-order turn-order players-info)]
  (print (moves empty-board players-info))))

(defn -main []
  (start-game))

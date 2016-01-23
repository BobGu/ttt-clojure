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
         players-info players-info
         current-player (first players-info)]
  (print (board-formatter board))
  (if (game-over? board)
    (if (game-won? board)
      (winner-message (.get-name (last players-info)))
      tie-message)
    (recur (update-board
             board
             (.get-piece (first players-info))
             (read-string (.get-move current-player (ask-player-for-move( .get-name current-player)) board)))
           (reverse players-info)
           (first players-info)))))

(defn get-game-mode []
  (clojure.string/upper-case (get-player-input game-mode valid-game-mode?)))

(defn player2-piece [player1-info]
  (opposite-piece (player1-info :piece)))

(defn get-players-info []
  (if (= (get-game-mode) "HH")
    (let [first-player-name (get-player-input ask-player-for-name valid-name?)
          first-player-piece (get-player-input ask-player-for-piece valid-piece?)
          second-player-name (get-player-input ask-player-for-name valid-name?)
          second-player-piece (opposite-piece first-player-piece)]
      [ (new-human first-player-name first-player-piece)
        (new-human second-player-name second-player-piece) ])))

(defn start-game []
  (let [players-info (get-players-info)
        turn-order-message (ask-player-for-turn-order players-info)
        turn-order (get-player-input turn-order-message valid-turn-order?)]
  (print instructions)
  (print (moves empty-board (assign-turn-order turn-order players-info)))))

(defn -main []
  (start-game))

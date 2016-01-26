(ns ttt-clojure.game
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.message-factory :refer :all]
            [ttt-clojure.validate-input :refer :all]
            [ttt-clojure.input :refer :all]
            [ttt-clojure.human :refer :all]
            [ttt-clojure.player :refer :all]
            [ttt-clojure.rules :refer :all]
            [ttt-clojure.negamax :refer :all]
            [ttt-clojure.computer :refer :all]
            [ttt-clojure.minimax :refer :all]))

(defn minimax-strategy []
  (fn [piece board] (better-move piece board)))

(defn negamax-strategy []
  (fn [piece board] (best-move piece board)))

(defn assign-turn-order [turn-order players-info]
  (if (= turn-order "1")
    players-info
    (reverse players-info)))

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
             (.get-piece current-player)
             (.get-move current-player (ask-player-for-move (.get-name current-player)) board))
           (reverse players-info)
           (last players-info)))))

(defn get-players-info []
  (if (= (get-game-mode) "HH")
    (let [first-player-name (get-player-name)
          first-player-piece (get-player-piece)
          second-player-name (get-player-input ask-player-for-name valid-name?)
          second-player-piece (opposite-piece first-player-piece)]
      [ (new-human first-player-name first-player-piece)
        (new-human second-player-name second-player-piece) ])

    (let [first-player-name (get-player-name)
          first-player-piece (get-player-piece)]
      [ (new-human first-player-name first-player-piece)
        (new-computer (opposite-piece first-player-piece) (minimax-strategy))])))

(defn start-game []
  (print instructions)
  (let [players-info (get-players-info)
        turn-order-message (ask-player-for-turn-order (.get-name (first players-info)))
        turn-order (get-turn-order turn-order-message)]
  (print (moves empty-board (assign-turn-order turn-order players-info)))))

(defn -main []
  (start-game))

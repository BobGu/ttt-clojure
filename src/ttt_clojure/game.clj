(ns ttt-clojure.game
  (:require [ttt-clojure.core :refer :all]))


(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

(defn next-player-move [board]
  (update-board board "X" 0))

(defn game-start [board]
  (if (game-won? board)
  "Game Over"
  (next-player-move board)))

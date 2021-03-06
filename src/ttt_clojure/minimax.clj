(ns ttt-clojure.minimax
  (:require [ttt-clojure.rules :refer :all]
            [ttt-clojure.board :refer :all]
            [ttt-clojure.negamax :refer :all]))

(defn score-minimax [board maximizing-player]
  (if (and (game-won? board) maximizing-player) 10
    (if (and (game-won? board) (not maximizing-player))
     -10
      0)))

(defn minimax [piece board index maximizing-player depth original-depth]
  (let [board (update-board board piece index)
        spaces (spaces-available board)]
  (if (or (game-over? board) (< 4 (- original-depth depth)))
    (* depth (score-minimax board maximizing-player))
    (let [maximizing-player (not maximizing-player)]
      (if maximizing-player
        (apply max (map #(minimax (opposite-piece piece) board % maximizing-player (dec depth) original-depth) spaces))
        (apply min (map #(minimax (opposite-piece piece) board % maximizing-player (dec depth) original-depth) spaces)))))))

(def m-minimax (memoize minimax))

(defn score-that-map [piece board]
  (let [score-map (sorted-map)
        spaces (spaces-available board)
        scored-spaces (map #(hash-map (m-minimax piece board % true (depth board) (depth board)) %) spaces)]
    (into score-map (score-and-list-of-spaces scored-spaces))))

(defn better-move [piece board]
  (let [scores (score-that-map piece board)
        recommended-space (first (to-set (last (vals scores))))]
        recommended-space))


(ns ttt-clojure.ai
  (:require [ttt-clojure.game :refer :all]
            [ttt-clojure.board :refer :all]))

(defn three-in-a-row-possible? [piece set]
  (and
    (not-any? #{(opposite-piece piece)} set)
    (= 2 (count (filterv #(= piece %) set)))))

(defn three-in-a-row? [piece set]
  (= 3 (count (filterv #(= piece %) set))))

(defn won? [piece board]
  (some #(three-in-a-row? piece %) (possible-wins board)))

(defn winning-set [piece board]
  (let [game-results (map #(three-in-a-row-possible? piece %)(possible-wins board))]
  (nth (possible-wins board) (.indexOf game-results true))))

(defn winning-move [piece board]
  (first (filter #(not (= piece %)) (winning-set piece board))))

(defn possible-boards [board]
  [(assoc board (first (spaces-available board)) "X")])

(defn score-a-board [depth piece board]
  (if (game-tied? board) 0
    (if (won? piece board)
      (* 1 depth)
      (* -1 depth))))

(defn minimax [piece board]
  (loop [piece piece
        board board
        last-move ""]
  (print board)
  (if (or (= 1 (depth board)) (game-won? board))
    {:move last-move :score (score-a-board (depth board) piece board)}
    (recur
      piece
      (update-board board piece (first (spaces-available board)))
      (first (spaces-available board))))))


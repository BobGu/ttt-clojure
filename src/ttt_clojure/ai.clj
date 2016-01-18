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

(defn score-a-board [depth piece board]
  (if (game-tied? board) 0
    (if (won? piece board)
      (* 1 depth)
      (* -1 depth))))

(defn future-boards [piece board]
  (map #(update-board board piece %) (spaces-available board)))

(defn scores [piece board maximizing-player]
  (let  [piece piece
         board board
         maximizing-player maximizing-player]

  (if (game-over? board)
    (score-a-board (depth board) maximizing-player board)
    (let [scores (map #(scores (opposite-piece piece) % maximizing-player)(future-boards piece board))]
    (map flatten (doall scores))))))

(defn moves-and-scores [moves scores]
  (map vector moves scores))

(defn minumum-scoring-move [moves-scores]
  (first (first (sort-by #(last %) moves-scores))))

(defn best-move [piece board]
  (let [scores (scores piece board (opposite-piece piece))
        moves (spaces-available board)]
    (minumum-scoring-move (moves-and-scores moves scores))))

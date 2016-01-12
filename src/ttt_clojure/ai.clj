(ns ttt-clojure.ai
  (:require [ttt-clojure.game :refer :all]
            [ttt-clojure.board :refer :all]))

(defn three-in-a-row-possible? [piece set]
  (and
    (not-any? #{(opposite-piece piece)} set)
    (= 2 (count (filterv #(= piece %) set)))))

(defn can-win? [piece board]
  (some true? (map #(three-in-a-row-possible? piece %)(possible-wins board))))

(defn find-winning-set [piece board]
  (let [game-results (map #(three-in-a-row-possible? piece %)(possible-wins board))]
  (nth (possible-wins board) (.indexOf game-results true))))

(defn possible-boards [board]
  [(assoc board (first (spaces-available board)) "X")])


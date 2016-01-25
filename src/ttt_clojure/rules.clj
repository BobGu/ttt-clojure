(ns ttt-clojure.rules
  (:require [ttt-clojure.board :refer :all]))

(defn game-won? [board]
  (some true? (map #(all-spaces-the-same? %) (possible-wins board))))

(defn game-tied? [board]
  (and (not (game-won? board)) (not (spaces-available? board))))

(defn game-over? [board]
  (or (game-tied? board) (game-won? board)))


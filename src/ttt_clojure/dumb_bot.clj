(ns ttt-clojure.dumb-bot
  (:require [ttt-clojure.board :refer :all]))

(defn one-open-space? [row]
  (some true? (map #(number? %) row)))

(defn two-pieces-in-a-row? [piece row]
  (= 2 (count (filterv #(= piece %) row))))

(defn three-in-a-row-possible? [piece row]
  (and (two-pieces-in-a-row? piece row) (one-open-space? row)))

(defn can-win? [piece board]
  (some true? (map #(three-in-a-row-possible? piece %) (possible-wins board))))

(defn find-winning-set [piece board]
  (let [index-of-winning-set (.indexOf (map #(three-in-a-row-possible? piece %) (possible-wins board)) true)]
    (nth (possible-wins board) index-of-winning-set)))

(defn find-winning-move [piece board]
  (let [winning-set (find-winning-set piece board)]
    (first (filterv number? winning-set))))


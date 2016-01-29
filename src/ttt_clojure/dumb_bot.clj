(ns ttt-clojure.dumb-bot
  (:require [ttt-clojure.board :refer :all]))

(defn two-pieces-in-a-row? [piece row]
  (= 2 (count (filterv #(= piece %) row))))

(defn three-in-a-row-possible? [piece row]
  (and (two-pieces-in-a-row? piece row) (some true? (map #(number? %) row))))

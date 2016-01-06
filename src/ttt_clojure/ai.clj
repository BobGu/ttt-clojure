(ns ttt-clojure.ai
  (:require [ttt-clojure.game :refer :all]))

(defn bot-can-win? [piece board]
  true
  )

(defn three-in-a-row-possible? [piece set]
  (and
    (not-any? #{(opposite-piece piece)} set)
    (= 2 (count (filterv #(= piece %) set)))))


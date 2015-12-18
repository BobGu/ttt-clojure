(ns ttt-clojure.game
  (:require [ttt-clojure.core :refer :all]))


(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

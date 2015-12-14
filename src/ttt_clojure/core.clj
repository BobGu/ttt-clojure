(ns ttt-clojure.core)

(def empty-board
  [0 1 2 3 4 5 6 7 8])

(defn update-board [board piece position]
  (assoc board position piece))

(defn space-available? [board position]
  (= position (nth board position)))

(defn rows [board]
  (partition 3 board))

(defn three-in-a-row? [row]
  (every? (fn [space] (= (first row) space)) row))

(defn game-won? [board]
  (let [rows (rows board)]
  (some true? (map #(three-in-a-row? %) rows))))

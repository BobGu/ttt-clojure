(ns ttt-clojure.core)

(def empty-board
  [0 1 2 3 4 5 6 7 8])

(defn update-board [board piece position]
  (assoc board position piece))

(defn space-available? [board position]
  (= position (nth board position)))

(defn rows [board]
  (partition 3 board))

(defn game-won? [board]
  (some true? (vector (every? (fn [space] (= "X" space)) (vector (first board) (nth board 1) (nth board 2)))
  (every? (fn [space] (= "X" space)) (vector (nth board 3) (nth board 4) (nth board 5)))
  (every? (fn [space] (= "X" space)) (vector (nth board 6) (nth board 7) (nth board 8))))))

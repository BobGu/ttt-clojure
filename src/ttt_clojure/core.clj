(ns ttt-clojure.core)

(def empty-board
  [0 1 2 3 4 5 6 7 8])

(defn update-board [board piece position]
  (assoc board position piece))

(defn space-available? [board position]
  (= position (nth board position)))

(defn rows [board]
  (into [] (partition 3 board)))

(defn columns [board]
  (let [rows (rows board)]
  (map vector (first rows) (nth rows 1) (nth rows 2))))

(defn possible-wins [board]
  (let [rows (rows board) columns (columns board)]
  (apply conj rows columns)))

(defn all-spaces-the-same? [set-of-spaces]
  (every? (fn [space] (= (first set-of-spaces) space)) set-of-spaces))

(defn game-won? [board]
  (let [possible-wins (possible-wins board)]
  (some true? (map #(all-spaces-the-same? %) possible-wins))))

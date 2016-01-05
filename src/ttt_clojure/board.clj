(ns ttt-clojure.board)

(def empty-board
  [0 1 2 3 4 5 6 7 8])

(defn update-board [board piece position]
  (assoc board position piece))

(defn space-available? [board position]
  (= position (nth board position)))

(defn spaces-available? [board]
  (some true? (map #(space-available? board %)[0 1 2 3 4 5 6 7 8])))

(defn rows [board]
  (into [] (partition 3 board)))

(defn columns [board]
  (let [rows (rows board)]
  (map vector (rows 0) (rows 1) (rows 2))))

(defn diagonals [board]
  (vector
    (map board [0 4 8])
    (map board [2 4 6])))

(defn possible-wins [board]
  (let [rows (rows board) columns (columns board) diagonals (diagonals board)]
  (apply conj (apply conj rows columns) diagonals)))

(defn all-spaces-the-same? [set-of-spaces]
  (every? (fn [space] (= (first set-of-spaces) space)) set-of-spaces))

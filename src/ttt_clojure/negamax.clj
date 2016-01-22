(ns ttt-clojure.negamax
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.game :refer :all]
            [ttt-clojure.player :refer :all]))

(defn abs [n] (max n (- n)))

(defn best-value [scores]
  (- (last (sort-by #(abs %) (map #(* -1 %) scores)))))

(defn score [board]
  (if (game-won? board) 10 0))

(defn negamax
  ([piece board index](negamax piece board index 1 (count (spaces-available board))))
  ([piece board index color depth]
  (let [board (update-board board piece index)
        spaces (spaces-available board)]
    (if (game-over? board)
      (* color (* depth (score board)))
      (best-value (flatten (map #(negamax (opposite-piece piece) board % (- color) (dec depth)) spaces)))))))

(defn to-set [s]
  (if (set? s) s #{s}))

(defn set-union [s1 s2]
  (clojure.set/union (to-set s1) (to-set s2)))

(defn mergeMatches [propertyMapList]
  (reduce #(merge-with set-union %1 %2) {} propertyMapList))

(defn score-map [piece board]
  (let [score-map (sorted-map)
        spaces (spaces-available board)
        scored-spaces (map #(hash-map (negamax piece board %) %) spaces)]
    (into score-map (mergeMatches scored-spaces))))

(defn get-move [piece board]
  (let [scores (score-map piece board)]
    (first (to-set (last (vals scores))))))

(deftype Computer [piece]
  Player
  (fetch-player-name [this] "Johnny-5")
  (fetch-player-move [this message board] (get-move piece board)))

(defn computer [piece] (Computer. piece))


(ns ttt-clojure.negamax
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.game :refer :all]))


(defn score [board]
  (if (game-won? board) 10 0))

(defn negamax
  ([piece board index](negamax piece board index 1))
  ([piece board index color]
  (let [board (update-board board piece index)
        spaces (spaces-available board)]
    (if (game-over? board)
      (* color (score board))
      (map #(negamax (opposite-piece piece) board % (- color)) spaces))))))

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

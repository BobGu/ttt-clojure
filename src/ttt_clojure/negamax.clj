(ns ttt-clojure.negamax
  (:require [ttt-clojure.board :refer :all]
            [ttt-clojure.player :refer :all]
            [ttt-clojure.rules :refer :all]))

(defn opposite-piece [piece]
  (if (= "X" piece) "O" "X"))

(defn abs [n] (max n (- n)))

(defn best-value [scores]
  (- (last (sort-by #(abs %) (map #(* -1 %) scores)))))

(defn score [board]
  (if (game-won? board) 10 0))

(defn negamax
  ([piece board index](negamax piece board index 1 (depth board)))
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

(defn score-and-list-of-spaces [scored-spaces]
  (reduce #(merge-with set-union %1 %2) {} scored-spaces))

(defn score-map [piece board]
  (let [score-map (sorted-map)
        spaces (spaces-available board)
        scored-spaces (map #(hash-map (negamax piece board %) %) spaces)]
    (into score-map (score-and-list-of-spaces scored-spaces))))

(defn best-move [piece board]
  (let [scores (score-map piece board)
        recommended-space (first (to-set (last (vals scores))))]
    recommended-space))

